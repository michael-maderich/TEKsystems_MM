package com.littlestore.michael.maderich.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.littlestore.michael.maderich.entity.Order;
import com.littlestore.michael.maderich.entity.OrderDetail;
import com.littlestore.michael.maderich.entity.OrderDetailId;
import com.littlestore.michael.maderich.repository.OrderDetailRepository;

@Service
public class OrderDetailService {

	@Autowired
	private OrderDetailRepository orderDetailRepository;

    
    public OrderDetailService(OrderDetailRepository orderDetailRepository) {
    	this.orderDetailRepository = orderDetailRepository;
    }
	
    
    @Transactional(rollbackFor = Exception.class)
    public void save(OrderDetail orderDetail) {	// Create new/Update Order Detail line item
        orderDetailRepository.save(orderDetail);
    }
     
/*    public List<OrderDetail> listAll() {
        return (List<OrderDetail>) orderDetailRepository.findAll();
    }*/

    public List<OrderDetail> listAllInOrder(Order order) {
    	return orderDetailRepository.findByOrderOrderByLineNumberAsc(order);
    }
    
    public OrderDetail get(OrderDetailId id) {
        return orderDetailRepository.findById(id).get();
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(OrderDetailId id) {
        orderDetailRepository.deleteById(id);
    }
}