package com.getir.readingisgood.mapper;

import com.getir.readingisgood.dto.OrderInfoResponseDto;
import com.getir.readingisgood.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderInfoResponseDto orderInfoResponseDtoToOrder(Order order);

    List<OrderInfoResponseDto> orderInfoResponseDtosToOrders(List<Order> orders);


}
