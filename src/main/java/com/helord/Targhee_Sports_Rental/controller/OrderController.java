package com.helord.Targhee_Sports_Rental.controller;

import com.helord.Targhee_Sports_Rental.database.dao.OrderDAO;
import com.helord.Targhee_Sports_Rental.database.dao.OrderDetailsDAO;
import com.helord.Targhee_Sports_Rental.database.dao.ProductDAO;
import com.helord.Targhee_Sports_Rental.database.entity.Order;
import com.helord.Targhee_Sports_Rental.database.entity.OrderDetail;
import com.helord.Targhee_Sports_Rental.database.entity.Product;
import com.helord.Targhee_Sports_Rental.database.entity.User;
import com.helord.Targhee_Sports_Rental.security.AuthenticatedUserUtilities;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class OrderController {

    @Autowired
    private OrderDAO orderDao;

    @Autowired
    private OrderDetailsDAO orderDetailsDao;

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private AuthenticatedUserUtilities authenticatedUserUtilities;
    @Autowired
    private HttpSession session;

    @GetMapping("/order/orderdetail")
    public ModelAndView orderDetail() {
        ModelAndView response = new ModelAndView("order/orderdetail");

        // get the logged-in user
        User user = authenticatedUserUtilities.getCurrentUser();

        // fetch order from the database where the status is 'CART'
        Order order = orderDao.findOrderInCartStatus(user.getId());

        if (order != null) {
            // get the order details for the order
            List<Map<String, Object>> orderDetails = orderDao.getOrderDetails(order.getId());
            response.addObject("orderDetails", orderDetails);

            // add total order amount
            Double orderTotal = orderDao.getOrderTotal(order.getId());
            response.addObject("orderTotal", orderTotal);
        }

        return response;
    }

    @GetMapping("/order/addToCart")
    public ModelAndView addToCart(@RequestParam Integer productId) {
        ModelAndView response = new ModelAndView();

        // search product in the database by passing in productId
        Product product = productDAO.findById(productId);

        // get the logged-in user
        User user = authenticatedUserUtilities.getCurrentUser();

        if (product == null) {
            // Error redirect, handle the case where the product is not found
            response.setViewName("redirect:/error");
            return response;
        }

        // get order from the db where the status is 'CART' and the user is the logged-in user
        Order order = orderDao.findOrderInCartStatus(user.getId());
        if (order == null) {
            // create a new order if user does not have an order in cart status
            order = new Order();
            order.setUser(user);
            order.setOrderDate(new Date());
            order.setStatus("CART");
            order.setRequiredDate(new Date());

            // save order
            orderDao.save(order);
        }

        // check if product is in order details table
        OrderDetail orderDetail = orderDetailsDao.isProductInCart(order.getId(), productId);
        if (orderDetail == null) {
            // create a new orderdetails if product is not part of this order
            orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(product);
            orderDetail.setQuantityOrdered(1);
            orderDetail.setOrderLineNumber(1);
            orderDetail.setPriceEach(product.getPrice()); // Ensure priceEach is set

            orderDetailsDao.save(orderDetail);
        } else {
            // increment product by 1 if present in cart
            orderDetail.setQuantityOrdered(orderDetail.getQuantityOrdered() + 1);
            orderDetailsDao.save(orderDetail);
        }

        // review updated cart
        response.setViewName("redirect:/order/orderdetail");
        return response;
    }

    // edit an order before checkout
    @PostMapping("/order/deleteProduct")
    public ModelAndView deleteProductFromOrder(@RequestParam Integer productId) {
        ModelAndView response = new ModelAndView("redirect:/order/orderdetail");

        User user = authenticatedUserUtilities.getCurrentUser();
        Order order = orderDao.findOrderInCartStatus(user.getId());

        if (order == null) {
            response.setViewName("redirect:/error");
            return response;
        }

        OrderDetail orderDetail = orderDetailsDao.isProductInCart(order.getId(), productId);
        if (orderDetail != null) {
            orderDetailsDao.delete(orderDetail);
        }

        return response;
    }


    private Integer generateOrderLineNumber(Order order) {
        // Use order.getId() to get the Integer ID of the order
        long count = orderDetailsDao.countByOrder(order.getId());
        return (int) count + 1;
    }


    @GetMapping("/order/checkout")
    public ModelAndView checkout(RedirectAttributes redirectAttributes) {
        ModelAndView response = new ModelAndView();

        // get the logged-in user
        User user = authenticatedUserUtilities.getCurrentUser();
        log.info("User ID: {}", user.getId());

        // query order from the database where the status is 'CART'
        Order order = orderDao.findOrderInCartStatus(user.getId());
        if (order == null) {
            log.error("There is no order with items in the cart to checkout");
        } else {
            // there was an order with items in the cart, so we change the status to COMPLETE
            order.setStatus("COMPLETE");
            orderDao.save(order);
            redirectAttributes.addFlashAttribute("message", "Your order # %d has been placed. Please allow 7-10 business days for delivery.");
}
            response.setViewName("redirect:/order/orderdetail");
            return response;
        }
}