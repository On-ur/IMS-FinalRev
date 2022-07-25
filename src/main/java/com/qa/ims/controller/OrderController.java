package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.dao.OrderItemsDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

/**
* Takes in order details for CRUD functionality
*
*/

public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private OrderItemsDAO orderitemsDAO;
	private CustomerDAO customerDAO;
	private Utils utils;

	public OrderController(OrderDAO orderDAO, OrderItemsDAO orderitemsDAO, CustomerDAO customerDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.orderitemsDAO = orderitemsDAO;
		this.customerDAO = customerDAO;
		this.utils = utils;
	}
	/**
	 * Reads all orders to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		List<Customer> customers = customerDAO.readAll();
		 LOGGER.info(" Order ID  ~   Value  ~         Customer Name         ");
		 LOGGER.info(" ");
		 
for (Order order : orders) {
for (Customer customer : customers) {
if (order.getCustomerId() == customer.getId() ) {
LOGGER.info(order.getId()+"       "+order.getValue()+"      "+ customer.getFirstName()+" "+customer.getSurname());
		}
		return orders;
}
}
/**
* Creates an order by taking in user input: customer id
  */
return orders;
	}

	@Override
	public Order create() {
		LOGGER.info("Please enter customer ID");
		Long customerId = utils.getLong();
		Long orderId = order.getId();
		LOGGER.info("what is the item ID?");
		Long itemID = utils.getLong();
		Order order = orderDAO.create(new Order(customerId));
		LOGGER.info("Item created successfully");
		return order;
	}
	/**
	 * Updates an existing order by adding or deleting an item from the order
	 */
	@Override
	public Order update() {
		LOGGER.info("Please enter the id of the Order that you would like to update");
		Long id = utils.getLong();
		do {
			LOGGER.info("Would you like to add, delete or Exit?");
			LOGGER.info("ADD"+ "-"+ "add an item to the order");
			LOGGER.info("DEL"+ "-"+"delete an item from the order" );
			LOGGER.info("EXIT");
			
		String option = utils.getString();
		
		Long orderId;
		switch(option) {
		case "ADD":
			LOGGER.info("what is the item ID?");
			Long addItemId = utils.getLong();
			orderitemsDAO.add(orderId, addItemId);
			LOGGER.info("added item(s) to the order successfully");
			break;
		case "DEL":
			LOGGER.info("what is the item ID?");
			Long deleteItemId = utils.getLong();
			orderitemsDAO.delete(orderId, deleteItemId);
			LOGGER.info("Item(s) deleted from the order successfully");
			break;
		case "EXIT":
			boolean exit;
			exit = true;
			
		}
			
			
		}
		
	

	/**
	 * Deletes an existing order by the id of the order
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order that you would like to delete");
		Long id = utils.getLong();
		return orderDAO.delete(id);
	}

}
