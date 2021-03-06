package com.getir.readingisgood.service;

import com.getir.readingisgood.dto.*;
import com.getir.readingisgood.exception.ExceptionTypeEnum;
import com.getir.readingisgood.exception.NotFoundException;
import com.getir.readingisgood.exception.StockNotEnoughException;
import com.getir.readingisgood.mapper.OrderMapper;
import com.getir.readingisgood.model.Book;
import com.getir.readingisgood.model.Order;
import com.getir.readingisgood.model.OrderDetail;
import com.getir.readingisgood.repository.BookRepository;
import com.getir.readingisgood.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author UmutBayram
 */
@Log4j2
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, BookRepository bookRepository) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public OrderInfoResponseDto newOrder(NewOrderRequestDto newOrderRequestDto) {
        List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
        List<Book> books = stockControlAndDecrease(newOrderRequestDto.getBooks(), orderDetails);
        bookRepository.saveAll(books);
        Order order = Order.builder()
                .date(LocalDateTime.now())
                .email(newOrderRequestDto
                        .getEmail()).orderDetails(orderDetails).build();
        order = orderRepository.insert(order);
        log.info("order created. ");
        return OrderMapper.INSTANCE.orderInfoResponseDtoToOrder(order);
    }

    private List<Book> stockControlAndDecrease(List<NewOrderBookRequestDto> newOrderBookRequestDtos, List<OrderDetail> orderDetails) {
        List<Book> newStockWithBooks = new ArrayList<Book>();
        List<Book> books = bookRepository.findByCodeIn(newOrderBookRequestDtos.stream().map(newOrderBookRequestDto -> newOrderBookRequestDto.getBookCode()).collect(Collectors.toList()));
        newOrderBookRequestDtos.forEach(newOrderBookRequestDto -> {
            Book book = books.stream().filter(tempBook -> tempBook.getCode().equals(newOrderBookRequestDto.getBookCode()))
                    .findFirst().orElseThrow(() -> new NotFoundException(ExceptionTypeEnum.NOT_FOUND_EXCEPTION.getCode(), "Data not found : " + newOrderBookRequestDto.getBookCode()));
            if (book.getStock() - newOrderBookRequestDto.getCount() < 0) {
                throw new StockNotEnoughException(ExceptionTypeEnum.STOCK_NOT_ENOUGH.getCode(), "Stock not enough : " + newOrderBookRequestDto.getBookCode());
            }
            book.setStock(book.getStock() - newOrderBookRequestDto.getCount());
            orderDetails.add(OrderDetail.builder().bookCode(book.getCode()).price(book.getPrice()).count(newOrderBookRequestDto.getCount()).build());
            newStockWithBooks.add(book);
        });
        return newStockWithBooks;
    }

    @Override
    public OrderInfoResponseDto orderInfoById(String orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        Order order = orderOptional.orElseThrow(() -> new NotFoundException(ExceptionTypeEnum.NOT_FOUND_EXCEPTION.getCode(), "Data not found : " + orderId));
        return OrderMapper.INSTANCE.orderInfoResponseDtoToOrder(order);
    }

    @Override
    public OrderFilterResponseDto filter(OrderFilterRequestDto orderFilterRequestDto) {
        Page<Order> orders = orderRepository.findByEmailAndDateBetween(
                orderFilterRequestDto.getEmail(),
                orderFilterRequestDto.getStartDate(),
                orderFilterRequestDto.getFinishDate(),
                PageRequest.of(orderFilterRequestDto.getPage(), orderFilterRequestDto.getSize(),
                        orderFilterRequestDto.getSortDir().equals("DESC") ? Sort.by(orderFilterRequestDto.getSortField()).descending() : Sort.by(orderFilterRequestDto.getSortField()).ascending()));
        OrderFilterResponseDto orderFilterResponseDto = new OrderFilterResponseDto();
        orderFilterResponseDto.setTotalElements(orders.getTotalElements());
        orderFilterResponseDto.setTotalPages(orders.getTotalPages());
        orderFilterResponseDto.setPage(orderFilterRequestDto.getPage());
        orderFilterResponseDto.setSize(orderFilterRequestDto.getSize());
        orderFilterResponseDto.setOrders(OrderMapper.INSTANCE.orderInfoResponseDtosToOrders(orders.getContent()));
        log.info("get customer order with filter result. ");
        return orderFilterResponseDto;
    }

    @Override
    public MonthlyStatisticsResponseDto monthlyStatistics(MonthlyStatisticsRequestDto monthlyStatisticsRequestDto) {

        Integer year = LocalDate.now().getYear();

        List<Order> orders = orderRepository.findByEmailAndDateBetween(monthlyStatisticsRequestDto.getEmail(),
                LocalDate.of(year, 1, 1),
                LocalDate.of(year, 12, 31));


        List<MonthlyStatisticsDetailsResponseDto> monthlyStatisticsDetailsResponseDtos = generateMonthlyStatisticsDetailsResponseDtos(orders);
        MonthlyStatisticsResponseDto monthlyStatisticsResponseDto =
                MonthlyStatisticsResponseDto.builder()
                        .reports(monthlyStatisticsDetailsResponseDtos.stream().sorted(Comparator.comparing(MonthlyStatisticsDetailsResponseDto::getMonthNumber))
                                .collect(Collectors.toList())).build();
        log.info("get customer monthly statistics result. ");
        return monthlyStatisticsResponseDto;
    }

    private List<MonthlyStatisticsDetailsResponseDto> generateMonthlyStatisticsDetailsResponseDtos(List<Order> orders) {
        List<MonthlyStatisticsDetailsResponseDto> monthlyStatisticsDetailsResponseDtos = new ArrayList<MonthlyStatisticsDetailsResponseDto>();
        orders.stream().forEach(order -> {
            LocalDateTime orderDate = order.getDate();
            MonthlyStatisticsDetailsResponseDto monthlyStatisticsDetailsResponseDto = monthlyStatisticsDetailsResponseDtos
                    .stream()
                    .filter(monthlyStatisticsDetailsResponseDtoTemp -> monthlyStatisticsDetailsResponseDtoTemp.getMonthNumber().equals(orderDate.getMonth().getValue()))
                    .findFirst()
                    .orElse(
                            MonthlyStatisticsDetailsResponseDto.builder()
                                    .month(orderDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.US))
                                    .monthNumber(orderDate.getMonth().getValue())
                                    .totalPrice(new BigDecimal(0))
                                    .bookCount(0L)
                                    .orderCount(0L)
                                    .build());
            monthlyStatisticsDetailsResponseDto.setOrderCount(monthlyStatisticsDetailsResponseDto.getOrderCount() + 1);

            order.getOrderDetails().stream().forEach(orderDetail -> {
                monthlyStatisticsDetailsResponseDto.setBookCount(monthlyStatisticsDetailsResponseDto.getBookCount() + orderDetail.getCount());
                monthlyStatisticsDetailsResponseDto.setTotalPrice(orderDetail.getPrice().multiply(BigDecimal.valueOf(orderDetail.getCount())).add(monthlyStatisticsDetailsResponseDto.getTotalPrice()));
            });
            if (!monthlyStatisticsDetailsResponseDtos.contains(monthlyStatisticsDetailsResponseDto)) {
                monthlyStatisticsDetailsResponseDtos.add(monthlyStatisticsDetailsResponseDto);
            }
        });
        return monthlyStatisticsDetailsResponseDtos;
    }

    @Override
    public CustomerOrdersResponseDto orders(CustomerOrdersRequestDto customerOrdersRequestDto) {
        Page<Order> orders = orderRepository.findByEmail(customerOrdersRequestDto.getEmail(), PageRequest.of(customerOrdersRequestDto.getPage(), customerOrdersRequestDto.getSize(),
                customerOrdersRequestDto.getSortDir().equals("DESC") ? Sort.by(customerOrdersRequestDto.getSortField()).descending() : Sort.by(customerOrdersRequestDto.getSortField()).ascending()));
        CustomerOrdersResponseDto customerOrdersResponseDto = new CustomerOrdersResponseDto();
        customerOrdersResponseDto.setTotalElements(orders.getTotalElements());
        customerOrdersResponseDto.setTotalPages(orders.getTotalPages());
        customerOrdersResponseDto.setPage(customerOrdersRequestDto.getPage());
        customerOrdersResponseDto.setSize(customerOrdersRequestDto.getSize());
        customerOrdersResponseDto.setOrders(OrderMapper.INSTANCE.orderInfoResponseDtosToOrders(orders.getContent()));
        log.info("get customer order result. ");
        return customerOrdersResponseDto;
    }
}
