package com.helord.Targhee_Sports_Rental.database.dao;

import com.helord.Targhee_Sports_Rental.database.entity.UserRole;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface UserRoleDAO extends JpaRepository<UserRole, Integer> {

    // used for granted authority query
    List<UserRole> findByUserId(Integer userId);

}
