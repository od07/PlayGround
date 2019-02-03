package com.mytectra.springboot.playground.core;

import java.util.List;

public interface VendingEngine<T> {
	
	List<T> getItems(int money) throws ChocolatesNotFoundException;

}
