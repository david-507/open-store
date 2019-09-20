package org.dmace.store.model.bean;

public class GoogleIdToken {
    private String idToken;

    public GoogleIdToken(String idToken) {
        this.idToken = idToken;
    }

    public GoogleIdToken() {
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }
}
