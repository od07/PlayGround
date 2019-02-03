package com.mytectra.springboot.playground.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mytectra.springboot.playground.datastore.ItemStore;
import com.mytectra.springboot.playground.model.Chocolate;

@RunWith(SpringJUnit4ClassRunner.class)
public class VendingBonanzaTest {

	@Mock
	private ItemStore<Chocolate> chocolateStore;
	
	@InjectMocks
	private VendingBonanza bonanza;
	
	@Test
	public void testGetItems() throws Exception {
		List<Chocolate> bonuzChocloates = bonanza.getItems(3);
		Assert.assertEquals(0, bonuzChocloates.size());
		
		
		List<Chocolate> chocolates = new ArrayList<>();
		
		chocolates.add(new Chocolate("KitKat", "Cadebury", 10));
		chocolates.add(new Chocolate("Tuffy", "Cadebury", 1));
		
		Mockito.when(chocolateStore.getItems(2)).thenReturn(Optional.of(chocolates));
		bonuzChocloates = bonanza.getItems(5);

		Assert.assertEquals(2, bonuzChocloates.size());
		
	}
	
	
	@Test
	public void testGetItems2() throws Exception {
		ItemStore<Chocolate> cItemStore = Mockito.mock(ItemStore.class);
		VendingBonanza bonanza = new VendingBonanza(cItemStore);
		
		List<Chocolate> bonuzChocloates = bonanza.getItems(3);
		Assert.assertEquals(0, bonuzChocloates.size());
		
		
		List<Chocolate> chocolates = new ArrayList<>();
		
		chocolates.add(new Chocolate("KitKat", "Cadebury", 10));
		chocolates.add(new Chocolate("Tuffy", "Cadebury", 1));
		
		Mockito.when(cItemStore.getItems(2)).thenReturn(Optional.of(chocolates));
		bonuzChocloates = bonanza.getItems(5);

		Assert.assertEquals(2, bonuzChocloates.size());
		
	}
	
	@Test(expected = Exception.class)
	public void testGetItems3() throws Exception {
		ItemStore<Chocolate> cItemStore = Mockito.mock(ItemStore.class);
		VendingBonanza bonanza = new VendingBonanza(cItemStore);
		
		List<Chocolate> bonuzChocloates = bonanza.getItems(3);
		Assert.assertEquals(0, bonuzChocloates.size());
		
		
		
		Mockito.when(cItemStore.getItems(2)).thenReturn(Optional.empty());
		bonuzChocloates = bonanza.getItems(5);

		
	}
}
