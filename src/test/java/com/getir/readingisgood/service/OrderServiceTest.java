package com.getir.readingisgood.service;

import com.getir.readingisgood.dto.*;
import com.getir.readingisgood.model.Book;
import com.getir.readingisgood.model.Order;
import com.getir.readingisgood.model.OrderDetail;
import com.getir.readingisgood.repository.BookRepository;
import com.getir.readingisgood.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

/**
 * @author UmutBayram
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class OrderServiceTest {

    private OrderService orderService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private BookRepository bookRepository;

    private Order order;
    private OrderFilterRequestDto orderFilterRequestDto;
    private List<Order> orders;
    private CustomerOrdersRequestDto customerOrdersRequestDto;
    private MonthlyStatisticsRequestDto monthlyStatisticsRequestDto;
    private NewOrderRequestDto newOrderRequestDto;
    private List<Book> books;


    @BeforeEach
    void setUp() {
        orderService = new OrderServiceImpl(orderRepository, bookRepository);

        List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
        orderDetails.add(OrderDetail.builder().bookCode("book1").count(1).price(new BigDecimal(1)).build());
        orderDetails.add(OrderDetail.builder().bookCode("book2").count(1).price(new BigDecimal(1)).build());
        order = Order.builder()
                .email("test@test.com")
                .date(LocalDateTime.now())
                .orderDetails(orderDetails)
                .build();

        orderFilterRequestDto = OrderFilterRequestDto.builder()
                .email("test@test.com")
                .startDate(LocalDate.now())
                .finishDate(LocalDate.now())
                .page(1)
                .size(1)
                .build();
        orders = new ArrayList<Order>();
        orders.add(order);

        customerOrdersRequestDto = CustomerOrdersRequestDto.builder()
                .email("test@test.com")
                .page(1)
                .size(1)
                .build();

        monthlyStatisticsRequestDto = MonthlyStatisticsRequestDto.builder()
                .email("test@test.com").build();

        List<NewOrderBookRequestDto> newOrderBookRequestDtos = new ArrayList<NewOrderBookRequestDto>();
        newOrderBookRequestDtos.add(NewOrderBookRequestDto.builder().bookCode("test1").count(1).build());
        newOrderBookRequestDtos.add(NewOrderBookRequestDto.builder().bookCode("test2").count(1).build());
        newOrderBookRequestDtos.add(NewOrderBookRequestDto.builder().bookCode("test3").count(1).build());
        newOrderRequestDto = NewOrderRequestDto.builder()
                .email("test@test.com")
                .books(newOrderBookRequestDtos)
                .build();

        books = new ArrayList<Book>();
        books.add(Book.builder().code("test1").price(new BigDecimal(1)).stock(1L).build());
        books.add(Book.builder().code("test2").price(new BigDecimal(1)).stock(1L).build());
        books.add(Book.builder().code("test3").price(new BigDecimal(1)).stock(1L).build());
    }

    @Test
    void testNewOrder() {
        Mockito.when(bookRepository.saveAll(any(ArrayList.class))).thenReturn(orders);
        Mockito.when(orderRepository.insert(any(Order.class))).thenReturn(order);
        Mockito.when(bookRepository.findByCodeIn(any(ArrayList.class))).thenReturn(books);

        OrderInfoResponseDto orderInfoResponseDto = orderService.newOrder(newOrderRequestDto);
    }

    @Test
    void testOrderInfoById() {
        Mockito.when(orderRepository.findById(anyString())).thenReturn(Optional.of(order));
        OrderInfoResponseDto orderInfoResponseDto = orderService.orderInfoById("orderId");
        assertNotNull(orderInfoResponseDto);
        assertEquals(orderInfoResponseDto.getEmail(), order.getEmail());
    }

    @Test
    void testFilter() {
        Mockito.when(orderRepository.findById(anyString())).thenReturn(Optional.of(order));

        Page orderPage = new PageImpl(orders);

        Mockito.when(orderRepository.findByEmailAndDateBetween(
                anyString(),
                any(LocalDate.class),
                any(LocalDate.class),
                any(Pageable.class))).thenReturn(orderPage);

        OrderFilterResponseDto orderFilterResponseDto = orderService.filter(orderFilterRequestDto);
        assertNotNull(orderFilterResponseDto);
        assertEquals(orderFilterResponseDto.getSize(), 1);
    }

    @Test
    void testMonthlyStatistics() {
        Mockito.when(orderRepository.findByEmailAndDateBetween(
                anyString(),
                any(LocalDate.class),
                any(LocalDate.class))).thenReturn(orders);
        MonthlyStatisticsResponseDto monthlyStatisticsResponseDto = orderService.monthlyStatistics(monthlyStatisticsRequestDto);
        assertNotNull(monthlyStatisticsResponseDto);
        assertEquals(monthlyStatisticsResponseDto.getReports().size(), 1);
    }

    @Test
    void testOrders() {
        Page orderPage = new PageImpl(orders);
        Mockito.when(orderRepository.findByEmail(anyString(), any(Pageable.class))).thenReturn(orderPage);
        CustomerOrdersResponseDto customerOrdersResponseDto = orderService.orders(customerOrdersRequestDto);
        assertNotNull(customerOrdersResponseDto);
        assertEquals(customerOrdersResponseDto.getSize(), 1);
    }
}