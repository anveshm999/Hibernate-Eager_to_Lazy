package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;


public class FetchJoinDemo {

	public static void main(String[] args) {
		
		//create session factory ( created only once that produces all sessions )
		
		SessionFactory sessionFactory = new Configuration()
											.configure("hibernate.cfg.xml")
											.addAnnotatedClass(Instructor.class)
											.addAnnotatedClass(InstructorDetail.class)
											.addAnnotatedClass(Course.class)
											.buildSessionFactory();
		
		//create session
		
		Session session = sessionFactory.getCurrentSession();

		try {
			
			
			//begin the transaction 
			session.beginTransaction();
			
			//option 2: Hibernate query with HQL
			
			//get instructor from db
			int id = 1;
			
			Query<Instructor> query = session.createQuery("select i from Instructor i"
					+ " JOIN FETCH i.courses "
					+ " where i.id = :theInstructorId",
					Instructor.class);
			
			query.setParameter("theInstructorId", id);
			
			Instructor tempInstructor = query.getSingleResult();
			

			System.out.println("Instructor object:" +tempInstructor);
			
			
			
			//commit the transaction
			session.getTransaction().commit();
			
			session.close();
			
			System.out.println("Session is closed");
			
			//since courses are lazy loaded and the session is closed, this should fail
			
			System.out.println("Couses related to the instructor are:" +tempInstructor.getCourses()); //now it works because we have already laoded the courses above
			
		} catch (Exception e) {

		}
		finally {
			session.close();
			sessionFactory.close();
		}

	}

}
