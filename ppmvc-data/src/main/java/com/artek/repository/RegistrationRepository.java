package com.artek.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.artek.model.User;

@Repository
public class RegistrationRepository {
	
	@Autowired
	private HibernateTemplate template;
	
	public int checkEmail(String email){
		List prevUsers = null;
		System.err.println(email);
		prevUsers = template.find("from User where email_id=?", email);
		return prevUsers.size();
	}
	
	public int registerRepo(User user){
		
		System.err.println("Inside registerRepo method of RegistrationRepository class");
		
		System.out.println(user.getFirstName());
		System.out.println(user.getLastName());
		System.out.println(user.getEmailId());
		System.out.println(user.getPassword());
					
		int id = -1;
		
		SessionFactory factory = template.getSessionFactory();
		Session session = factory.openSession();
		
		Transaction transaction = session.beginTransaction();		
		
		try{
			//id = (Integer) template.save(user);
			id = (Integer) session.save(user);
		}catch(Exception e){
			System.out.println(e);
			id = -1;
		}
		
		transaction.commit();
		session.close();
		
		return id;
	}
}
