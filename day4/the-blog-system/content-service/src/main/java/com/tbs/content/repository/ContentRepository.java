package com.tbs.content.repository;

import com.tbs.content.model.Content;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends CrudRepository<Content, Long> {
}
