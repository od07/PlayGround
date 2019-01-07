package com.mytectra.springboot.playground.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Range;

@XmlRootElement
public class Chocolate {
	
	@NotNull(message = "Chocolate name cannot be null")
	@Size(min = 3)
	private String name;
	
	@NotNull(message = "Chocolate name cannot be null")
	@Size(min = 3)
	private String brand;
	
	@NotNull
	@Range(min = 1 , max = 10 ,message = "Out of range price")
	private int price;
	
	@NotNull
	private Date expiryDate;
	
	public Chocolate() {
	}
	
	public Chocolate(String name, String brand, int price) {
		//super();
		this.name = name;
		this.brand = brand;
		this.price = price;
	}

	public Chocolate(String name, String brand, int price , Date date) {
		//super();
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.expiryDate = date;
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
	
	

	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
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
	
	
	public String toCSV() {
		StringBuilder builder = new StringBuilder();
		builder.append(name);
		builder.append(",");
		builder.append(brand);
		builder.append(",");
		builder.append(price);
		builder.append("\n");
		return builder.toString();
	}
	

}
