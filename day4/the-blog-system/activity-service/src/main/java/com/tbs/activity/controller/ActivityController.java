package com.tbs.activity.controller;

import com.tbs.activity.model.Activity;
import com.tbs.activity.repository.ActivityRepository;
import con.tbs.payload.LogActivity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

@RestController
@RequestMapping("activity")
public class ActivityController {

    private final ActivityRepository activityRepository;

    public ActivityController(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void log(@Valid @RequestBody LogActivity request) {
        Activity activity = new Activity(request.getAction(), request.getData(), request.getIdentity());
        activityRepository.save(activity);
    }

    @GetMapping
    public Set<LogActivity> getLogs(@RequestParam(name = "q", required = false, defaultValue = "") String query) {
        Set<LogActivity> activities = new HashSet<>();

        Consumer<Activity> activityConsumer = activity -> activities.add(new LogActivity(activity.getAction(), activity.getData(), activity.getIdentity()));
        if (query.isBlank()) {
            activityRepository.findAll()
                    .forEach(activityConsumer);
        } else {
            activityRepository.findAllByDataContainingIgnoreCaseOrIdentityContainingIgnoreCaseOrActionContainsIgnoreCase(query, query, query)
                    .forEach(activityConsumer);
        }

        return activities;
    }
}
