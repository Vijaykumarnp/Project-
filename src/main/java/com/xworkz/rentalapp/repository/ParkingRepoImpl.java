package com.xworkz.rentalapp.repository;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

import com.xworkz.rentalapp.entity.AdminEntity;
import com.xworkz.rentalapp.parkingdto.AdminDTO;

@Repository
public class ParkingRepoImpl implements ParkingRepo {

	@Autowired
	EntityManagerFactory factory;
   
	
	@Override
	public AdminEntity login(String emailId) {
		
     EntityManager manager =		factory.createEntityManager();
     manager.getTransaction().begin();
      Query q  =   manager.createNamedQuery("findByEmail");
      q.setParameter("emailId", emailId);
		Object dto = q.getSingleResult();
		AdminEntity result = (AdminEntity) dto;
		System.out.println("Result from repo " + result);
		
		
		
		
		return result ;
	}

	@Override
	public boolean updatedTime(AdminEntity entity) {
		System.out.println("Running updatedTime");

		EntityManager em = this.factory.createEntityManager();
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();
		em.close();

		return false;
	}
}
