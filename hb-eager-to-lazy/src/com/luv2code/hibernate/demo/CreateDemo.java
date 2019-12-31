package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;


public class CreateDemo {

	public static void main(String[] args) {
		
		//create session factory ( created only once that produces all sessions )
		
		SessionFactory sessionFactory = new Configuration()
											.configure("hibernate.cfg.xml")
											.addAnnotatedClass(Instructor.class)
											.addAnnotatedClass(InstructorDetail.class)
											.buildSessionFactory();
		
		//create session
		
		Session session = sessionFactory.getCurrentSession();

		try {
			
			//create the objects
			Instructor tempInstructor = new Instructor("anv", "muth", "anv@muth.com");
			InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.anvesh.com", "plyg cricket");
			
			//associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			//begin the transaction 
			session.beginTransaction();
			
			//save the instructor ( this will also save instructor detail because of the cascade.ALL )
			System.out.println("Saving instructor:" +tempInstructor);
			session.save(tempInstructor);
				
			//commit the transaction
			session.getTransaction().commit();
			
		} catch (Exception e) {

		}
		finally {
			sessionFactory.close();
		}

	}

}
