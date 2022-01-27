package com.getir.readingisgood.mapper;

import com.getir.readingisgood.dto.OrderDetailsInfoResponseDto;
import com.getir.readingisgood.dto.OrderInfoResponseDto;
import com.getir.readingisgood.model.Order;
import com.getir.readingisgood.model.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author UmutBayram
 */
@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderInfoResponseDto orderInfoResponseDtoToOrder(Order order);

    OrderDetailsInfoResponseDto orderDetailsInfoResponseDtoToOrderDetail(OrderDetail orderDetail);

    List<OrderInfoResponseDto> orderInfoResponseDtosToOrders(List<Order> orders);


}
