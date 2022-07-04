package concert.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

<<<<<<< HEAD:src/main/java/probono/model/RecipientDAO.java
import concert.model.entity.Recipient;
import probono.model.dto.ProbonoDTO;
import probono.model.dto.RecipientDTO;
=======
import concert.model.dto.OrderDTO;
import concert.model.dto.SingerDTO;
import probono.model.entity.Singer;
>>>>>>> 2f63035c1378ab1cb0e3594d975637e217aa704f:src/main/java/concert/model/SingerDAO.java
import probono.model.util.PublicCommon;

public class SingerDAO {
	
	private static SingerDAO instance = new SingerDAO();

	private SingerDAO() {
	}

	public static SingerDAO getInstance() {
		return instance;
	}
	
	public boolean addSinger(SingerDTO recipient) throws SQLException{
		EntityManager em = PublicCommon.getEntityManager();
		em.getTransaction().begin();
		boolean result = false;
		
		try {
			em.persist(recipient.toEntity());
			em.getTransaction().commit();

			result = true;

		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return result;
	}

	//수정 로직
	// 프로젝트 명으로 내용 수정하기
	public boolean updateSinger(String recipientId, String receiveHopeContent) throws SQLException{
		EntityManager em = PublicCommon.getEntityManager();
		em.getTransaction().begin();
		boolean result = false;
		
		try {
			em.find(Singer.class, recipientId).setReceiveHopeContent(receiveHopeContent);

			em.getTransaction().commit();

			result = true;
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return result;
	}


	//삭제 로직
	public boolean deleteSinger(String recipientId) throws SQLException{
		EntityManager em = PublicCommon.getEntityManager();
		em.getTransaction().begin();
		boolean result = false;
		
		try {
			em.remove(em.find(Singer.class, recipientId));

			em.getTransaction().commit();

			result = true;
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return result;
	}

	public SingerDTO getSinger(String recipientId) throws SQLException{
		EntityManager em = PublicCommon.getEntityManager();
		em.getTransaction().begin();
		SingerDTO recipient = null;

		try {
			Singer r = em.find(Singer.class, recipientId);
			recipient = new SingerDTO(r.getId(), r.getName(), r.getPassword(), r.getReceiveHopeContent());
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return recipient;
	}

	public ArrayList<SingerDTO> getAllSingers() throws SQLException{
		EntityManager em = PublicCommon.getEntityManager();
		ArrayList<SingerDTO> alist = new ArrayList<>();
		List list = null;
		try {
			list = em.createNativeQuery("select * from Singer").getResultList();
			Iterator it = list.iterator();
			while(it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				alist.add(new SingerDTO(String.valueOf(obj[0]), String.valueOf(obj[1]), String.valueOf(obj[2]), String.valueOf(obj[3])));
			}
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return alist;
	}
}
