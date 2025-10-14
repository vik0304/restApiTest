package ViktorVasileski.restApiTest.controllers;

import ViktorVasileski.restApiTest.entities.Blog;
import ViktorVasileski.restApiTest.payloads.NewBlogPayload;
import ViktorVasileski.restApiTest.services.BlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogsController {
    @Autowired
    private BlogsService blogsService;

    @GetMapping
    public List<Blog> getBlogs(){
        return this.blogsService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Blog createBlog(@RequestBody NewBlogPayload payload){
        return this.blogsService.saveBlog(payload);
    }

    @GetMapping("/{blogId}")
    public Blog getBlogById(@PathVariable long userId){
        return this.blogsService.findById(userId);
    }
}
