package jpql;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;




public class JpaMain {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			
			
			Team teamA = new Team();
			teamA.setName("ÆÀ A");
			em.persist(teamA);
			
			Team teamB = new Team();
			teamB.setName("ÆÀ B");
			em.persist(teamB);
			
			
			Member member1 = new Member();
			member1.setUsername("È¸¿ø1");
			member1.setTeam(teamA);
			member1.setAge(0);
			em.persist(member1);
			
			Member member2 = new Member();
			member2.setUsername("È¸¿ø2");
			member2.setTeam(teamA);
			member2.setAge(1);
			em.persist(member2);
			
			Member member3 = new Member();
			member3.setUsername("È¸¿ø3");
			member3.setTeam(teamB);
			member3.setAge(2);
			em.persist(member3);
			
			
			em.flush();
			em.clear();
//			
//			List<Member> resultList = em.createNamedQuery("Member.findByUsername",Member.class)
//								.setParameter("username", "È¸¿ø1").getResultList();
//			for( Member member: resultList) {
//				System.out.println("member = " +member );
//			}
			int resultCount = em.createQuery("update Member m set m.age =20").executeUpdate();
			System.out.println("resultCount = " + resultCount);
			
			em.clear();
			Member findMember = em.find(Member.class,member1.getId());
			System.out.println("findMember = " +findMember.getAge());
			
//			System.out.println("member1.getAge() = " + member1.getAge());
//			System.out.println("member2.getAge() = " + member2.getAge());
//			System.out.println("member3.getAge() = " + member3.getAge());
//			
//			String jpql = "select m from Member m where m.team = :team"; 
//			List<Member> members = em.createQuery(jpql, Member.class) 
//					.setParameter("team",teamA)
//					.getResultList(); 
//			for(Member member : members) {
//				System.out.println("member = " + member);
//			}
//			
			
			
			
			tx.commit();
		}catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			em.close();
		}
		
		emf.close();
		
	}
}
