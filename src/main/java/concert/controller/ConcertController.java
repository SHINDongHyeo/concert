
package concert.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import concert.model.dao.ConcertService;
import concert.model.dto.ConcertDTO;

import concert.model.dto.ConcertSingerDTO;

import concert.model.dto.OrdersDTO;
import concert.model.dto.SingerDTO;

@WebServlet("/concert")
public class ConcertController extends HttpServlet {

	private static ConcertService service = ConcertService.getInstance();
	
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

///////////////////////////////////////////////////////////////////////////////////////////////
	// COMMAND에 따라 분류
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String command = request.getParameter("command");

		switch (command) {
		case "getConcert":
			getConcert(request, response);
			break;
		case "getAllConcert":
			getAllConcert(request, response);
			break;
		case "deleteConcert":
			deleteConcert(request, response);
			break;
		case "addConcert":
			try {
				addConcert(request, response);
			} catch (ServletException | IOException | ParseException e) {
				e.printStackTrace();
			}
			break;
		case "updateConcert":
			updateConcert(request, response);
			break;
		case "addOrders":
			addOrders(request, response);
			break;
		case "deleteOrders":
			deleteOrders(request, response);
			break;
		case "getAllOrders":
			getAllOrders(request, response);
			break;
		case "addSinger":
			addSinger(request, response);
			break;
		case "updateSinger":
			updateSinger(request, response);
			break;
		case "deleteSinger":
			deleteSinger(request, response);
			break;
		case "getSinger":
			getSinger(request, response);
			break;
		case "getAllSingers":
			getAllSingers(request, response);
			break;		
		case "getAllconcertSinger":
			getAllconcertSinger(request, response);
			break;
		case "addConcertSinger":
			addConcertSinger(request, response);
			break;
		case "deleteConcertSinger":
			deleteConcertSinger(request, response);
			break;
		case "getConcertSinger":
			getConcertSinger(request, response);
			break;
		default:
			break;
		}
	}
