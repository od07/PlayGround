package com.mytectra.springboot.playground.ui;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mytectra.springboot.playground.core.VendingEngine;
import com.mytectra.springboot.playground.datastore.ItemStore;
import com.mytectra.springboot.playground.model.Chocolate;

@RunWith(SpringJUnit4ClassRunner.class)
public class ChoclolateVendingMachineTest {

	@Mock
	private ItemStore<Chocolate> chocolateStore;

	@Mock
	private VendingEngine<Chocolate> vendingEngine1;
	
	@Mock
	private VendingEngine<Chocolate> vendingEngine3;
	
	@Mock
	private VendingEngine<Chocolate> vendingEngine2;

	@Spy
	private ArrayList<VendingEngine<Chocolate>> vendingEngs;
	
	@InjectMocks
	private ChoclolateVendingMachine chocolateVendingMachine;

	
	@Before
	public void setUp() {
		vendingEngs.add(vendingEngine1);
		vendingEngs.add(vendingEngine2);
		vendingEngs.add(vendingEngine3);
	}
	
	@Test
	public void testGetChocolates() throws Exception {
		List<Chocolate> chocolates = new ArrayList<>();

		chocolates.add(new Chocolate("KitKat", "Cadebury", 10));
		chocolates.add(new Chocolate("Tuffy", "Cadebury", 1));
		Mockito.when(vendingEngine1.getItems(Mockito.anyInt())).thenReturn(chocolates);
		Mockito.when(vendingEngine2.getItems(Mockito.anyInt())).thenReturn(chocolates);
		Mockito.when(vendingEngine3.getItems(Mockito.anyInt())).thenReturn(chocolates);

		List<Chocolate> chocolateVend = chocolateVendingMachine.getChocolates(2);
		Assert.assertEquals(6, chocolateVend.size());
	}
}
