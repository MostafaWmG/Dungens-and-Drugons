import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dungeons");  
		EntityManager em = emf.createEntityManager();                               
		EntityTransaction tx = em.getTransaction();                                 

		System.out.println("Before test");
		tx.begin();
		Test test = em.find(Test.class, 1);
		tx.commit();
		System.out.println("After test");
		if (test == null) {
			System.out.println("test is null");
		  test = new Test();                                                        
		  test.setId(1);                                                              
		  test.setData("a");                                                          

		  tx.begin();                                                               
		  em.persist(test);                                                         
		  tx.commit();                                                              
		}                                                                           

		System.out.format("Test{id=%s, data=%s}\n", test.getId(), test.getData());            

		em.close();                                                                 
		emf.close();

	}

}
