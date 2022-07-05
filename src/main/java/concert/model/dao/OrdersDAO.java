package concert.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import concert.model.dto.OrdersDTO;
import concert.model.entity.Orders;
import concert.model.util.PublicCommon;

public class OrdersDAO {
	private static OrdersDAO instance = new OrdersDAO();

	private OrdersDAO() {
	}

	public static OrdersDAO getInstance() {
		return instance;
	}

	public boolean addOrders(OrdersDTO orders) throws SQLException {
		EntityManager manager = PublicCommon.getEntityManager();
		manager.getTransaction().begin();
		boolean result = false;

		try {
			manager.persist(orders.toEntity());
			manager.getTransaction().commit();
			result = true;

		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
		return result;
	}

//	public boolean updateProbonoProjectActivist(int projectId, String activistId) throws SQLException {
//		EntityManager manager = PublicCommon.getEntityManager();
//		manager.getTransaction().begin();
//		boolean result = false;
//
//		try {
//			manager.find(ProbonoProject.class, projectId).setActivistId(activistId);
//
//			manager.getTransaction().commit();
//
//			result = true;
//		} catch (Exception e) {
//			manager.getTransaction().rollback();
//		} finally {
//			manager.close();
//		}
//		return result;
//	}

//	public boolean updateProbonoProjectReceive(int probonoProjectId, String receiveId) throws SQLException {
//		EntityManager manager = PublicCommon.getEntityManager();
//		manager.getTransaction().begin();
//		boolean result = false;
//
//		try {
//			manager.find(ProbonoProject.class, probonoProjectId).setReceiveId(receiveId);
//
//			manager.getTransaction().commit();
//
//			result = true;
//		} catch (Exception e) {
//			manager.getTransaction().rollback();
//		} finally {
//			manager.close();
//		}
//		return result;
//	}

	public boolean deleteOrders(int orderId) throws SQLException {
		EntityManager manager = PublicCommon.getEntityManager();
		manager.getTransaction().begin();
		boolean result = false;

		try {
			manager.remove(manager.find(Orders.class, orderId));

			manager.getTransaction().commit();

			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
		return result;
	}

//	public OrdersDTO getProbonoProject(int probonoProjectId) throws SQLException {
//		EntityManager manager = PublicCommon.getEntityManager();
//		manager.getTransaction().begin();
//		OrdersDTO probonoProject = null;
//
//		try {
//			ProbonoProject p = manager.find(ProbonoProject.class, probonoProjectId);
//			probonoProject = new OrdersDTO(p.getProbonoProjectId(), p.getProbonoProjectName(), p.getProbonoId(),
//					p.getActivistId(), p.getReceiveId(), p.getProjectContent());
//		} catch (Exception e) {
//			manager.getTransaction().rollback();
//		} finally {
//			manager.close();
//		}
//		return probonoProject;
//	}

	public List<OrdersDTO> getAllOrders() throws SQLException {
		EntityManager manager = PublicCommon.getEntityManager();
		List<OrdersDTO> DTO = new ArrayList<OrdersDTO>();
		List<Orders> entity = null;
		try {
			entity = manager.createQuery("SELECT o FROM Orders o").getResultList();
			for(Orders o : entity) {
				OrdersDTO temp = OrdersDTO.builder().orderId(Integer.valueOf(o.getOrderId()))
													.customerName(String.valueOf(o.getCustomerName()))
													.customerEmail(String.valueOf(o.getCustomerEmail()))
													.concertId(Integer.valueOf(o.getConcert_id()))
													.amount(Integer.valueOf(o.getAmount())).build();
//				System.out.println(temp);
				DTO.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
		return DTO;
	}
}
