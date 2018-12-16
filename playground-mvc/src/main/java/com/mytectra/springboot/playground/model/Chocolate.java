package com.mytectra.springboot.playground.model;

public class Chocolate {
	
	private String name;
	
	private String brand;
	
	private int price;
	
	

	public Chocolate(String name, String brand, int price) {
		super();
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
