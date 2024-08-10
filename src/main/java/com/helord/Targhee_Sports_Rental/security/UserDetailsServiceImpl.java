package com.helord.Targhee_Sports_Rental.security;

import com.helord.Targhee_Sports_Rental.database.dao.UserDAO;
import com.helord.Targhee_Sports_Rental.database.dao.UserRoleDAO;
import com.helord.Targhee_Sports_Rental.database.entity.User;
import com.helord.Targhee_Sports_Rental.database.entity.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


// purpose is to go to the database, get user,
// send to spring security
@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

        @Autowired
        private UserDAO userDao;

        @Autowired
        private UserRoleDAO userRoleDao;

        @Override
        public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
            // fetch the user from the database - username is what the person entered into the username field on the login form
            // this User object is in the imports section
            User user = userDao.findByEmailIgnoreCase(username);

            // if the user is null then what ever the person entered on the login form does not exist in the database
            // automatically throw an exception
            if (user == null) {
                throw new UsernameNotFoundException("Username '" + username + "' not found in database");
            }

            // check account status
            boolean accountIsEnabled = true;
            boolean accountNonExpired = true;
            boolean credentialsNonExpired = true;
            boolean accountNonLocked = true;

            // im using the user object from the database to get the user roles
            List<UserRole> userRoles = userRoleDao.findByUserId(user.getId());
            // passing the user roles to create the granted authorities

            Collection<? extends GrantedAuthority> authorities = buildGrantAuthorities(userRoles);

            // this User object is part of Spring Security
            // because both objets are named User, we have to use the full path to the object
            UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                    user.getEmail(),  // this paramter is the username, in our case the user from the database
                    user.getPassword(), // this is the users encrypted password from the database
                    accountIsEnabled, // is this account enabled, if false, then spring security will deny access
                    accountNonExpired,
                    credentialsNonExpired,
                    accountNonLocked,
                    authorities); // this is the list of security roles that the user is Authorized to have

            return userDetails;
        }

        // boilerplate - looping through the user roles and creating GrantedAuthorities for Spring security
        // the GrantedAuthorities are what Spring Security uses to determine if a user has authority
        private Collection<? extends GrantedAuthority> buildGrantAuthorities (List < UserRole > userRoles) {
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

            for (UserRole role : userRoles) {
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            }

            return authorities;
        }

    }