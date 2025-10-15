package ViktorVasileski.restApiTest.services;

import ViktorVasileski.restApiTest.entities.Author;
import ViktorVasileski.restApiTest.exceptions.NotFoundException;
import ViktorVasileski.restApiTest.repositories.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public Author findById(UUID id){
        return this.authorRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }
}
