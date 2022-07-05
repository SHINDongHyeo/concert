package concert.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;

import concert.model.dto.SingerDTO;
import concert.model.entity.Singer;
import probono.model.util.PublicCommon;

public class SingerDAO {
	
	
	//가수 저장
	public boolean addSinger(SingerDTO Singer) throws SQLException {
		EntityManager manager = PublicCommon.getEntityManager();
		manager.getTransaction().begin();
		boolean result = false;
		try {
			manager.persist(Singer);
			manager.getTransaction().commit();

			result = true;

		} catch (Exception e) {
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
		return result;
	}
	
		// singer id로 detail 수정하기 
		public boolean updateSinger(String SingerId, String SingerDetail) throws SQLException {
			EntityManager manager = PublicCommon.getEntityManager();
			manager.getTransaction().begin();
			boolean result = false;
			try {
				manager.find(Singer.class, SingerId).setDetail(SingerDetail);

				manager.getTransaction().commit();

				result = true;
			} catch (Exception e) {
				manager.getTransaction().rollback();
			} finally {
				manager.close();
			}
			return result;
		}
	
		//가수 삭제
		public boolean deleteSinger(String SingerId) throws SQLException {
			EntityManager manager = PublicCommon.getEntityManager();
			manager.getTransaction().begin();
			boolean result = false;

			try {
				manager.remove(manager.find(Singer.class, SingerId));

				manager.getTransaction().commit();

				result = true;
			} catch (Exception e) {
				manager.getTransaction().rollback();
			} finally {
				manager.close();
			}
			return result;
		}

		//가수 id로 가수 모든 정보 검색
		public SingerDTO getSinger(String SingerId) throws SQLException {
			EntityManager manager = PublicCommon.getEntityManager();
			manager.getTransaction().begin();
			SingerDTO Singer = null;

			try {
				Singer p = manager.find(Singer.class, SingerId);
				Singer = new SingerDTO(p.getSingerId(), p.getSingerName(), p.getDetail());
			} catch (Exception e) {
				manager.getTransaction().rollback();
			} finally {
				manager.close();
			}
			return Singer;
		}
		
		//모든 가수 검색
		@Test //ArrayList<SingerDTO>
		public ArrayList<SingerDTO> getAllSingers() throws SQLException {
			EntityManager em = PublicCommon.getEntityManager();
			ArrayList<SingerDTO> alist = null;
			List list = null;
			try {
				list = em.createNativeQuery("SELECT * FROM singer").getResultList();
				Iterator it = list.iterator();
				while(it.hasNext()) {
					Object[] obj = (Object[]) it.next();
					alist.add(new SingerDTO(Integer.valueOf((String) obj[0]), String.valueOf(obj[1]), String.valueOf(obj[2])));
					
				}
			} catch (Exception e) {
				em.getTransaction().rollback();
			} finally {
				em.close();
			}
			return alist;
		}
}

