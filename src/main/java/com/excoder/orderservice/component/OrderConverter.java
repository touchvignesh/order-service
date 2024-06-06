package com.excoder.orderservice.component;

import com.excoder.orderservice.dto.OrderDTO;
import com.excoder.orderservice.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter {
    public OrderDTO convertOrderEntityToDto(Order order) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(order, OrderDTO.class);
    }

    public Order convertOrderDtoToEntity(OrderDTO orderDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(orderDTO, Order.class);
    }
}
