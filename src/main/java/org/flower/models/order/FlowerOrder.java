package org.flower.models.order;

import org.flower.entities.Order;
import org.flower.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlowerOrder {

    @Autowired
    private OrderRepository repository;
    public List<Order> getAllOrders(){
        return repository.findAll();
    }

}
