package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;


public class GetInstructorCoursesDemo {

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
			
			//get instructor from db
			int id = 1;
			Instructor tempInstructor = session.get(Instructor.class, id);

			System.out.println("Instructor object:" +tempInstructor);
			
			System.out.println("Couses related to the instructor are:" +tempInstructor.getCourses());
			
			//commit the transaction
			session.getTransaction().commit();
			
		} catch (Exception e) {

		}
		finally {
			session.close();
			sessionFactory.close();
		}

	}

}
