package com.pblgllgs.todosrestapi.response;
/*
 *
 * @author pblgl
 * Created on 14-05-2025
 *
 */

public class AuthenticationResponse {
    private String token;

    public AuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
