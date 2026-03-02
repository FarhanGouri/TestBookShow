package org.example.serviceImpl;

import org.example.Service.SearchService;
import org.example.model.Movie.Movie;
import org.example.repository.MovieRepository;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

public class SearchServiceImpl implements SearchService {

    @Autowired
    MovieRepository repository;

    @Override
    public Page getMovies(Pageable pageable, String search) {
        if (!search.isEmpty()){
            return (Page) repository.findAll(pageable);
        }else {
            return (Page) repository.findAllByNameContaining(search, pageable);
        }
    }
}
