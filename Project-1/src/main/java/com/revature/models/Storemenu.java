package com.revature.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "storemenu")
public class Storemenu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String itemname;
	@Column(nullable = false)
	private double amount;
	@ManyToOne
	@JoinColumn(nullable = false, name = "owner_id")
	private User owner;

	public Storemenu() {
		super();

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

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
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
		Storemenu other = (Storemenu) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount) && id == other.id
				&& Objects.equals(itemname, other.itemname) && Objects.equals(owner, other.owner);
	}

	@Override
	public String toString() {
		return "Storemenu [id=" + id + ", itemname=" + itemname + ", amount=" + amount + ", storewoner=" + owner
				+ "]";
	}

}
