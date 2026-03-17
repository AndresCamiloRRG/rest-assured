package com.globant.automation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDTO {

    private String id;
    private String petId;
    private int quantity;
    private Date shipDate;
    private String status;
    private boolean complete;

}
