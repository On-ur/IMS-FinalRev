package com.qa.ims.persistence.domain;

public class Item {

	// Initialising private fields
	private Long id;
	private String itemname;
	private Double value;

	// Constructor taking in parameters and calling methods to assign given
	// parameters.
	public Item(String name, Double value) {
		this.setName(itemname);
		this.setValue(value);
	}

	// Second constructors which assigns the parameters to the fields by calling the
	// methods.
	public Item(Long id, String itemname, Double value) {
			this.setId(id);
			this.setName(itemname);
			this.setValue(value);
		}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return itemname;
	}

	public void setName(String itemname) {
		this.itemname = itemname;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value2) {
		this.value = value2;
	}

	@Override
	public String toString() {
		return "id: " + id + " name: " + itemname + " value: " + value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemname == null) ? 0 : itemname.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Item other = (Item) obj;
		if (getName() == null) {
			if (other.getName() != null)
				return false;
		} else if (!getName().equals(other.getName()))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
}

