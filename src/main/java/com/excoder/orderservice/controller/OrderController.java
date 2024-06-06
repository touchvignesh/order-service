package com.excoder.orderservice.controller;

import com.excoder.orderservice.component.OrderConverter;
import com.excoder.orderservice.dto.OrderDTO;
import com.excoder.orderservice.model.Order;
import com.excoder.orderservice.service.OrderService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    OrderConverter orderConverter;

    @GetMapping("/list/all")
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/list/id/{id}")
    public Optional<Order> findById(@PathVariable Integer id) {
        return orderService.findById(id);
    }

    @GetMapping("/list/customerid/{customerId}")
    public Optional<Order> findByCustomerId(@PathVariable Integer customerId) {
        return orderService.findByCustomerId(customerId);
    }

    // create an order
    @ResponseStatus(HttpStatus.CREATED) // 201
    @PostMapping("/create")
    public OrderDTO create(@RequestBody OrderDTO orderDTO) {
        return orderService.save(orderDTO);
    }

    // update an order
    @PutMapping("/update")
    public OrderDTO update(@RequestBody OrderDTO orderDTO) {
        return orderService.save(orderDTO);
    }

    // delete an order
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    @DeleteMapping("/id/{id}")
    public void deleteById(@PathVariable Integer id) {
        orderService.deleteById(id);
    }

    // delete an order by customerId
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    @DeleteMapping("/customerid/{customerId}")
    public void deleteByCustomerId(@PathVariable Integer customerId) {
        orderService.deleteByCustomerId(customerId);
    }

    @GetMapping("/list/status/{status}")
    public List<Order> findByStatus(@PathVariable String status) {
        return orderService.findByStatus(status);
    }

    @GetMapping("/list/date-after/{date}")
    public List<Order> findByCreatedDateAfter(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return orderService.findByCreatedDateAfter(date);
    }
}