///////////////////////////////////////////////////////////////////////////////////////////////

	/** Concert **/
	// 한 콘서트 검색
	private void getConcert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "showError.jsp";
		String concertId = request.getParameter("concertId");
		if (concertId == null) {
			request.setAttribute("errorMsg", "검색하려는 콘서트 id값으로 null이나 공백이 입력되었습니다. 다시 입력해주세요");
		} else {
			try {
				request.setAttribute("getConcert", service.getConcert(Integer.parseInt(concertId)));
				url = "showSuccess.jsp";
			} catch (Exception e) {
				request.setAttribute("errMsg", e.getMessage());
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	// 모든 콘서트 검색
	private void getAllConcert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			request.setAttribute("getAllConcert", service.getAllConcert());
			url = "showSuccess.jsp";
		} catch (Exception e) {
			request.setAttribute("errMsg", e.getMessage());
			e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	// 콘서트 삭제
	private void deleteConcert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "showError.jsp";
		String concertId = request.getParameter("concertId");
		if (concertId == null) {
			request.setAttribute("errorMsg", "삭제하려는 콘서트 id값으로 null이나 공백이 입력되었습니다. 다시 입력해주세요");
		} else {
			try {
				request.setAttribute("deleteConcert", service.deleteConcert(Integer.parseInt(concertId)));
				url = "showSuccess.jsp";
			} catch (Exception e) {
				request.setAttribute("errMsg", e.getMessage());
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	// 콘서트 추가
	private void addConcert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		String url = "showError.jsp";
		int id = Integer.parseInt(request.getParameter("concertId"));
		String name = request.getParameter("concertName");
		String loc = request.getParameter("location");
		Date date = formatter.parse(request.getParameter("date"));
		int maxSeats = Integer.parseInt(request.getParameter("maxSeats"));
		String contents = request.getParameter("contents");
		
		if (id == 0) {
			request.setAttribute("errorMsg", "삭제하려는 콘서트 id값으로 null이나 공백이 입력되었습니다. 다시 입력해주세요");
		} else {
			try {
				ConcertDTO concert = ConcertDTO.builder().concertId(id).concertName(name)
						.location(loc).date(date).maxSeats(maxSeats)
						.contents(contents).build();
				request.setAttribute("addConcert", service.addConcert(concert));
				url = "showSuccess.jsp";
			} catch (Exception e) {
				request.setAttribute("errMsg", e.getMessage());
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	// 콘서트 수정
	private void updateConcert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "showError.jsp";
		
		int id = Integer.parseInt(request.getParameter("concertId"));
		int maxSeats = Integer.parseInt(request.getParameter("maxSeats"));
		
		if (id == 0) {
			request.setAttribute("errorMsg", "수정하려는 콘서트 id값으로 null이나 공백이 입력되었습니다. 다시 입력해주세요");
		} else {
			try {
				request.setAttribute("updateConcert", service.updateConcert(id, maxSeats));
				url = "showSuccess.jsp";
			} catch (Exception e) {
				request.setAttribute("errMsg", e.getMessage());
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	
	/** Orders **/
	// 한 주문내역 추가
	private void addOrders(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "showError.jsp";
		int id = Integer.parseInt(request.getParameter("orderId"));
		String name = request.getParameter("customerName");
		String email = request.getParameter("customerEmail");
		int concertId = Integer.parseInt(request.getParameter("concertId"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		
		OrdersDTO order = OrdersDTO.builder().orderId(id).customerName(name)
							.customerEmail(email).concertId(concertId)
							.amount(amount).build();
		
		if (id == 0) {
			request.setAttribute("errorMsg", "추가하려는 주문내역값으로 null이나 공백이 입력되었습니다. 다시 입력해주세요");
		} else {
			try {
				request.setAttribute("addOrders", service.addOrders(order));
				url = "showSuccess.jsp";
			} catch (Exception e) {
				request.setAttribute("errMsg", e.getMessage());
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	// 한 주문내역 삭제
	private void deleteOrders(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "showError.jsp";
		int id = Integer.parseInt(request.getParameter("orderId"));

		if (id == 0) {
			request.setAttribute("errorMsg", "삭제하려는 주문내역 id값으로 null이나 공백이 입력되었습니다. 다시 입력해주세요");
		} else {
			try {
				request.setAttribute("deleteOrders", service.deleteOrders(id));
				url = "showSuccess.jsp";
			} catch (Exception e) {
				request.setAttribute("errMsg", e.getMessage());
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	// 모든 주문내역 검색
	private void getAllOrders(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			request.setAttribute("getAllOrders", service.getAllOrders());
			url = "showSuccess.jsp";
		} catch (Exception e) {
			request.setAttribute("errMsg", e.getMessage());
			e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	/** Singer **/
	// 모든 Singer 검색 
	private void getAllSingers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			request.setAttribute("singerAll", service.getAllSingers());
			url = "singerList.jsp";
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	// 한 Singer 검색 
	private void getSinger(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			SingerDTO s = service.getSinger(request.getParameter("singerId"));
			if(s != null) {
				request.setAttribute("singer", s);
				url = "singer/singerDetail.jsp";
			}else {
				request.setAttribute("errorMsg", "존재하지 않는 가수입니다.");
			}
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	// 한 Singer 추가
	protected void addSinger(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		
		String id = request.getParameter("singerId");
		String name = request.getParameter("singerName");
		String detail = request.getParameter("singerDetail");
		
		//해킹등으로 불합리하게 요청도 될수 있다는 가정하에 모든 데이터가 제대로 전송이 되었는지를 검증하는 로직
		if(id != null && id.length() !=0 && name != null) {
		

			SingerDTO singer = SingerDTO.builder()
										.singerId(Integer.parseInt(id))
										.singerName(name)
										.detail(detail)
										.build();

			try{
				boolean result = service.addSinger(singer);
				if(result){
					request.setAttribute("singer", singer);
					request.setAttribute("successMsg", "가입 완료");
					url = "singer/singerDetail.jsp";
				}else{
					request.setAttribute("errorMsg", "다시 시도하세요");
				}
			}catch(Exception s){
				request.setAttribute("errorMsg", s.getMessage());
			}
			request.getRequestDispatcher(url).forward(request, response);
		}
	}

	// 한 Singer 삭제
	private void deleteSinger(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		String singerId = request.getParameter("singerId");
		try {
			if(service.deleteSinger(singerId)) {
				request.setAttribute("singerAll", service.deleteSinger(singerId));
				url = "activist/activistList.jsp";
			}else {
				request.setAttribute("errorMsg", "저장 실패");
			}
			getAllSingers(request, response);
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	// 한 Singer 수정
	private void updateSinger(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		String singerId = request.getParameter("singerId");
		String singerDetail = request.getParameter("singerDetail");
		try {
			if(service.updateSinger(singerId,singerDetail)) {
				request.setAttribute("singerAll", service.updateSinger(singerId,singerDetail));
				url = "activist/activistList.jsp";
			}else {
				request.setAttribute("errorMsg", "저장 실패");
			}
			getAllSingers(request, response);
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	/** ConcertSinger **/
	// 모든 콘서트싱어 검색
	private void getAllconcertSinger(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			request.setAttribute("getAllconcertSinger", service.getAllconcertSinger());
			url = "showSuccess.jsp";
		} catch (Exception e) {
			request.setAttribute("errMsg", e.getMessage());
			e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	// 한 콘서트싱어 추가
	private void addConcertSinger(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "showError.jsp";
		String concertSingerId = request.getParameter("concertSingerId");
		String concertId = request.getParameter("concertId");
		String singerId = request.getParameter("singerId");
		ConcertSingerDTO concertSinger = ConcertSingerDTO.builder()
											.concertSingerId(Integer.parseInt(concertSingerId))
											.concertId(Integer.parseInt(concertId))
											.singerId(Integer.parseInt(singerId))
											.build();
							
		if (concertSinger == null) {
			request.setAttribute("errorMsg", "추가하려는 콘서트싱어 id값으로 null이나 공백이 입력되었습니다. 다시 입력해주세요");
		} else {
			try {
				request.setAttribute("addConcertSinger", service.addConcertSinger(concertSinger));
				url = "showSuccess.jsp";
			} catch (Exception e) {
				request.setAttribute("errMsg", e.getMessage());
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	// 모든 콘서트싱어 검색
	private void deleteConcertSinger(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "showError.jsp";
		String concertSingerId = request.getParameter("concertSingerId");
		if (concertSingerId == null) {
			request.setAttribute("errorMsg", "삭제하려는 콘서트싱어 id값으로 null이나 공백이 입력되었습니다. 다시 입력해주세요");
		} else {
			try {
				request.setAttribute("deleteConcertSinger", service.deleteConcertSinger(concertSingerId));
				url = "showSuccess.jsp";
			} catch (Exception e) {
				request.setAttribute("errMsg", e.getMessage());
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	// 한 콘서트싱어 검색
	private void getConcertSinger(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "showError.jsp";
		String concertSingerId = request.getParameter("concertSingerId");
		if (concertSingerId == null) {
			request.setAttribute("errorMsg", "검색하려는 콘서트싱어 id값으로 null이나 공백이 입력되었습니다. 다시 입력해주세요");
		} else {
			try {
				request.setAttribute("getConcertSinger", service.getConcertSinger(concertSingerId));
				url = "showSuccess.jsp";
			} catch (Exception e) {
				request.setAttribute("errMsg", e.getMessage());
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher(url).forward(request, response);


	}
}

