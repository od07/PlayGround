package com.mytectra.springboot.playground.datastore.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.mytectra.springboot.playground.model.Chocolate;

@Repository
@Primary
public class ChocolateHibernateDao implements IChocolateDao {
	
	/*@Autowired
	private HibernateTemplate htemplate;*/
	
	@Autowired
	private EntityManager manager;

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void save(Chocolate chocolate) {
		manager.persist(chocolate);
	}

	@Override
	public List<Chocolate> findAll() {
		/*CriteriaBuilder empty = manager.getCriteriaBuilder();
		Selection<Object> selection = empty.createQuery().getSelection();
		selection.
		TypedQuery<Chocolate> query = manager.createQuery(empty.createQuery(Chocolate.class).select(Chocolate.class));*/
		TypedQuery<Chocolate> query = manager.createQuery("SELECT c FROM Chocolate c", Chocolate.class);
		return query.getResultList();
	}
	

}
