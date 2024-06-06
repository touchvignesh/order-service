package com.excoder.orderservice.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Integer id;
    private Integer orderId;
    private Integer customerId;
    private String status;
    private String address;
    private BigDecimal orderTotal;
    private LocalDate createdDate;
}
