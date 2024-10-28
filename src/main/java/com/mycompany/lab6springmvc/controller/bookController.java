package com.mycompany.lab6springmvc.controller;

import com.mycompany.lab6springmvc.dao.BooksDao;
import com.mycompany.lab6springmvc.model.Books;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class bookController implements Controller {

    private BooksDao booksDao;

    public void setBooksDao(BooksDao booksDao) {
        this.booksDao = booksDao;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String action = request.getParameter("action");

        try {
            if (action == null) {
                return listBooks();
            }
            switch (action) {
                case "showBookCount":
                    return showBookCountForm();
                case "showForm":
                    return showBookForm(request);
                case "insert":
                    return insertBooks(request);
                default:
                    return listBooks();
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private ModelAndView showBookCountForm() {
        return new ModelAndView("book-count");
    }

    private ModelAndView listBooks() throws SQLException {
        List<Books> listBooks = booksDao.selectAllBooks();
        ModelAndView modelAndView = new ModelAndView("book-list");
        modelAndView.addObject("listBooks", listBooks);
        return modelAndView;
    }

    private ModelAndView showBookForm(HttpServletRequest request) {
        int count = Integer.parseInt(request.getParameter("count"));
        ModelAndView modelAndView = new ModelAndView("book-form");
        modelAndView.addObject("count", count);
        return modelAndView;
    }

    private ModelAndView insertBooks(HttpServletRequest request) throws SQLException {
        String[] isbns = request.getParameterValues("isbn");
        String[] titles = request.getParameterValues("title");
        String[] authors = request.getParameterValues("authors");
        String[] prices = request.getParameterValues("price");

        List<Books> booksList = new ArrayList<>();
        for (int i = 0; i < isbns.length; i++) {
            String isbn = isbns[i];
            String title = titles[i];
            String author = authors[i];
            float price = Float.parseFloat(prices[i]);
            Books book = new Books(isbn, title, author, price);
            booksList.add(book);
        }

        booksDao.insertBooks(booksList);
        return new ModelAndView("redirect:/part7_book?action=list");
    }
}
