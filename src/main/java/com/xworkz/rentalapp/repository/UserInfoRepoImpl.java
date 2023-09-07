package com.xworkz.rentalapp.repository;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.rentalapp.entity.UserInfoEntity;
import com.xworkz.rentalapp.parkingdto.UserInfoDTO;

@Repository
public class UserInfoRepoImpl implements UserInfoRepo {
	@Autowired
	EntityManagerFactory factory;

	@Override
	public void validateAndSave(UserInfoEntity entity) {

		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(entity);
		manager.getTransaction().commit();

	}

	@Override
	public void saveUserInformation(String location, String vehicleType, String vehicleClassification, String terms,
			int price, String discount, int totalAmount, UserInfoEntity entity) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createNamedQuery("update");

		q.setParameter("l", entity.getLocation());
		q.setParameter("vt", entity.getVehicleType());
		q.setParameter("vc", entity.getVehicleClassification());
		q.setParameter("terms", entity.getTerms());

		q.setParameter("id", entity.getUser_Id());

		int i = q.executeUpdate();
		manager.getTransaction().commit();
	}

	@Override
	public List<UserInfoDTO> fectchAndUpdate(UserInfoEntity entity2, String email) {
		EntityManager manegr = factory.createEntityManager();
		manegr.getTransaction().begin();
		Query q = manegr.createQuery("fetch");

		List<UserInfoDTO> o = q.getResultList();
		manegr.getTransaction().commit();

		return o;
	}

	@Override
	public UserInfoEntity update(UserInfoEntity entit) {

		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query q = em.createNamedQuery("updatebyid");
		q.setParameter("l", entit.getLocation());
		q.setParameter("vt", entit.getVehicleType());
		q.setParameter("vc", entit.getVehicleClassification());
		q.setParameter("t", entit.getTerms());
		q.setParameter("p", entit.getPrice());
		q.setParameter("d", entit.getDiscount());
		q.setParameter("ta", entit.getTotalAmount());
		q.setParameter("imag", entit.getImageName());
		q.setParameter("pid", entit.getParkingId());
		//em.merge(entit);
		int i = q.executeUpdate();
		em.getTransaction().commit();
		System.out.println("repo" + i);
		return entit ;
	}

	@Override
	public List<UserInfoEntity> findByUserId(int id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query q = em.createNamedQuery("fetch");
		q.setParameter("id", id);
		List<UserInfoEntity> en = q.getResultList();
		em.getTransaction().commit();

		return en;
	}

	@Override
	public UserInfoEntity findByUserIdForUpdate(int parkingId) {
		EntityManager em = factory.createEntityManager();
em.getTransaction().begin();
		Query q = em.createNamedQuery("fetchbyparking");
		q.setParameter("pid", parkingId);

		try {
			UserInfoEntity en = (UserInfoEntity) q.getSingleResult();

			return en;
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.getTransaction().commit();
		return null;
	}

	@Override
	public List<UserInfoDTO> deleteById(int parkingId) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query q = em.createNamedQuery("delete");
		q.setParameter("id", parkingId);
		int i = q.executeUpdate();
		em.getTransaction().commit();
		return null;
	}

}
