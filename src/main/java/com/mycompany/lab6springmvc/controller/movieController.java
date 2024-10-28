package com.mycompany.lab6springmvc.controller;

import com.mycompany.lab6springmvc.model.Movie;
import com.mycompany.lab6springmvc.dao.MovieDao;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class movieController implements Controller {
    private MovieDao movieDao;

    public void setMovieDao(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String action = request.getParameter("action");

        if (action == null) {
            return listMovies();
        }

        switch (action) {
            case "new":
                return showNewForm();
            case "insert":
                return insertMovie(request);
            case "searchByTitle":
                return searchMovieByTitle(request);
            case "searchByActor":
                return searchMoviesByActor(request);
            case "searchByActress":
                return searchMoviesByActress(request);
            default:
                return listMovies();
        }
    }

    private ModelAndView listMovies() throws SQLException {
        List<Movie> listMovies = movieDao.selectAllMovies();
        ModelAndView modelAndView = new ModelAndView("movie-list");
        modelAndView.addObject("listMovies", listMovies);
        return modelAndView;
    }

    private ModelAndView showNewForm() {
        return new ModelAndView("movie-form");
    }

    private ModelAndView insertMovie(HttpServletRequest request) throws SQLException {
        String title = request.getParameter("title");
        String actor = request.getParameter("actor");
        String actress = request.getParameter("actress");
        String genre = request.getParameter("genre");
        int year = Integer.parseInt(request.getParameter("year"));

        Movie newMovie = new Movie(title, actor, actress, genre, year);
        movieDao.insertMovie(newMovie);
        return new ModelAndView("redirect:/movieController?action=list");
    }

    private ModelAndView searchMovieByTitle(HttpServletRequest request) throws SQLException {
        String title = request.getParameter("title");
        Movie movie = movieDao.selectMovieByTitle(title);
        ModelAndView modelAndView = new ModelAndView("movie-details");
        modelAndView.addObject("movie", movie);
        return modelAndView;
    }

    private ModelAndView searchMoviesByActor(HttpServletRequest request) throws SQLException {
        String actor = request.getParameter("actor");
        List<Movie> listMovies = movieDao.selectMoviesByActor(actor);
        ModelAndView modelAndView = new ModelAndView("movie-list");
        modelAndView.addObject("listMovies", listMovies);
        return modelAndView;
    }

    private ModelAndView searchMoviesByActress(HttpServletRequest request) throws SQLException {
        String actress = request.getParameter("actress");
        List<Movie> listMovies = movieDao.selectMoviesByActress(actress);
        ModelAndView modelAndView = new ModelAndView("movie-list");
        modelAndView.addObject("listMovies", listMovies);
        return modelAndView;
    }
}
