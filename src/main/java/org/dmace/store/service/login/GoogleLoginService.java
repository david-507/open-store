package org.dmace.store.service.login;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.dmace.store.model.User;
import org.dmace.store.model.UserDetails;
import org.dmace.store.model.google.GoogleUser;
import org.dmace.store.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.Timestamp;
import java.util.Collections;

@Service
public class GoogleLoginService {
    private static Logger logger = LoggerFactory.getLogger(GoogleLoginService.class);
    /** Global instance of the HTTP transport. */
    private static HttpTransport httpTransport;
    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String CLIENT_ID = System.getenv("GOOGLE_CLIENT_ID");


    @Autowired
    private UserRepository userRepository;

    /**
     * Verifies the given google idToken
     * if its a valid idToken, returns the User assotiated with it
     *
     * @param idToken Valid google idToken
     * @return User
     */
    public User VerifyAndStore(String idToken) {
        User user = null;

        try {

            GoogleIdToken googleIdToken = verify(idToken);
            if (googleIdToken != null) {

                GoogleIdToken.Payload payload = googleIdToken.getPayload();

                if( payload.getEmailVerified() && CLIENT_ID.equals(payload.getAuthorizedParty()) ){
                    logger.info("email verified by google");

                    GoogleUser guser = new GoogleUser(payload);
                    // check if we already have a user with this email
                    user = userRepository.findByEmail(guser.getEmail());
                    if( user!=null )
                        updateUser(user, guser);
                    else
                        user = createUser(guser);
                    userRepository.save(user);
                }
            }

        } catch (Exception e) {
            logger.error("Error while verifying google user", e);
        }
        return user;
    }

    private void updateUser(User user, GoogleUser guser) {
        UserDetails details = user.getUserDetails();
        UserDetails newDetails = new UserDetails(guser);
        if( !newDetails.equals(details) ) {
            logger.info("Updating user details");
            user.setUserDetails(newDetails);
        }
        user.setUpdated(new Timestamp(System.currentTimeMillis()));
    }

    private GoogleIdToken verify(String idToken) throws GeneralSecurityException, IOException {
        if(CLIENT_ID==null)
            throw new IllegalArgumentException("GOOGLE_CLIENT_ID system variable was not set...");

        // initialize the transport
        httpTransport = GoogleNetHttpTransport.newTrustedTransport();

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, JSON_FACTORY)
                .setAudience(Collections.singletonList(CLIENT_ID))
                // if multiple clients access the backend:
                //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                .build();

        return verifier.verify(idToken);
    }

    private User createUser(GoogleUser guser) {
        User user = new User();
        user.setName(guser.getName());
        user.setEmail(guser.getEmail());
        user.setEnabled(true);

        user.setUserDetails( new UserDetails(guser));

        return user;
    }
}
