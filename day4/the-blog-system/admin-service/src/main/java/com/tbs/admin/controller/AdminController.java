package com.tbs.admin.controller;

import con.tbs.payload.ContentResponse;
import con.tbs.payload.GetAllContentsResponse;
import con.tbs.payload.GetAllUsersResponse;
import con.tbs.payload.UserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {
    private final RestTemplate restTemplate;
    private final String userServiceEndpoint;
    private final String contentServiceEndpoint;

    public AdminController(RestTemplate restTemplate,
                           @Value("${user-service.endpoint}") String userServiceEndpoint,
                           @Value("${content-service.endpoint}") String contentServiceEndpoint) {
        this.restTemplate = restTemplate;
        this.userServiceEndpoint = userServiceEndpoint;
        this.contentServiceEndpoint = contentServiceEndpoint;
    }

    @GetMapping("all-users")
    public List<UserDetails> getAllUsers() throws InconsistentResultException, GetAllException {
        ResponseEntity<GetAllUsersResponse> entity = restTemplate.getForEntity(userServiceEndpoint + "/user/all", GetAllUsersResponse.class);

        if (entity.getStatusCode().is2xxSuccessful()) {
            GetAllUsersResponse body = entity.getBody();

            if (body.getCurrentUsers() != (long) body.getUsers().size()) {
                throw new InconsistentResultException("Returned number of user didn't match the actual list length");
            }

            return body.getUsers();
        }

        throw new GetAllException("Something went wrong");
    }

    @GetMapping("all-contents")
    public List<ContentResponse> getAllContents() throws InconsistentResultException, GetAllException {
        ResponseEntity<GetAllContentsResponse> entity = restTemplate.getForEntity(contentServiceEndpoint + "/content/all", GetAllContentsResponse.class);

        if (entity.getStatusCode().is2xxSuccessful()) {
            GetAllContentsResponse body = entity.getBody();

            if (body.getCurrentContentCount() != (long) body.getContents().size()) {
                throw new InconsistentResultException("Returned number of user didn't match the actual list length");
            }

            return body.getContents();
        }

        throw new GetAllException("Something went wrong");
    }
}
