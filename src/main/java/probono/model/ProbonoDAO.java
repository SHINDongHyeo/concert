package probono.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import probono.model.dto.ActivistDTO;
import probono.model.dto.ProbonoDTO;
import probono.model.entity.Probono;
import probono.model.util.PublicCommon;

public class ProbonoDAO {

	private static ProbonoDAO instance = new ProbonoDAO();

	private ProbonoDAO() {
	}

	public static ProbonoDAO getInstance() {
		return instance;
	}

	// 저장
	public boolean addProbono(ProbonoDTO probono) throws SQLException {
		EntityManager manager = PublicCommon.getEntityManager();
		manager.getTransaction().begin();
		boolean result = false;
		try {
			manager.persist(probono);
			manager.getTransaction().commit();

			result = true;

		} catch (Exception e) {
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
		return result;
	}

	// 수정
	// 프로보노 id로 프로보노 목적 수정하기
	public boolean updateProbono(String probonoId, String probonoPurpose) throws SQLException {
		EntityManager manager = PublicCommon.getEntityManager();
		manager.getTransaction().begin();
		boolean result = false;
		try {
			manager.find(Probono.class, probonoId).setProbonoPurpose(probonoPurpose);

			manager.getTransaction().commit();

			result = true;
		} catch (Exception e) {
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
		return result;
	}

	// 삭제
	public boolean deleteProbono(String probonoId) throws SQLException {
		EntityManager manager = PublicCommon.getEntityManager();
		manager.getTransaction().begin();
		boolean result = false;

		try {
			manager.remove(manager.find(Probono.class, probonoId));

			manager.getTransaction().commit();

			result = true;
		} catch (Exception e) {
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
		return result;
	}

	// 프로포노 아이디로 해당 프로보노 모든 정보 검색
	public ProbonoDTO getProbono(String probonoId) throws SQLException {
		EntityManager manager = PublicCommon.getEntityManager();
		manager.getTransaction().begin();
		ProbonoDTO probono = null;

		try {
			Probono p = manager.find(Probono.class, probonoId);
			probono = new ProbonoDTO(p.getProbonoId(), p.getProbonoName(), p.getProbonoPurpose());
		} catch (Exception e) {
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
		return probono;
	}

	// 모든 프로보노 검색
	public ArrayList<ProbonoDTO> getAllProbonos() throws SQLException {
		EntityManager manager = PublicCommon.getEntityManager();
		ArrayList<ProbonoDTO> alist = null;
		List list = null;
		try {
			list = manager.createNativeQuery("SELECT * FROM Probono").getResultList();
			Iterator it = list.iterator();
			while(it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				alist.add(new ProbonoDTO(String.valueOf(obj[0]), String.valueOf(obj[1]), String.valueOf(obj[2])));
			}
		} catch (Exception e) {
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
		return alist;
	}
}
