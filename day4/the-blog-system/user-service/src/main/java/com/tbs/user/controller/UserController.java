package com.tbs.user.controller;

import com.tbs.user.model.User;
import com.tbs.user.repository.UserRepository;
import con.tbs.payload.GetAllUsersResponse;
import con.tbs.payload.UserDetails;
import con.tbs.payload.UserRegistrationRequest;
import con.tbs.payload.UserRegistrationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public UserRegistrationResponse register(@Valid @RequestBody UserRegistrationRequest request) throws UserRegistrationException {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserRegistrationException("User " + request.getEmail() + " already registered!");
        }

        User newUser = new User(request.getEmail(), request.getFirstName(), request.getLastName(), request.getPassword());
        User savedUser = userRepository.save(newUser);

        return new UserRegistrationResponse(savedUser.getId());
    }

    @GetMapping("all")
    public GetAllUsersResponse getAllUsers() {
        GetAllUsersResponse response = new GetAllUsersResponse(userRepository.count(), new ArrayList<>());
        userRepository.findAll().forEach(user -> response.getUsers().add(new UserDetails(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getCreatedAt())));

        return response;
    }

    @ExceptionHandler(UserRegistrationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleUserRegistrationException(UserRegistrationException e) {
        return Map.of("error", e.getMessage());
    }
}
