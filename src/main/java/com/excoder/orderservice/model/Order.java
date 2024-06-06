package com.excoder.orderservice.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("order")
public class Order {

    @MongoId(FieldType.OBJECT_ID)
    private Integer id;

    private Integer customerId;

    @Indexed
    private String status;

    @Indexed
    private String address;

    private BigDecimal orderTotal;
    private LocalDate createdDate;
}
