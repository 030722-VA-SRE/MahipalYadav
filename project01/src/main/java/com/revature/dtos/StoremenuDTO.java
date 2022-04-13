package com.revature.dtos;
import java.util.Objects;

import com.revature.models.Storemenu;

public class StoremenuDTO {
	
	private int id;
	private String itemname;
	private double amount;
	private UserDTO owner;
	
	
	public StoremenuDTO()
	{
		super();
		// TODO Auto-generated constructor stub
	}


	public StoremenuDTO(Storemenu storemenu) {
		super();
		this.id = storemenu.getId();
		this.itemname = storemenu.getItemname();
		this.amount = storemenu.getAmount();
		this.owner = new UserDTO(storemenu.getOwner());
		
		
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


	public UserDTO getOwner() {
		return owner;
	}


	public void setOwner(UserDTO owner) {
		this.owner = owner;
	}


	@Override
	public int hashCode() {
		return Objects.hash(amount, id, itemname, owner);
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
				&& Objects.equals(itemname, other.itemname) && Objects.equals(owner, other.owner);
	}


	@Override
	public String toString() {
		return "StoremenuDTO [id=" + id + ", itemname=" + itemname + ", amount=" + amount + ", owner=" + owner + "]";
	}


	
	
	

}
