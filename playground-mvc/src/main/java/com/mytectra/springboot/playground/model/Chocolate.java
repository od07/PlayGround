package com.mytectra.springboot.playground.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

public class Chocolate {
	
	@NotNull
	private String name;
	
	@NotNull
	private String brand;
	
	@NotNull
	@Range(min = 1 , max = 10)
	private int price;
	
	public Chocolate() {
	}

	public Chocolate(String name, String brand, int price) {
		//super();
		this.name = name;
		this.brand = brand;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Chocolate [name=");
		builder.append(name);
		builder.append(", brand=");
		builder.append(brand);
		builder.append(", price=");
		builder.append(price);
		builder.append("]");
		return builder.toString();
	}
	
	

}
