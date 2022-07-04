package concert.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

<<<<<<< HEAD:src/main/java/probono/model/ProbonoDAO.java
import concert.model.entity.Probono;
import probono.model.dto.ActivistDTO;
import probono.model.dto.ProbonoDTO;
=======
import concert.model.dto.ActivistDTO;
import concert.model.dto.OrderDTO;
import probono.model.entity.Order;
>>>>>>> 2f63035c1378ab1cb0e3594d975637e217aa704f:src/main/java/concert/model/OrderDAO.java
import probono.model.util.PublicCommon;

public class OrderDAO {

	private static OrderDAO instance = new OrderDAO();

	private OrderDAO() {
	}

	public static OrderDAO getInstance() {
		return instance;
	}

	// 저장
	public boolean addOrder(OrderDTO probono) throws SQLException {
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
	public boolean updateOrder(String probonoId, String probonoPurpose) throws SQLException {
		EntityManager manager = PublicCommon.getEntityManager();
		manager.getTransaction().begin();
		boolean result = false;
		try {
			manager.find(Order.class, probonoId).setOrderPurpose(probonoPurpose);

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
	public boolean deleteOrder(String probonoId) throws SQLException {
		EntityManager manager = PublicCommon.getEntityManager();
		manager.getTransaction().begin();
		boolean result = false;

		try {
			manager.remove(manager.find(Order.class, probonoId));

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
	public OrderDTO getOrder(String probonoId) throws SQLException {
		EntityManager manager = PublicCommon.getEntityManager();
		manager.getTransaction().begin();
		OrderDTO probono = null;

		try {
			Order p = manager.find(Order.class, probonoId);
			probono = new OrderDTO(p.getOrderId(), p.getOrderName(), p.getOrderPurpose());
		} catch (Exception e) {
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
		return probono;
	}

	// 모든 프로보노 검색
	public ArrayList<OrderDTO> getAllOrders() throws SQLException {
		EntityManager manager = PublicCommon.getEntityManager();
		ArrayList<OrderDTO> alist = null;
		List list = null;
		try {
			list = manager.createNativeQuery("SELECT * FROM Order").getResultList();
			Iterator it = list.iterator();
			while(it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				alist.add(new OrderDTO(String.valueOf(obj[0]), String.valueOf(obj[1]), String.valueOf(obj[2])));
			}
		} catch (Exception e) {
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
		return alist;
	}
}
