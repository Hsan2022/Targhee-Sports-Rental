package com.helord.Targhee_Sports_Rental.form;

import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;

@Getter
@Setter
public class CreateOrderFormBean {

    private Integer id;
    private Integer userid;
    private Timestamp orderdate;
    private Timestamp requireddate;
    private String status;

}
