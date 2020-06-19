package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.models.Author;
import com.lambdaschool.foundation.models.Wrote;
import com.lambdaschool.foundation.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "authorService")
public class AuthorServiceImpl implements AuthorService{
    @Autowired
    private AuthorRepository authorrepos;

    @Override
    public List<Author> findAll() {
        List<Author> list = new ArrayList<>();
        authorrepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Author save(Author author) {
        Author newAuthor = new Author();

        if (author.getAuthorid() != 0) {
            newAuthor.setAuthorid(author.getAuthorid());
        }

        newAuthor.setLastname(author.getLastname());
        newAuthor.setFirstname(author.getFirstname());

        for (Wrote w : author.getWrotes()) {
            newAuthor.getWrotes().add(new Wrote(w.getAuthor(), w.getBook()));
        }

        return authorrepos.save(newAuthor);
    }
}
