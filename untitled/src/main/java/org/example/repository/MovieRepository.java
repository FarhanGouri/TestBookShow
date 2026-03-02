package org.example.repository;

import org.example.model.Movie.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Override
    Page<Movie> findAll(Pageable pageable);

    Page<Movie> findAllByNameContaining(String name, Pageable pageable);
}

