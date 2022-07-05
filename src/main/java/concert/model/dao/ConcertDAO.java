package concert.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.modelmapper.ModelMapper;

import concert.model.dto.ConcertDTO;
import concert.model.entity.Concert;
import concert.model.util.PublicCommon;

public class ConcertDAO {
	private static ConcertDAO instance = new ConcertDAO();

	private ConcertDAO() {}

	public static ConcertDAO getInstance() {
		return instance;
	}
	
	// 한 콘서트 검색
	public ConcertDTO getConcert(int concertId) throws SQLException {
		EntityManager em = PublicCommon.getEntityManager();
		em.getTransaction().begin();
		ConcertDTO concert = null;
		try {
			Concert a = em.find(Concert.class, concertId);
			concert = new ConcertDTO(a.getConcertId(), a.getConcertName(), a.getLocation(), a.getDate(), a.getMaxSeats(), a.getContents());
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return concert;
	}
	// 모든 콘서트 검색
//	DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
//	format.parse((String)obj[3])
	public ArrayList<ConcertDTO> getAllConcert() throws SQLException {
		EntityManager em = PublicCommon.getEntityManager();
		em.getTransaction().begin();
		List<Concert> a = null;
		ArrayList<ConcertDTO> allConcert = new ArrayList<>();
		try {
			a = em.createQuery("select c from Concert c", Concert.class).getResultList();
			ModelMapper modelMapper = new ModelMapper();
			for (Concert aaa : a) {
				allConcert.add(modelMapper.map(aaa, ConcertDTO.class));
			}
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		System.out.println(allConcert);
		return allConcert;
	}
	// 한 콘서트 삭제
	public boolean deleteConcert(int concertId) throws SQLException {
		EntityManager manager = PublicCommon.getEntityManager();
		manager.getTransaction().begin();
		boolean result = false;
		try {
			manager.remove(manager.find(Concert.class, concertId));
			manager.getTransaction().commit();
			result = true;
		} catch (Exception e) {
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
		return result;
	}
	// 한 콘서트 추가
	public boolean addConcert(ConcertDTO concert) throws SQLException {
		EntityManager manager = PublicCommon.getEntityManager();
		manager.getTransaction().begin();
		boolean result = false;
		try {
			manager.persist(concert);
			manager.getTransaction().commit();
			result = true;
		} catch (Exception e) {
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
		return result;
	}
	// 한 콘서트 수정
	public boolean updateConcert(int concertId, int maxSeats) throws SQLException {
		EntityManager manager = PublicCommon.getEntityManager();
		manager.getTransaction().begin();
		boolean result = false;
		try {
			manager.find(Concert.class, concertId).setMaxSeats(maxSeats);
			manager.getTransaction().commit();
			result = true;
		} catch (Exception e) {
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
		return result;
	}
}
