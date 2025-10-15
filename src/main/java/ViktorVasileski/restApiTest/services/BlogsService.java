package ViktorVasileski.restApiTest.services;

import ViktorVasileski.restApiTest.entities.Author;
import ViktorVasileski.restApiTest.entities.Blog;
import ViktorVasileski.restApiTest.exceptions.NotFoundException;
import ViktorVasileski.restApiTest.payloads.NewBlogPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BlogsService {
    @Autowired
    AuthorService authorService;

    private List<Blog> blogsDB = new ArrayList<>();

    public List<Blog> findAll(){
        return this.blogsDB;
    }

    public Blog saveBlog(NewBlogPayload payload){
        Author author = authorService.findById(payload.getAuthorId());
        Blog newBlog = new Blog(payload.getCategory(), payload.getTitle(), payload.getContent(), payload.getReadingTime(), author);
        this.blogsDB.add(newBlog);
        log.info("Il blog con id {} è stato aggiunto.", newBlog.getId());
        return newBlog;
    }

    public Blog findById(long id){
        Blog found = null;
        for (Blog blog : this.blogsDB) {
            if (blog.getId() == id) found = blog;
        }

        if (found == null) throw new NotFoundException(id);
        return found;
    }
}
