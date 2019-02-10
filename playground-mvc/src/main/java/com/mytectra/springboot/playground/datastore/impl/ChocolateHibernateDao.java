package com.mytectra.springboot.playground.datastore.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.mytectra.springboot.playground.model.Chocolate;

@Repository
@Primary
public class ChocolateHibernateDao implements IChocolateDao {

	/*
	 * @Autowired private HibernateTemplate htemplate;
	 */

	@Autowired
	private EntityManager manager;

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void save(Chocolate chocolate) {
		manager.persist(chocolate);
	}

	@Override
	public List<Chocolate> findAll() {
		TypedQuery<Chocolate> query = manager.createQuery("SELECT c FROM Chocolate c", Chocolate.class);
		return query.getResultList();
	}

	public List<Chocolate> findAll(String brand) {
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Chocolate> cQuery = cb.createQuery(Chocolate.class);

		Root<Chocolate> rootPath = cQuery.from(Chocolate.class);
		cQuery.where(cb.equal(rootPath.get("brand"), brand));
		cQuery.select(rootPath);

		TypedQuery<Chocolate> rsQuery = manager.createQuery(cQuery);

		return rsQuery.getResultList();
	}

	// Upadate
	@Override
	@Transactional(value = TxType.REQUIRED)
	public void update(Chocolate chocolate) {
		manager.merge(chocolate);
	}

	@Override
	public Chocolate findByChocolate(String chocolateName) {
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Chocolate> cQuery = cb.createQuery(Chocolate.class);

		Root<Chocolate> rootPath = cQuery.from(Chocolate.class);
		cQuery.where(cb.equal(rootPath.get("name"), chocolateName));
		cQuery.select(rootPath);

		TypedQuery<Chocolate> rsQuery = manager.createQuery(cQuery);

		return rsQuery.getSingleResult();

	}
	@Transactional(value = TxType.REQUIRED)
	public void updateChocolate(Chocolate chocolate) {
		CriteriaBuilder cb = manager.getCriteriaBuilder();

		// create update
		CriteriaUpdate<Chocolate> update = cb.createCriteriaUpdate(Chocolate.class);

		// set the root class
		Root<Chocolate> e = update.from(Chocolate.class);

		// set update and where clause
		update.set("price", chocolate.getPrice());
		update.where(cb.equal(e.get("name"), chocolate.getName()));

		// perform update
		manager.createQuery(update).executeUpdate();
	}

}
