package com.tbs.content.controller;

import com.tbs.content.model.Content;
import com.tbs.content.model.Review;
import com.tbs.content.repository.ContentRepository;
import com.tbs.content.repository.ReviewRepository;
import con.tbs.payload.CreateReviewRequest;
import con.tbs.payload.ReviewResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("review")
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final ContentRepository contentRepository;

    public ReviewController(ReviewRepository reviewRepository, ContentRepository contentRepository) {
        this.reviewRepository = reviewRepository;
        this.contentRepository = contentRepository;
    }

    @PostMapping
    public ReviewResponse createReview(@Valid @RequestBody CreateReviewRequest request) throws ContentNotFoundException {
        Review review = new Review(getContent(request.getContentId()), request.getUserId(), request.getComment(), request.getStars());
        Review createdReview = reviewRepository.save(review);

        return new ReviewResponse(""+review.getId(), createdReview.getCreatedAt(), createdReview.getLastUpdated());
    }


    private Content getContent(String id) throws ContentNotFoundException {
        Optional<Content> result = contentRepository.findById(Long.parseLong(id));
        if (result.isEmpty()) {
            throw new ContentNotFoundException("Content with id " + id + " not found");
        }
        return result.get();
    }
}
