package com.tbs.api.controller;

import con.tbs.payload.ContentResponse;
import con.tbs.payload.CreateContentRequest;
import con.tbs.payload.CreateReviewRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("content")
public class ContentController {
    /**
     * create a post
     * comment to a post
     * Update content
     * Update comment
     * Search content
     */

    @PostMapping
    public ContentResponse create(@Valid @RequestBody CreateContentRequest request) {
        //TODO

        return null;
    }

    @PostMapping
    public void review(@Valid @RequestBody CreateReviewRequest request) {
        //TODO
    }

    @PutMapping("{id}")
    public void updateContent(@PathVariable(name = "id") String contentId, @Valid @RequestBody CreateContentRequest request) {
        //TODO
    }

    @PutMapping
    public void updateReview(@Valid @RequestBody CreateReviewRequest request) {
        //TODO
    }

    @GetMapping("{id}")
    public ContentResponse get(@PathVariable(name = "id")  String contentId) {
        //TODO

        return null;
    }

    //TODO implement search content
}
