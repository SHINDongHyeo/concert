package concert.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import concert.model.dto.OrdersDTO;
import concert.model.dto.SingerDTO;
import concert.model.entity.Orders;
import concert.model.entity.Singer;
import concert.model.util.PublicCommon;

public class SingerDAO {
	
	private static SingerDAO instance = new SingerDAO();

	private SingerDAO() {
	}

	public static SingerDAO getInstance() {
		return instance;
	}
	
	//가수 저장
	public boolean addSinger(SingerDTO singer) throws SQLException {
		EntityManager manager = PublicCommon.getEntityManager();
		manager.getTransaction().begin();
		boolean result = false;
		try {
			manager.persist(singer);
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
		public boolean updateSinger(String singerId, String singerDetail) throws SQLException {
			EntityManager manager = PublicCommon.getEntityManager();
			manager.getTransaction().begin();
			boolean result = false;
			try {
				manager.find(Singer.class, singerId).setDetail(singerDetail);

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
		public boolean deleteSinger(String singerId) throws SQLException {
			EntityManager manager = PublicCommon.getEntityManager();
			manager.getTransaction().begin();
			boolean result = false;

			try {
				manager.remove(manager.find(Singer.class, singerId));

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
		public SingerDTO getSinger(String singerId) throws SQLException {
			EntityManager manager = PublicCommon.getEntityManager();
			manager.getTransaction().begin();
			SingerDTO singer = null;

			try {
				Singer p = manager.find(Singer.class, Integer.parseInt(singerId));
				singer = new SingerDTO(p.getSingerId(), p.getSingerName(), p.getDetail());
			} catch (Exception e) {
				manager.getTransaction().rollback();
			} finally {
				manager.close();
			}
			return singer;
		}
		
		//모든 가수 검색
		public ArrayList<SingerDTO> getAllSingers() throws SQLException {
			EntityManager em = PublicCommon.getEntityManager();
			ArrayList<SingerDTO> DTO = new ArrayList<SingerDTO>();
			List<Singer> entity = null;
			try {
				entity = em.createQuery("SELECT s FROM Singer s").getResultList();
				for(Singer s : entity) {
					SingerDTO temp = SingerDTO.builder().singerId(s.getSingerId())
														.singerName(s.getSingerName())
														.detail(s.getDetail()).build();
					DTO.add(temp);
				}
			} catch (Exception e) {
				em.getTransaction().rollback();
			} finally {
				em.close();
			}
			return DTO;
		}

		public ArrayList<SingerDTO> getSomeConcertSinger() {
			EntityManager em = PublicCommon.getEntityManager();
			ArrayList<SingerDTO> DTO = new ArrayList<SingerDTO>();
			List<Singer> entity = null;
			try {
				entity = em.createQuery("SELECT s FROM Singer s").getResultList();
				for(Singer s : entity) {
					SingerDTO temp = SingerDTO.builder().singerId(s.getSingerId())
														.singerName(s.getSingerName())
														.detail(s.getDetail()).build();
					DTO.add(temp);
				}
			} catch (Exception e) {
				em.getTransaction().rollback();
			} finally {
				em.close();
			}
			return DTO;
		}
}

