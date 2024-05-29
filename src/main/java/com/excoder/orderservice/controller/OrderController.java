package com.excoder.orderservice.controller;

import com.excoder.orderservice.model.Order;
import com.excoder.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService OrderService;

    @GetMapping("/list/all")
    public List<Order> findAll() {
        return OrderService.findAll();
    }

    @GetMapping("/list/id/{id}")
    public Optional<Order> findById(@PathVariable Integer id) {
        return OrderService.findById(id);
    }

    @GetMapping("/list/customerid/{customerId}")
    public Optional<Order> findByCustomerId(@PathVariable Integer customerId) {
        return OrderService.findByCustomerId(customerId);
    }

    // create an order
    @ResponseStatus(HttpStatus.CREATED) // 201
    @PostMapping("/create")
    public Order create(@RequestBody Order order) {
        return OrderService.save(order);
    }

    // update an order
    @PutMapping("/update")
    public Order update(@RequestBody Order order) {
        return OrderService.save(order);
    }

    // delete an order
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    @DeleteMapping("/id/{id}")
    public void deleteById(@PathVariable Integer id) {
        OrderService.deleteById(id);
    }

    // delete an order by customerId
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    @DeleteMapping("/customerid/{customerId}")
    public void deleteByCustomerId(@PathVariable Integer customerId) {
        OrderService.deleteByCustomerId(customerId);
    }

    @GetMapping("/list/status/{status}")
    public List<Order> findByStatus(@PathVariable String status) {
        return OrderService.findByStatus(status);
    }

    @GetMapping("/list/date-after/{date}")
    public List<Order> findByCreatedDateAfter(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return OrderService.findByCreatedDateAfter(date);
    }

}
