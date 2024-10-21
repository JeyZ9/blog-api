package com.app.jblog.controller;

import com.app.jblog.models.Blog;
import com.app.jblog.repository.BlogRepository;
import com.app.jblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    private final BlogService blogService;
    private final BlogRepository blogRepository;

    @Autowired
    public BlogController(BlogService blogService, BlogRepository blogRepository) {
        this.blogService = blogService;
        this.blogRepository = blogRepository;
    }

    @GetMapping
    public ResponseEntity<List<Blog>> getAllBlog(){
        List<Blog> blogList = blogService.getBlog();
        return new ResponseEntity<>(blogList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Long id) {
        Optional<Blog> blog = blogService.getBlogById(id);
        return blog.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Blog> addBlog(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam MultipartFile image,
            @RequestParam String field
    ) throws IOException {
        Blog blog = new Blog();
        blog.setName(name);
        blog.setDescription(description);
        blog.setImage(String.valueOf(image));
        blog.setField(field);

        Blog addBlog = blogService.createBlog(blog, image);
        return ResponseEntity.status(HttpStatus.CREATED).body(addBlog);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> updateBlog(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam MultipartFile image,
            @RequestParam String field
    ) throws IOException {
        Blog getBlog = new Blog();
        getBlog.setName(name);
        getBlog.setDescription(description);
        getBlog.setImage(String.valueOf(image));
        getBlog.setField(field);

        Blog updateBlog = blogService.updateBlog(id, getBlog, image);

        return ResponseEntity.ok(updateBlog);
    }

    @DeleteMapping("/{id")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return ResponseEntity.noContent().build();
    }
}
