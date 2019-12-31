package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;


public class DeleteInstructorDemo {

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
			
			
			//begin the transaction 
			session.beginTransaction();
			
			
			//get instructor detail object
			int theId = 4;
			InstructorDetail instructorDetail = session.get(InstructorDetail.class, theId);
			
			//print the instructor detail object
			System.out.println("Instructor detail object is : "+instructorDetail);
			
			//print the associated instructor object
			System.out.println("associated instructor object is: "+instructorDetail.getInstructor());
			
			
			instructorDetail.getInstructor().setInstructorDetail(null); // this will make to delete only instructor detail but not the instructor
			
			//let's delete the instructor details of id=1
			System.out.println("Deleting Instructor detail object: "+instructorDetail);
			session.delete(instructorDetail);
			
			//commit the transaction
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
			sessionFactory.close();
		}

	}

}
