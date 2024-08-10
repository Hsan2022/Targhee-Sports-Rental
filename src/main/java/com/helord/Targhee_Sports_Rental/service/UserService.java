package com.helord.Targhee_Sports_Rental.service;

import com.helord.Targhee_Sports_Rental.database.dao.UserDAO;
import com.helord.Targhee_Sports_Rental.database.dao.UserRoleDAO;
import com.helord.Targhee_Sports_Rental.database.entity.User;
import com.helord.Targhee_Sports_Rental.database.entity.UserRole;
import com.helord.Targhee_Sports_Rental.form.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private UserRoleDAO userRoleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;


    // this method creates new user accounts using a form
    public User createUser(CreateAccountFormBean form) {

        User user = new User(); // new instance/obj of User

        user.setEmail(form.getEmail()); // set email from form

        // take plain text password from form and encrypt it
        String encryptedPassword = passwordEncoder.encode(form.getPassword());

        user.setPassword(encryptedPassword);
        user.setDateCreated(new Date());
        userDao.save(user); // save user to the db
        UserRole userRole = new UserRole(); // create a user role for the user
        userRole.setRoleName("USER");
        userRole.setUserId(user.getId());
        userRoleDao.save(userRole); // save user role to the db

        return user;
    }


}
