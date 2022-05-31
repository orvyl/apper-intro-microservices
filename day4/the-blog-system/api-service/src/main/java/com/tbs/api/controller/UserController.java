package com.tbs.api.controller;

import con.tbs.payload.ChangePasswordRequest;
import con.tbs.payload.LoginRequest;
import con.tbs.payload.LoginResponse;
import con.tbs.payload.UserRegistrationRequest;
import con.tbs.payload.UserRegistrationResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {
    /**
     * User can register
     * User login
     * User can change password
     */

    @PostMapping
    public UserRegistrationResponse register(@Valid @RequestBody UserRegistrationRequest request) {
        //TODO

        return null;
    }

    @PostMapping
    public LoginResponse authenticate(@Valid @RequestBody LoginRequest request) {
        //TODO

        return null;
    }

    @PutMapping
    public void changePassword(@Valid @RequestBody ChangePasswordRequest request) {
        //TODO
    }

}
