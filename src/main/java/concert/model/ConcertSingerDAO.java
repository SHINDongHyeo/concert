package concert.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import concert.model.dto.ConcertSingerDTO;
import concert.model.dto.OrderDTO;
import probono.model.entity.ProbonoProject;
import probono.model.util.PublicCommon;

public class ConcertSingerDAO {

	private static ConcertSingerDAO instance = new ConcertSingerDAO();

	private ConcertSingerDAO() {
	}

	public static ConcertSingerDAO getInstance() {
		return instance;
	}

	// 프로보노 프로젝트 저장
	public boolean addProbonoProject(ConcertSingerDTO probonoProject) throws SQLException {
		EntityManager manager = PublicCommon.getEntityManager();
		manager.getTransaction().begin();
		boolean result = false;

		try {
			manager.persist(probonoProject);
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
	// 프로보노 프로젝트 ID로 재능기부자 수정
	public boolean updateProbonoProjectActivist(int projectId, String activistId) throws SQLException {
		EntityManager manager = PublicCommon.getEntityManager();
		manager.getTransaction().begin();
		boolean result = false;

		try {
			manager.find(ProbonoProject.class, projectId).setActivistId(activistId);

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
	// 프로보노 프로젝트 id로 수해자 정보 변경
	public boolean updateProbonoProjectReceive(int probonoProjectId, String receiveId) throws SQLException {
		EntityManager manager = PublicCommon.getEntityManager();
		manager.getTransaction().begin();
		boolean result = false;

		try {
			manager.find(ProbonoProject.class, probonoProjectId).setReceiveId(receiveId);

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
	// 프로보노 프로젝트 id로 프로보노 프로젝트 삭제
	public boolean deleteProbonoProject(int probonoProjectId) throws SQLException {
		EntityManager manager = PublicCommon.getEntityManager();
		manager.getTransaction().begin();
		boolean result = false;

		try {
			manager.remove(manager.find(ProbonoProject.class, probonoProjectId));

			manager.getTransaction().commit();

			result = true;
		} catch (Exception e) {
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
		return result;
	}

	// 프로보노 프로젝트 id로 해당 프로보노프로젝트 검색
	public ConcertSingerDTO getProbonoProject(int probonoProjectId) throws SQLException {
		EntityManager manager = PublicCommon.getEntityManager();
		manager.getTransaction().begin();
		ConcertSingerDTO probonoProject = null;

		try {
			ProbonoProject p = manager.find(ProbonoProject.class, probonoProjectId);
			probonoProject = new ConcertSingerDTO(p.getProbonoProjectId(), p.getProbonoProjectName(), p.getProbonoId(),
					p.getActivistId(), p.getReceiveId(), p.getProjectContent());
		} catch (Exception e) {
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
		return probonoProject;
	}

	// 모든 프로보노 프로젝트 검색
	public ArrayList<ConcertSingerDTO> getAllProbonoProjects() throws SQLException {
		EntityManager manager = PublicCommon.getEntityManager();
		ArrayList<ConcertSingerDTO> alist = new ArrayList<>();
		List<ConcertSingerDTO> list = null;
		try {
			list = manager.createNativeQuery("SELECT * FROM Probono_Project").getResultList();
			Iterator it = list.iterator();
			while(it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				alist.add(new ConcertSingerDTO(Integer.parseInt(String.valueOf(obj[0])), String.valueOf(obj[1]), String.valueOf(obj[2]), String.valueOf(obj[3]), String.valueOf(obj[4]), String.valueOf(obj[5])));
			}
		} catch (Exception e) {
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
		return alist;
	}
}
