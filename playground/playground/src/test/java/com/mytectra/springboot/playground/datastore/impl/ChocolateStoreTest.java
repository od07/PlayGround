package com.mytectra.springboot.playground.datastore.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.mytectra.springboot.playground.model.Chocolate;

public class ChocolateStoreTest {

@Test
public void testChocolateStoteLoadItems()
{
	ChocolateStore chocolateStore = new ChocolateStore();
	
	List<Chocolate> chocolates = new ArrayList<>();
	
	chocolates.add(new Chocolate("KitKat", "Cadebury", 10));
	chocolates.add(new Chocolate("Tuffy", "Cadebury", 1));
	chocolates.add(new Chocolate("Cocochoco", "Parley", 15));
	chocolates.add(new Chocolate("Walnut", "Cadebury", 5));
	chocolates.add(new Chocolate("XYZ", "Cadebury", 2));
	
	Assert.assertTrue(chocolateStore.listItems().isEmpty());
	
	chocolateStore.loadItem(chocolates);
	
	Assert.assertEquals(chocolates.size(), chocolateStore.listItems().size());
	
}

	
}
