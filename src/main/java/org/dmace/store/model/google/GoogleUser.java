package org.dmace.store.model.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;

public class GoogleUser {

    private String userId;
    private String email;
    private String name;
    private String familyName;
    private String givenName;
    private String pictureUrl;
    private String locale;

    public GoogleUser() {
    }

    public GoogleUser(GoogleIdToken.Payload payload) {
        this.userId = payload.getSubject();
        this.email = payload.getEmail();
        this.name = (String) payload.get("name");
        this.pictureUrl = (String) payload.get("picture");
        this.locale = (String) payload.get("locale");
        this.familyName = (String) payload.get("family_name");
        this.givenName = (String) payload.get("given_name");
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
