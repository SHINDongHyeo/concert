package probono.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import probono.model.dto.ProbonoDTO;
import probono.model.dto.RecipientDTO;
import probono.model.entity.Recipient;
import probono.model.util.PublicCommon;

public class RecipientDAO {
	
	private static RecipientDAO instance = new RecipientDAO();

	private RecipientDAO() {
	}

	public static RecipientDAO getInstance() {
		return instance;
	}
	
	public boolean addRecipient(RecipientDTO recipient) throws SQLException{
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
	public boolean updateRecipient(String recipientId, String receiveHopeContent) throws SQLException{
		EntityManager em = PublicCommon.getEntityManager();
		em.getTransaction().begin();
		boolean result = false;
		
		try {
			em.find(Recipient.class, recipientId).setReceiveHopeContent(receiveHopeContent);

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
	public boolean deleteRecipient(String recipientId) throws SQLException{
		EntityManager em = PublicCommon.getEntityManager();
		em.getTransaction().begin();
		boolean result = false;
		
		try {
			em.remove(em.find(Recipient.class, recipientId));

			em.getTransaction().commit();

			result = true;
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return result;
	}

	public RecipientDTO getRecipient(String recipientId) throws SQLException{
		EntityManager em = PublicCommon.getEntityManager();
		em.getTransaction().begin();
		RecipientDTO recipient = null;

		try {
			Recipient r = em.find(Recipient.class, recipientId);
			recipient = new RecipientDTO(r.getId(), r.getName(), r.getPassword(), r.getReceiveHopeContent());
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return recipient;
	}

	public ArrayList<RecipientDTO> getAllRecipients() throws SQLException{
		EntityManager em = PublicCommon.getEntityManager();
		ArrayList<RecipientDTO> alist = new ArrayList<>();
		List list = null;
		try {
			list = em.createNativeQuery("select * from Recipient").getResultList();
			Iterator it = list.iterator();
			while(it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				alist.add(new RecipientDTO(String.valueOf(obj[0]), String.valueOf(obj[1]), String.valueOf(obj[2]), String.valueOf(obj[3])));
			}
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return alist;
	}
}
