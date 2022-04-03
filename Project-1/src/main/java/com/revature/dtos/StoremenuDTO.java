package com.revature.dtos;

import java.util.Objects;

public class StoremenuDTO {
	
	private int id;
	private String itemname;
	private double amount;
	
	
	public StoremenuDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getItemname() {
		return itemname;
	}


	public void setItemname(String itemname) {
		this.itemname = itemname;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	@Override
	public int hashCode() {
		return Objects.hash(amount, id, itemname);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StoremenuDTO other = (StoremenuDTO) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount) && id == other.id
				&& Objects.equals(itemname, other.itemname);
	}


	@Override
	public String toString() {
		return "StoremenuDTO [id=" + id + ", itemname=" + itemname + ", amount=" + amount + "]";
	}
	
	

}
