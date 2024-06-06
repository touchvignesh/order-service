package com.excoder.orderservice.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("order")
public class Order {

    @Id
    private Integer id;

    @Indexed(unique = true)
    @Field("order_id")
    private Integer orderId;

    @Indexed
    @Field("customer_id")
    private Integer customerId;

    @Indexed
    private String status;

    private String address;

    @Field("order_total")
    private BigDecimal orderTotal;

    @Field("created_date")
    private LocalDate createdDate;
}
