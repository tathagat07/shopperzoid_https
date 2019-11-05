package com.stackroute.service;

import com.stackroute.domain.Order;
import com.stackroute.exception.OrderAlreadyExistsException;
import com.stackroute.exception.OrderDoesNotExistException;
import com.stackroute.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrderDetails() {
        List<Order> fetchedOrderList;
        fetchedOrderList = orderRepository.findAll();
        return fetchedOrderList;
    }

    @Override
    public Order getOrderDetailsById(String orderId) throws OrderDoesNotExistException {
        Order fetchedOrder;
        if(!orderRepository.findById(orderId).isPresent()){
            throw new OrderDoesNotExistException("Order Does Not Exist");
        }
        else {
            fetchedOrder = orderRepository.findById(orderId).get();
        }
        return fetchedOrder;
    }

    @Override
    public Order saveOrderDetails(Order order) throws OrderAlreadyExistsException {
        Order savedOrder = new Order();
        if(orderRepository.findById(order.getOrderId()).isPresent()){
            throw new OrderAlreadyExistsException("Order Already Exists");
        }
        else {
            savedOrder.setOrderId(order.getOrderId());
            savedOrder.setBuyerEmail(order.getBuyerEmail());
            savedOrder.setRating(order.getRating());
            savedOrder.setProducts(order.getProducts());

//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
//            savedOrder.setTimestamp(LocalDateTime.now().format(formatter));
//            savedOrder.setTimestamp(LocalDateTime.now());
            orderRepository.save(savedOrder);
        }
        return savedOrder;
    }

    @Override
    public boolean deleteOrder(String orderId) throws OrderDoesNotExistException {
        if(!orderRepository.findById(orderId).isPresent()){
            throw new OrderDoesNotExistException("Order Does Not Exist");
        }
        else {
            orderRepository.deleteById(orderId);
        }
        return true;
    }

    @Override
    public Order updateOrder(Order order) throws OrderDoesNotExistException {
        Order updatedOrder = new Order();
        if(!orderRepository.findById(order.getOrderId()).isPresent()){
            throw new OrderDoesNotExistException("Order Does Not Exist");
        }
        else {
            updatedOrder.setOrderId(order.getOrderId());
            updatedOrder.setBuyerEmail(order.getBuyerEmail());
            updatedOrder.setRating(order.getRating());
            updatedOrder.setProducts(order.getProducts());

//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
//            updatedOrder.setTimestamp(LocalDateTime.now().format(formatter));
//            updatedOrder.setTimestamp(LocalDateTime.now());
            orderRepository.save(updatedOrder);
        }
        return updatedOrder;
    }

    @Override
    public List<Order> getAllOrderDetailsByBuyerEmail(String buyerEmail) {
        List<Order> fetchedOrderListByBuyerEmail;
        fetchedOrderListByBuyerEmail = orderRepository.findByBuyerEmail(buyerEmail);
        return fetchedOrderListByBuyerEmail;
    }

}
