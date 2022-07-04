package probono.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import probono.model.dto.ActivistDTO;
import probono.model.entity.Activist;
import probono.model.util.PublicCommon;

public class ActivistDAO {

	private static ActivistDAO instance = new ActivistDAO();

	private ActivistDAO() {}

	public static ActivistDAO getInstance() {
		return instance;
	}

	public boolean addActivist(ActivistDTO activist) throws SQLException {
		EntityManager em = PublicCommon.getEntityManager();
		em.getTransaction().begin();
		boolean result = false;

		try {
			em.persist(activist.toEntity());
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
	public boolean updateActivist(String activistId, String major) throws SQLException {
		EntityManager em = PublicCommon.getEntityManager();
		em.getTransaction().begin();
		boolean result = false;

		try {
			em.find(Activist.class, activistId).setMajor(major);

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
	// sql - delete from activist where activist_id=?
	public boolean deleteActivist(String activistId) throws SQLException {
		EntityManager em = PublicCommon.getEntityManager();
		em.getTransaction().begin();
		boolean result = false;

		try {
			em.remove(em.find(Activist.class, activistId));

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
	public ActivistDTO getActivist(String activistId) throws SQLException {
		EntityManager em = PublicCommon.getEntityManager();
		em.getTransaction().begin();
		ActivistDTO activist = null;

		try {
			Activist a = em.find(Activist.class, activistId);
			activist = new ActivistDTO(a.getId(), a.getName(), a.getPassword(), a.getMajor());
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return activist;
	}

	// ???모든 기부자 검색해서 반환
	// sql - select * from activist
	@SuppressWarnings("unchecked")
	public ArrayList<ActivistDTO> getAllActivists() throws SQLException {
		EntityManager em = PublicCommon.getEntityManager();
		List<Activist> list = null;
		ArrayList<ActivistDTO> alist = new ArrayList<>();
		try {
			list = em.createNativeQuery("SELECT * FROM Activist").getResultList();
			Iterator it = list.iterator();
			while(it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				alist.add(new ActivistDTO(String.valueOf(obj[0]), String.valueOf(obj[1]), String.valueOf(obj[2]), String.valueOf(obj[3])));
			}
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return alist;
	}
}
