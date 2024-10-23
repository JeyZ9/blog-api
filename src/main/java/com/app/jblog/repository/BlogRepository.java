package com.app.jblog.repository;

import com.app.jblog.models.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    Blog findBlogByField(String fieldName);
}
