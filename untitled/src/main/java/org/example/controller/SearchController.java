package org.example.controller;

import org.example.Service.SearchService;
import org.example.Service.UserService;
import org.example.authentication.UserSession;
import org.example.model.Movie.Movie;
import org.example.response.DataResponse;
import org.example.response.RestResponse;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SearchController {

    @Autowired
    UserService userService;

    @Autowired
    UserSession userSession;

    @Autowired
    SearchService searchService;

    @PostMapping(value = "/movie-search/", produces = "application/json")
    public RestResponse searchMovie(@RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "limit", defaultValue = "2") int limit,
                                    @RequestParam(value = "sort_by", defaultValue = "date") String sortBy,
                                    @RequestParam(value = "order_by", defaultValue = "asc") String orderBy,
                                    @RequestParam(value = "search", defaultValue = "", required = false) String search){

        Page pageMovie = null;
        if (search != null && !search.isEmpty()) {

            Sort sort = Sort.by(sortBy).ascending();
            if (orderBy.toUpperCase().equals("DESC")) {
                sort = sort.descending();
            }

            @SuppressWarnings("deprecation")
            Pageable pageable = new PageRequest(page, limit, sort);

            pageMovie = searchService.getMovies(pageable, search);
        }
        return new DataResponse(200,"Success", pageMovie);
    }
}
