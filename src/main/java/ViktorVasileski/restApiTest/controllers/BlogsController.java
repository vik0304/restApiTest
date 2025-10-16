package ViktorVasileski.restApiTest.controllers;

import ViktorVasileski.restApiTest.entities.Blog;
import ViktorVasileski.restApiTest.payloads.NewBlogPayload;
import ViktorVasileski.restApiTest.services.BlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/blogs")
public class BlogsController {
    @Autowired
    private BlogsService blogsService;

    @GetMapping
    public Page<Blog> getAllBlogs(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "title") String sortBy){
        return this.blogsService.findAll(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Blog createBlog(@RequestBody NewBlogPayload payload){
        return this.blogsService.saveBlog(payload);
    }

    @GetMapping("/{blogId}")
    public Blog getBlogById(@PathVariable long blogId){
        return this.blogsService.findById(blogId);
    }

    @PutMapping("/{blogId}")
    public Blog findByIdAndUpdate(@PathVariable long id, @RequestBody NewBlogPayload payload){
        return this.blogsService.findByIdAndUpdate(id, payload);
    }

    @DeleteMapping("/{blogId}")
    public void findByIdAndDelete(@PathVariable long id){
        this.blogsService.findByIdAndDelete(id);
    }

    @PatchMapping("/{blogId/cover")
    public String uploadImage(@PathVariable long id, @RequestParam("cover")MultipartFile file) throws IOException{
        System.out.println(file.getSize());
        System.out.println(file.getName());
        return this.blogsService.uploadCover(id, file);
    }
}
