package com.xworkz.rentalapp.repository;

import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import com.xworkz.rentalapp.entity.InformationEntity;

@Repository
public class InformationRepoImpl implements InformationRepo {

	@Autowired
	EntityManagerFactory factory;
	
	@Override
	public void onSave(InformationEntity entity) {
		
	EntityManager manager =	factory.createEntityManager();
		manager.getTransaction().begin();
	manager.persist(entity);
		manager.getTransaction().commit();
	}

	@Override
	public List<InformationEntity> getByLocation(String location) {
	EntityManager man = 	factory.createEntityManager();
	//	man.getTransaction().begin();
	Query q = 	man.createNamedQuery("getByLocation");
	q.setParameter("location", location);
	List<InformationEntity> i =	q.getResultList();
		//sman.getTransaction().commit();
		
		return i ;
	}

	@Override
	public InformationEntity getByAll(String location, String vehicleType, String vehicleClassification, String terms) {
	//EntityManager man = null;
	try {
	
	EntityManager manager = 	factory.createEntityManager();
	Query q = 	manager.createNamedQuery("getByAll");
		q.setParameter("location", location );
		q.setParameter("vehicleType", vehicleType);
		q.setParameter("vehicleClassification", vehicleClassification);
		q.setParameter("terms", terms);
		Object i =	 q.getSingleResult();
	if(i != null ) {
		
		InformationEntity entity2 = (InformationEntity)i;
		return entity2;
		
	}
		
		
	}catch( PersistenceException exp) {
		
	exp.printStackTrace();
	}finally {
		//man.close();
	}
	
	
	return null;
	
	}

}
