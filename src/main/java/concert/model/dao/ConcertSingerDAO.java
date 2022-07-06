package concert.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.modelmapper.ModelMapper;

import concert.model.dto.ConcertSingerDTO;
import concert.model.entity.ConcertSinger;
import concert.model.util.PublicCommon;

public class ConcertSingerDAO {
	
	private static ConcertSingerDAO instance = new ConcertSingerDAO();

	private ConcertSingerDAO() {}

	public static ConcertSingerDAO getInstance() {
		return instance;
	}
	
	static ModelMapper modelMapper = new ModelMapper();
	
	public ArrayList<ConcertSingerDTO> getAllconcertSinger() throws SQLException{
		
		EntityManager em = PublicCommon.getEntityManager();
		List<ConcertSingerDTO> l = null;
		
		ArrayList<ConcertSingerDTO> concertSinger = new ArrayList<ConcertSingerDTO>();
		ConcertSingerDTO newConcertSinger = null;
		
		try {
			l = em.createQuery("select c from ConcertSinger c").getResultList();

			for(int i = 0; i < l.size(); i ++) {
				newConcertSinger = modelMapper.map(l.get(i), ConcertSingerDTO.class);
				if(newConcertSinger != null) {
					concertSinger.add(newConcertSinger);
				}
			}
		
		}finally {
			em.close();
			em = null;
		}
		return concertSinger;
		
	}
		
	public boolean addConcertSinger(ConcertSingerDTO concertSinger) throws SQLException {
		EntityManager em = PublicCommon.getEntityManager();
		em.getTransaction().begin();
		boolean result = false;
		ConcertSinger newConcertSinger = modelMapper.map(concertSinger, ConcertSinger.class);

		try {
			em.persist(newConcertSinger);
			em.getTransaction().commit();

			result = true;

		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
			em = null;
		}
		return result;
	}
	
	public boolean deleteConcertSinger(String concertSingerId) throws SQLException {
		EntityManager em = PublicCommon.getEntityManager();
		em.getTransaction().begin();
		boolean result = false;

		try {
			em.remove(em.find(ConcertSinger.class, Integer.parseInt(concertSingerId)));

			em.getTransaction().commit();

			result = true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		} finally {
			em.close();
		}
		return result;
	}
	
	public ConcertSingerDTO getConcertSinger(String concertSingerId) throws SQLException {
		EntityManager em = PublicCommon.getEntityManager();
		em.getTransaction().begin();
		ConcertSingerDTO concertSinger = null;

		try {
			ConcertSinger a = em.find(ConcertSinger.class, Integer.parseInt(concertSingerId));
			concertSinger = new ConcertSingerDTO(a.getConcertSingerId(), a.getConcertId(), a.getSingerId());
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return concertSinger;
	}
	
//	@Test
//	public void test() {
//		System.out.println("테스트용");
//		int concertId = 1;
//		EntityManager em = PublicCommon.getEntityManager();
//		em.getTransaction().begin();
//		ArrayList<Integer> result2 = new ArrayList<Integer>();
//		List<ConcertSinger> result = null;
//		try {
//			System.out.println("쿼리문실행");
//			result = em.createQuery("select cs from ConcertSinger cs where "
//					+ "cs.concertId=:concertId").setParameter("concertId", concertId)
//					.getResultList();
//			System.out.println("쿼리문실행완료");
//			System.out.println(result);
//			for(ConcertSinger i:result) {
//				result2.add(i.getConcertId());			
//			}
//			System.out.println(result2);
//		} catch (Exception e) {
//			em.getTransaction().rollback();
//			e.printStackTrace();
//			System.out.println("예외처리발생");
//		} finally {
//			em.close();
//			System.out.println("em종료");
//		}
//	}
	
	public ArrayList<Integer> getSingersByConcert(int concertId) throws SQLException{
		EntityManager em = PublicCommon.getEntityManager();
//		em.getTransaction().begin();
		ArrayList<Integer> result2 = new ArrayList<Integer>();
		List<ConcertSinger> result = null;
		try {
			System.out.println("쿼리문실행");
			result = em.createQuery("select cs from ConcertSinger cs where "
					+ "cs.concertId=:concertId").setParameter("concertId", concertId)
					.getResultList();
			System.out.println("쿼리문실행완료");
			System.out.println(result);
			for(ConcertSinger i:result) {
				result2.add(i.getSingerId());			
			}
			System.out.println(result2);
		} catch (Exception e) {
//			em.getTransaction().rollback();
			e.printStackTrace();
			System.out.println("예외처리발생");
		} finally {
			em.close();
			System.out.println("em종료");
		}
		return result2;
	}

	
//	public static boolean updateConcertSinger(String activistId, String major) throws SQLException{
//		EntityManager em = PublicCommon.getEntityManager();
//		em.getTransaction().begin();
//		boolean result = false;
//
//		try {
//			em.find(Activist.class, activistId).setMajor(major);
//
//			em.getTransaction().commit();
//
//			result = true;
//		} catch (Exception e) {
//			em.getTransaction().rollback();
//		} finally {
//			em.close();
//		}
//		return result;
//	}


}
