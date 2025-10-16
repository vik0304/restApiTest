package ViktorVasileski.restApiTest.services;

import ViktorVasileski.restApiTest.entities.Author;
import ViktorVasileski.restApiTest.entities.Blog;
import ViktorVasileski.restApiTest.exceptions.NotFoundException;
import ViktorVasileski.restApiTest.payloads.NewBlogPayload;
import ViktorVasileski.restApiTest.repositories.BlogRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;


@Service
@Slf4j
public class BlogsService {
    @Autowired
    AuthorService authorService;
    @Autowired
    BlogRepository blogRepository;
    @Autowired
    private Cloudinary imageUploader;



    public Page<Blog> findAll(int pageNumber, int pageSize, String sortBy){
        if (pageSize > 25) pageSize = 25;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        return this.blogRepository.findAll(pageable);
    }

    public Blog saveBlog(NewBlogPayload payload){
        Author author = authorService.findById(payload.getAuthorId());
        Blog newBlog = new Blog(payload.getCategory(), payload.getTitle(), payload.getContent(), payload.getReadingTime(), author);
        this.blogRepository.save(newBlog);
        log.info("Il blog con id {} è stato aggiunto.", newBlog.getId());
        return newBlog;
    }

    public Blog findById(long id){
        return this.blogRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Blog findByIdAndUpdate(long id, NewBlogPayload payload){
        Blog found = this.findById(id);
        found.setCategory(payload.getCategory());
        found.setTitle(payload.getTitle());
        found.setContent(payload.getContent());
        found.setReadingTime(payload.getReadingTime());
        Author author = authorService.findById(payload.getAuthorId());
        found.setAuthor(author);
        Blog modifiedBlog = this.blogRepository.save(found);
        log.info("L'utente con id {} è stato modificato correttamente", modifiedBlog.getId());
        return modifiedBlog;
    }

    public void findByIdAndDelete(long id){
        Blog found = this.findById(id);
        this.blogRepository.delete(found);
    }

    public String uploadCover(long id, MultipartFile file){
//        TODO: da gestire
//        if(file.isEmpty()) throw new BadRequestException("File Vuoto!");
        Blog found = this.findById(id);
        try {
            Map result = imageUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String imageURL= (String) result.get("url");
            found.setCover(imageURL);
            return imageURL;
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
