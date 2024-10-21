package com.app.jblog.service.impl;

import com.app.jblog.models.Blog;
import com.app.jblog.repository.BlogRepository;
import com.app.jblog.service.BlogService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BlogServiceImpl implements BlogService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    private BlogRepository blogRepository;

    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public String saveFile(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);

        if (!Files.exists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }

        try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
            fos.write(file.getBytes());
        }

        return fileName;
    }

    public void deleteFile(String fileName) throws IOException {
        if(fileName != "noimg.jpg"){
            Path filePath = Paths.get(uploadDir, fileName);
            try {
                Files.deleteIfExists(filePath);
            }catch (IOException e){
                throw new IOException(e.getMessage());
            }
        }
    }


    @Override
    public List<Blog> getBlog(){
        return blogRepository.findAll();
    }

    @Override
    public Optional<Blog> getBlogById(Long id){
        Optional<Blog> blog = blogRepository.findById(id);
        return blog;
    }

    @Override
    public Blog createBlog(Blog blog, MultipartFile image) throws IOException {
        if (image != null){
            blog.setImage(saveFile(image));
        }else{
            blog.setImage("noimg.jpg");
        }
        return blogRepository.save(blog);
    }

    @Override
    public Blog updateBlog(Long id, Blog blog, MultipartFile image) throws IOException{
        Optional<Blog> blogId = blogRepository.findById(id);
        if (blogId.isPresent()) {
            Blog updatedBlog = blogId.get();
            updatedBlog.setName(blog.getName());
            updatedBlog.setDescription(blog.getDescription());
            updatedBlog.setField(blog.getField());

            if (image != null){
                String newFileName = saveFile(image);
                deleteFile(updatedBlog.getImage());
                updatedBlog.setImage(newFileName);
            }
            return blogRepository.save(updatedBlog);
        }else {
            throw new EntityNotFoundException("Blog with id " + id + " not found");
        }
    }

    @Override
    public void deleteBlog(Long id){
        Optional<Blog> blogId = blogRepository.findById(id);
        if(blogId.isPresent()) {
            blogRepository.delete(blogId.get());
        }else {
            throw new EntityNotFoundException("Blog with id " + id + " not found");
        }
    }
}
