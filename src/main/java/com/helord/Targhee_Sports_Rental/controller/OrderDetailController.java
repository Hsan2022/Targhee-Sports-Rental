package com.helord.Targhee_Sports_Rental.controller;

import com.helord.Targhee_Sports_Rental.database.dao.OrderDetailsDAO;
import com.helord.Targhee_Sports_Rental.database.entity.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Slf4j
@Controller
public class OrderDetailController {

    private final OrderDetailsDAO orderDetailsDAO;

    @Autowired
    public OrderDetailController(OrderDetailsDAO orderDetailsDAO) {
        this.orderDetailsDAO = orderDetailsDAO;
    }

    @GetMapping("/orderDetails")
    public ModelAndView showOrderDetails() {
        ModelAndView mav = new ModelAndView("orderDetails");

        Integer orderId = 1; // Example orderId
        Integer productId = 1; // Example productId

//        List<OrderDetail> orderDetails = orderDetailsDAO.findByOrderIdAndProductId(orderId, productId);

//        mav.addObject("orderDetails", orderDetails);

        return mav;
    }
}
