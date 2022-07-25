package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAOTest {
	
	private final ItemDAO DAO = new ItemDAO();
	
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
		final Item created = new Item(6L, "Cream Turtle Neck", 19.99);
		assertEquals(created, DAO.create(created));
	}
	
	@Test
	public void testReadAll() {
		List<Item> expected = new ArrayList<>();
		expected.add(new Item(1L, "Red Fedora", 10.49));
		expected.add(new Item(2L, "White Baseball Cap", 15.99));
		expected.add(new Item(3L, "Black Letter Jacket", 99.99));
		expected.add(new Item(4L, "Black V Neck T Shirt", 9.99));
		expected.add(new Item(5L, "Yellow Crew Neck T Shirt", 12.45));
		System.out.println(DAO.readAll());
		assertEquals(expected, DAO.readAll());
	}
	
	@Test
	public void testReadLatest() {
		assertEquals(new Item(5L, "Yellow Crew Neck T Shirt", 12.45), DAO.readLatest());
	}
	
	@Test
	public void testRead() {
		final long ID = 1L;
		assertEquals(new Item(ID, "Red Fedora" ,10.49), DAO.readItem(ID));
	}
	
	@Test
	public void testUpdate() {
		final Item updated = new Item(1L, "Black Beanie", 9.99);
		assertEquals(updated, DAO.update(updated));

	}
	
	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(5));
	}

	
}
