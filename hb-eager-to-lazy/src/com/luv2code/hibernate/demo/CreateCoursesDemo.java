package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;


public class CreateCoursesDemo {

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

			//create some courses
			Course course1 = new Course("Maths");
			Course course2 = new Course("Social");
			Course course3 = new Course("Science");
			
			//add courses to instructor
			tempInstructor.add(course1);
			tempInstructor.add(course2);
			tempInstructor.add(course3);
			
			//save the courses
			session.save(course1);
			session.save(course2);
			session.save(course3);
			
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
