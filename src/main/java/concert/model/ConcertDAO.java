package concert.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

<<<<<<< HEAD:src/main/java/probono/model/ActivistDAO.java
import concert.model.entity.Concert;
import probono.model.dto.ActivistDTO;
=======
import concert.model.dto.ConcertDTO;
import probono.model.entity.Concert;
>>>>>>> 2f63035c1378ab1cb0e3594d975637e217aa704f:src/main/java/concert/model/ConcertDAO.java
import probono.model.util.PublicCommon;

public class ConcertDAO {

	private static ConcertDAO instance = new ConcertDAO();

	private ConcertDAO() {}

	public static ConcertDAO getInstance() {
		return instance;
	}

	public boolean addConcert(ConcertDTO Concert) throws SQLException {
		EntityManager em = PublicCommon.getEntityManager();
		em.getTransaction().begin();
		boolean result = false;

		try {
			em.persist(Concert.toEntity());
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

	// 수정
	// 기부자 id로 주요 기부 내용 수정하기
	public boolean updateConcert(String activistId, String major) throws SQLException {
		EntityManager em = PublicCommon.getEntityManager();
		em.getTransaction().begin();
		boolean result = false;

		try {
			em.find(Concert.class, activistId).setMajor(major);

			em.getTransaction().commit();

			result = true;
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return result;
	}

	// ??? 삭제
	// sql - delete from Concert where activist_id=?
	public boolean deleteConcert(String activistId) throws SQLException {
		EntityManager em = PublicCommon.getEntityManager();
		em.getTransaction().begin();
		boolean result = false;

		try {
			em.remove(em.find(Concert.class, activistId));

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

	// id로 해당 기부자의 모든 정보 반환
	public ConcertDTO getConcert(String activistId) throws SQLException {
		EntityManager em = PublicCommon.getEntityManager();
		em.getTransaction().begin();
		ConcertDTO Concert = null;

		try {
			Concert a = em.find(Concert.class, activistId);
<<<<<<< HEAD:src/main/java/probono/model/ActivistDAO.java
			activist = new ActivistDTO(a.getId(), a.getName(), a.getPassword(), a.getMajor());
=======
			Concert = new ConcertDTO(a.getId(), a.getName(), a.getPassword(), a.getMajor());
>>>>>>> 2f63035c1378ab1cb0e3594d975637e217aa704f:src/main/java/concert/model/ConcertDAO.java
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return Concert;
	}

	// ???모든 기부자 검색해서 반환
	// sql - select * from Concert
	@SuppressWarnings("unchecked")
	public ArrayList<ConcertDTO> getAllConcerts() throws SQLException {
		EntityManager em = PublicCommon.getEntityManager();
		List<Concert> list = null;
<<<<<<< HEAD:src/main/java/probono/model/ActivistDAO.java
		ArrayList<ActivistDTO> alist = new ArrayList<>();
=======
		ArrayList<ConcertDTO> alist = new ArrayList<>();
>>>>>>> 2f63035c1378ab1cb0e3594d975637e217aa704f:src/main/java/concert/model/ConcertDAO.java
		try {
			list = em.createNativeQuery("SELECT * FROM Concert").getResultList();
			Iterator it = list.iterator();
			while(it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				alist.add(new ConcertDTO(String.valueOf(obj[0]), String.valueOf(obj[1]), String.valueOf(obj[2]), String.valueOf(obj[3])));
			}
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return alist;
	}
}
