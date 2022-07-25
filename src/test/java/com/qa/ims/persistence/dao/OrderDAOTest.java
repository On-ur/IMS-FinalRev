package com.qa.ims.persistence.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTest {

	private final OrderDAO DAO = new OrderDAO();

	@BeforeClass
	public static void init() {
		DBUtils.connect("root", "ochroot256");
	}

	@Before
	public void setup() {
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		final Order created = new Order(1L);
		Order x = DAO.create(created);
		assertEquals(new Order(3L, 1L, 0.0) , x);
	}
	
	@Test
	public void testReadAll() {
		List<Order> expected = new ArrayList<>();
		expected.add(new Order(1L, 1L, 0.0));
		expected.add(new Order(2L, 3L, 0.0));
		assertEquals(expected, DAO.readAll());
	}
	
	@Test
	public void testReadLatest() {
		assertEquals(new Order(2L, 3L, 0.0), DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		assertEquals(new Order(1L, 1L, 0.0), DAO.readOrder(ID));
	}

	@Test
	public void testUpdate() {
		final Order x = new Order(1L, 1L, 0.0);
		Order result = DAO.update(x);
		assertEquals(new Order(1L, 1L, 12.49), result);
		Double cost = 12.49;
		assertEquals(cost, result.getValue());
	}

	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(2));
	}

}
