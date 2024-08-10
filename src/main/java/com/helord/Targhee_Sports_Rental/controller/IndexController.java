package com.helord.Targhee_Sports_Rental.controller;

import com.helord.Targhee_Sports_Rental.database.dao.ProductDAO;
import com.helord.Targhee_Sports_Rental.database.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class IndexController {

    @Autowired
    private ProductDAO productDao;

    // handling of client requests to this url
    @GetMapping("/search")
    public ModelAndView search(@RequestParam(required = false) String search) {

        // create a model, pass in the jsp view
        ModelAndView response = new ModelAndView("search");

        log.debug("The user searched for the term: {}", search);

        // the search term(s) passed in back to the object, seen on url
        response.addObject("search", search);
        // query a list of products from db matching the search parameter
        List<Product> products = productDao.findByNameOrCode(search);
        // add list result to the model
        response.addObject("products", products);

        // return ModelAndView
        return response;
    }
}
