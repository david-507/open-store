package org.dmace.store.model;

import org.dmace.store.model.google.GoogleUser;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class UserDetails {

    private String googleId;
    private String familyName;
    private String givenName;
    private String pictureUrl;
    private String preferredLocale;

    public UserDetails() {
    }

    public UserDetails(GoogleUser guser) {
        this.googleId = guser.getUserId();
        this.familyName = guser.getFamilyName();
        this.givenName = guser.getGivenName();
        this.pictureUrl = guser.getPictureUrl();
        this.preferredLocale = guser.getLocale();
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
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

    public String getPreferredLocale() {
        return preferredLocale;
    }

    public void setPreferredLocale(String preferredLocale) {
        this.preferredLocale = preferredLocale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetails details = (UserDetails) o;
        return Objects.equals(googleId, details.googleId) &&
                Objects.equals(familyName, details.familyName) &&
                Objects.equals(givenName, details.givenName) &&
                Objects.equals(pictureUrl, details.pictureUrl) &&
                Objects.equals(preferredLocale, details.preferredLocale);
    }

    @Override
    public int hashCode() {
        return Objects.hash(googleId, familyName, givenName, pictureUrl, preferredLocale);
    }
}
