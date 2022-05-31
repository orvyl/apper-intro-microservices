package com.tbs.content.controller;

import com.tbs.content.model.Content;
import com.tbs.content.repository.ContentRepository;
import con.tbs.payload.ContentResponse;
import con.tbs.payload.CreateContentRequest;
import con.tbs.payload.ReviewResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("content")
public class ContentController {

    private final ContentRepository contentRepository;

    public ContentController(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @PostMapping
    public ContentResponse create(@Valid @RequestBody CreateContentRequest request) {

        Content newContent = new Content(request.getUserId(), request.getTitle(), request.getBody());
        Content createdContent = contentRepository.save(newContent);

        ContentResponse response = new ContentResponse(""+createdContent.getId(), createdContent.getUserId(), createdContent.getTitle(), createdContent.getBody());
        response.setCreatedAt(createdContent.getCreatedAt());
        response.setUpdatedAt(createdContent.getLastUpdated());

        return response;
    }

    @GetMapping("{id}")
    public ContentResponse get(@PathVariable String id, @RequestParam(defaultValue = "false", name = "with-reviews") Boolean includeReviews) throws ContentNotFoundException {
        Content content = getContent(id);

        ContentResponse response = new ContentResponse(id, content.getUserId(), content.getTitle(), content.getBody());
        response.setCreatedAt(content.getCreatedAt());
        response.setUpdatedAt(content.getLastUpdated());

        if (includeReviews) {
            content.getReviews().forEach(review -> {
                ReviewResponse rs = new ReviewResponse(""+review.getId(), review.getCreatedAt(), review.getLastUpdated());
                rs.setComment(review.getComment());
                rs.setStars(review.getStars());

                response.getReviews().add(rs);
            });
        }

        return response;
    }

    @PutMapping("{id}")
    public void update(@PathVariable String id, @Valid @RequestBody CreateContentRequest request) throws ContentNotFoundException {
        Content content = getContent(id);

        content.setTitle(request.getTitle());
        content.setBody(request.getBody());

        contentRepository.save(content);
    }

    private Content getContent(String id) throws ContentNotFoundException {
        Optional<Content> result = contentRepository.findById(Long.parseLong(id));
        if (result.isEmpty()) {
            throw new ContentNotFoundException("Content with id " + id + " not found");
        }
        return result.get();
    }
}
