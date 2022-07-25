package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.OrderItems;
import com.qa.ims.utils.DBUtils;

public class OrderItemsDAOTest {
	
	private final OrderItemsDAO DAO = new OrderItemsDAO();

	@BeforeClass
	public static void init() {
		DBUtils.connect("root", "ochroot256");
	}

	@Before
	public void setup() {
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testReadLatest() {
		assertEquals(new OrderItems(2L, 2L, 1L), DAO.readLatest());
	}
	
	@Test
	public void testReadAll() {
		List<Orders_Items> expected = new ArrayList<>();
		expected.add(new OrderItems(1L, 1L, 1L));
		expected.add(new OrderItems(2L, 2L, 1L));
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testAdd() {
		final OrderItems created = new OrderItems(3L, 1L, 4L);
		OrderItems results = DAO.add(created.getOrderId(), created.getItemId());
		assertEquals(created, results);
		assertEquals(created.getId(), results.getId());
		System.out.println(results.toString());
	}
	
	@Test
	public void testDelete() {
		final OrderItems created = new OrderItems(2L, 1L);
		assertEquals(1, DAO.delete(created.getOrderId(), created.getItemId()));
	}
	
	
}
