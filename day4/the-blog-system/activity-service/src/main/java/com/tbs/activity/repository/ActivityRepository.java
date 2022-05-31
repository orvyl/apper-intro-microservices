package com.tbs.activity.repository;

import com.tbs.activity.model.Activity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ActivityRepository extends CrudRepository<Activity, Long> {
    Set<Activity> findAllByDataContainingIgnoreCaseOrIdentityContainingIgnoreCaseOrActionContainsIgnoreCase(String data, String identity, String action);
}
