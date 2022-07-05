package concert.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import concert.exception.MessageException;
import concert.exception.NotExistException;
import concert.model.dto.ConcertDTO;
import concert.model.dto.ConcertSingerDTO;
import concert.model.dto.OrdersDTO;
import concert.model.dto.SingerDTO;

public class ConcertService {
	
	private static ConcertService instance = new ConcertService();

	private ConcertService() {
	}

	public static ConcertService getInstance() {
		return instance;
	}
	
	private static ConcertDAO concertDAO = ConcertDAO.getInstance();
	private static ConcertSingerDAO concertSingerDAO = ConcertSingerDAO.getInstance();
	private static OrdersDAO ordersDAO = OrdersDAO.getInstance();
	private static SingerDAO singerDAO = SingerDAO.getInstance();
	
	
	// concert CRUD
	
	public static void notExistConcert(int concertId) throws NotExistException, SQLException{
		ConcertDTO concert = concertDAO.getConcert(concertId);
		if(concert == null){
			throw new NotExistException("검색하진 재능기부 정보가 없습니다.");
		}
	}
	
	//모든 concert 정보 반환
	public static ArrayList<ConcertDTO> getAllConcert() throws SQLException{
		return concertDAO.getAllConcert();
	}
	
	//concert id로 검색
	public static ConcertDTO getConcert(int concertId) throws SQLException, NotExistException{
		ConcertDTO concert = concertDAO.getConcert(concertId);
		if(concert == null){
			throw new NotExistException("검색하신 재능기부 정보가 없습니다.");
		}
		return concert;
	}
	
	//새로운 concert 저장
	public static boolean addConcert(ConcertDTO concert) throws SQLException{
		return concertDAO.addConcert(concert);
	}
	
	public static boolean updateConcert(int concertId, int maxSeats) throws SQLException, NotExistException{
		notExistConcert(concertId);
		return concertDAO.updateConcert(concertId, maxSeats);
	}

	//concert 삭제
	public boolean deleteConcert(int concertId) throws SQLException, NotExistException{
		notExistConcert(concertId);
		boolean result = concertDAO.deleteConcert(concertId);
		if(!result){
			throw new NotExistException("concert 정보 삭제 실패");
		}
		return concertDAO.deleteConcert(concertId);
	}
		
	
	
	//ConcertSinger - CRUD
	public static void notExistConcertSinger(String concertSingerId) throws NotExistException, SQLException{
		ConcertSingerDTO concertSinger = concertSingerDAO.getConcertSinger(concertSingerId);
		if(concertSinger == null){
			throw new NotExistException("검색하는 콘서트-가수가 미 존재합니다.");
		}
	}
	
	
	public boolean addConcertSinger(ConcertSingerDTO concertSinger) throws MessageException{
		boolean result = false;
		try{
			result = concertSingerDAO.addConcertSinger(concertSinger);
		}catch(SQLException s){
			throw new MessageException("이미 존재하는 ID입니다 다시 시도 하세요.");
		}
		return result;
	}
	
	public boolean deleteConcertSinger(String concertSingerId) throws SQLException, NotExistException{
		notExistConcertSinger(concertSingerId);
		boolean result = concertSingerDAO.deleteConcertSinger(concertSingerId);
		if(!result){
			throw new NotExistException("콘서트-가수 정보 삭제 실패");
		}
		return result;
	}
	
	public ConcertSingerDTO getConcertSinger(String concertSingerId) throws SQLException, NotExistException{
		ConcertSingerDTO concertSinger = concertSingerDAO.getConcertSinger(concertSingerId);
		if(concertSinger == null){
			throw new NotExistException("검색하는 콘서트-가수가 미 존재합니다.");
		}
		return concertSinger;
	}
	
	public ArrayList<ConcertSingerDTO> getAllconcertSinger() throws SQLException{
		return concertSingerDAO.getAllconcertSinger();
	}
	
	
	
	//ordersDAO - CRUD
	public static void notExistOrders(int orderId) throws NotExistException, SQLException{
		OrdersDTO order = ordersDAO.getOrder(orderId);
		if(order == null){
			throw new NotExistException("검색하는 주문이 존재합니다.");
		}
	}
	
	public boolean addOrders(OrdersDTO orders) throws SQLException{
		return ordersDAO.addOrders(orders);
	}

	public boolean deleteOrders(int orderId) throws SQLException, NotExistException{
		notExistOrders(orderId);
		return ordersDAO.deleteOrders(orderId);
	}
	public OrdersDTO getOrder(int orderId) throws SQLException{
		return ordersDAO.getOrder(orderId);
	}
	public List<OrdersDTO> getAllOrders() throws SQLException{
		return ordersDAO.getAllOrders();
	}
	
	
	//SingerDAO - CRUD
	public static void notExistSinger(String singerId) throws NotExistException, SQLException{
		SingerDTO singer = singerDAO.getSinger(singerId);
		if(singer == null){
			throw new NotExistException("검색하는 가수가 미 존재합니다.");
		}
	}
	
	public static boolean addSinger(SingerDTO singer) throws SQLException{
		return singerDAO.addSinger(singer);
	}
	
	public static boolean deleteSinger(String singerId) throws SQLException, NotExistException{
		notExistSinger(singerId);
		return singerDAO.deleteSinger(singerId);
	}
	
	public static SingerDTO getSinger(String singerId) throws SQLException, NotExistException{
		SingerDTO singer = singerDAO.getSinger(singerId);
		if(singer == null){
			throw new NotExistException("검색하는 가수가 미 존재합니다.");
		}
		return singer;
	}
	
	public static boolean updateSinger(String singerId, String singerDetail) throws SQLException, NotExistException{
		notExistSinger(singerId);
		return singerDAO.updateSinger(singerId, singerDetail);
	}
	
	public ArrayList<SingerDTO> getAllSingers() throws SQLException,NotExistException{
		 ArrayList<SingerDTO> singers = singerDAO.getAllSingers();
		if(singers == null){
			throw new NotExistException("가수가 미 존재합니다.");
		}
		return singers;
	}
	public ArrayList<SingerDTO> getSomeConcertSinger() throws SQLException,NotExistException{
		ArrayList<SingerDTO> singers = singerDAO.getSomeConcertSinger();
		if(singers == null){
			throw new NotExistException("가수가 미 존재합니다.");
		}
		return singers;
	}

}
