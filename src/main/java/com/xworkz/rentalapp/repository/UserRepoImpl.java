package com.xworkz.rentalapp.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.rentalapp.entity.AdminEntity;
import com.xworkz.rentalapp.entity.UserEntity;
import com.xworkz.rentalapp.parkingdto.UserDTO;
@Repository
public class UserRepoImpl  implements UserRepo{

	@Autowired
	EntityManagerFactory factory;
	
	@Override
	public boolean onSave(UserEntity entity) {
	
	EntityManager manager = 	factory.createEntityManager();
		
	manager.getTransaction().begin();
	manager.persist(entity);
		manager.getTransaction().commit();
		return true;
		
	}
	
	
	
	
	@Override
	public UserEntity findByUserEmail(String emailId) {

		EntityManager entityManager = factory.createEntityManager();
		Query query = entityManager.createNamedQuery("findByUserEmail");
		query.setParameter("email", emailId);
		try {
	   Object obj=query.getSingleResult();
	   return (UserEntity) obj;
	}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	   finally {
			entityManager.close();
			
		}

}




	@Override
	public boolean userSignInUpdate(String oneTimePassword, String emailId) {
		System.out.println("Inside userSignInUpdate");
		
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager.createNamedQuery("updateOTP");
		query.setParameter("mail", emailId);
		query.setParameter("onetime", oneTimePassword);
		
		
		int in=query.executeUpdate();
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;
	}




	@Override
	public UserEntity login(String emailId) {
		
     EntityManager manager =		factory.createEntityManager();
     manager.getTransaction().begin();
      Query q  =   manager.createNamedQuery("findByUserEmailId");
      q.setParameter("email", emailId);
		Object dto = q.getSingleResult();
		UserEntity result =  (UserEntity) q.getSingleResult();
		System.out.println("Result from repo " + result);
		
		//UserEntity entiry = new UserEntity();
		
		
		return result ;
	}




	@Override
	public boolean updatedTime(UserEntity entity) {
		System.out.println("Running updatedTime");

		EntityManager em = this.factory.createEntityManager();
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();
		em.close();

		return false;
	}




	@Override
	public UserEntity fectchAndUpdate( String emailId) {
		EntityManager manegr =     factory.createEntityManager();
		manegr.getTransaction().begin();
		Query q =    manegr.createQuery("findByUserEmailId");
		q.setParameter("email", emailId);
		UserEntity o = 	(UserEntity) q.getSingleResult();
	
		manegr.getTransaction().commit();
		
		return o;
		
	}
	
	
	

	
}
