package probono.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import probono.model.dto.ProbonoDTO;
import probono.model.dto.ProbonoProjectDTO;
import probono.model.entity.ProbonoProject;
import probono.model.util.PublicCommon;

public class ProbonoProjectDAO {

	private static ProbonoProjectDAO instance = new ProbonoProjectDAO();

	private ProbonoProjectDAO() {
	}

	public static ProbonoProjectDAO getInstance() {
		return instance;
	}

	// 프로보노 프로젝트 저장
	public boolean addProbonoProject(ProbonoProjectDTO probonoProject) throws SQLException {
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
	public ProbonoProjectDTO getProbonoProject(int probonoProjectId) throws SQLException {
		EntityManager manager = PublicCommon.getEntityManager();
		manager.getTransaction().begin();
		ProbonoProjectDTO probonoProject = null;

		try {
			ProbonoProject p = manager.find(ProbonoProject.class, probonoProjectId);
			probonoProject = new ProbonoProjectDTO(p.getProbonoProjectId(), p.getProbonoProjectName(), p.getProbonoId(),
					p.getActivistId(), p.getReceiveId(), p.getProjectContent());
		} catch (Exception e) {
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
		return probonoProject;
	}

	// 모든 프로보노 프로젝트 검색
	public ArrayList<ProbonoProjectDTO> getAllProbonoProjects() throws SQLException {
		EntityManager manager = PublicCommon.getEntityManager();
		ArrayList<ProbonoProjectDTO> alist = new ArrayList<>();
		List<ProbonoProjectDTO> list = null;
		try {
			list = manager.createNativeQuery("SELECT * FROM Probono_Project").getResultList();
			Iterator it = list.iterator();
			while(it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				alist.add(new ProbonoProjectDTO(Integer.parseInt(String.valueOf(obj[0])), String.valueOf(obj[1]), String.valueOf(obj[2]), String.valueOf(obj[3]), String.valueOf(obj[4]), String.valueOf(obj[5])));
			}
		} catch (Exception e) {
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
		return alist;
	}
}
