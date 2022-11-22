package b1.capitalHumano;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateUtil { 
	private static SessionFactory sessionFactory;

    public static final ThreadLocal local = new ThreadLocal(); 

    public static Session currentSession() throws HibernateException { 
       Session session = (Session) local.get(); 
       //open a new session if this thread has no session 
       if(session == null) { 
          session = sessionFactory.openSession(); 
          local.set(session);     
       } 
      return session; 
   } 
} 
