package com.qa.ims.persistence.domain;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class OrderItemsTest {

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(OrderItems.class).verify();
	}

}
