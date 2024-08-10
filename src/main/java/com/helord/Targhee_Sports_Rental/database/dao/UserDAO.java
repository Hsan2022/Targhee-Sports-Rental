package com.helord.Targhee_Sports_Rental.database.dao;

import com.helord.Targhee_Sports_Rental.database.entity.User;
import org.springframework.data.jpa.repository.*;

public interface UserDAO extends JpaRepository<User, Long> {

    // this method is called in userDetailService
    User findByEmailIgnoreCase(String email);
}
