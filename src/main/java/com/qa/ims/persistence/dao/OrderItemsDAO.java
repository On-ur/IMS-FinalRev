package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.OrderItems;
import com.qa.ims.utils.DBUtils;

public class OrderItemsDAO {
	public static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Assigns all orderitems values attained from query and creates an object
	 * 
	 * @param result - results from the query
	 * 
	 * @return A new orders_items object
	 */
	public OrderItems modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		Long orderId = resultSet.getLong("order_id");
		Long itemId = resultSet.getLong("item_id");
		return new OrderItems(id, orderId, itemId);
	}

	/**
	 * Reads the latest item added to order from the database
	 * 
	 * @return orders_items object
	 */
	public OrderItems readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orderitems ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Reads all orders from the database
	 * 
	 * @return A list of orders
	 */
	public List<OrderItems> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orderitems");) {
			List<OrderItems> oi = new ArrayList<>();
			while (resultSet.next()) {
				oi.add(modelFromResultSet(resultSet));
			}
			return oi;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	/**
	 * Creates a new tuple which adds an item to an order in the database
	 * 
	 * @param orders_items - takes in a orders_items object. id will be ignored
	 */
	public OrderItems add(Long orderID, Long itemID) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate(
					"INSERT INTO orderitems(order_id, item_id) VALUES('" + orderID + "','" + itemID + "')");
			LOGGER.info(readLatest());
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Removes a item from the order in the database
	 * 
	 * @param orders_items - takes in a orders_items object. id will be ignored
	 */
	public int delete(Long orderID, Long itemID) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			return statement.executeUpdate(
					"DELETE FROM orders_items WHERE id IN (SELECT id FROM (SELECT id FROM orders_items WHERE order_id = '"
							+ orderID + "' AND item_id = '" + itemID + "' LIMIT 1) AS a)");
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

}
