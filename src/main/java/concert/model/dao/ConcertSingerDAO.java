package concert.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.modelmapper.ModelMapper;

import concert.model.dto.ConcertSingerDTO;
import concert.model.entity.ConcertSinger;
import probono.model.util.PublicCommon;

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
