package com.app.jblog.service;

import com.app.jblog.models.Blog;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface BlogService {
    List<Blog> getBlog();

    Optional<Blog> getBlogById(Long id);

    Blog createBlog(Blog blog, MultipartFile image) throws IOException;

    Blog updateBlog(Long id, Blog blog, MultipartFile image) throws IOException;

    void deleteBlog(Long id);
}
