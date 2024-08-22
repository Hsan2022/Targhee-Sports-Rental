package com.helord.Targhee_Sports_Rental.form;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateProductFormBean {


//match form bean variables

    private Integer Id;

    @NotEmpty(message = "Name is required")
    private String name;

    @NotEmpty(message = "Description is required")
    private String description;

    @NotNull(message = "Price is required")
    private Double price;

    @NotEmpty(message = "ImageURL is required")
    private String imageURL;

}
