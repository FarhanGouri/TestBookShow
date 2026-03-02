package org.example.Service;

import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;

public interface SearchService {
    Page getMovies(Pageable pageable, String search);
}
