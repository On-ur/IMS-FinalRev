package com.qa.ims.persistence.domain;

public class OrderItems {

	// Initialising private fields
	private Long id;
	private Long orderId;
	private Long itemId;

	// Constructor taking in parameters and calling methods to assign given
	// parameters.
	public OrderItems(Long orderId, Long itemId) {
		this.setOrderId(orderId);
		this.setItemId(itemId);
	}

	// Second constructors which assigns the parameters to the fields by calling the
	// methods.
	public OrderItems(Long id, Long orderId, Long itemId) {
		this.setId(id);
		this.setOrderId(orderId);
		this.setItemId(itemId);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	@Override
	public String toString() {
		return "OrderItems (id=" + id + ", orderId=" + orderId + ", itemId=" + itemId + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItems other = (OrderItems) obj;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		return true;
	}

}
