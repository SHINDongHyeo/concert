package concert.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import concert.model.dto.SingerDTO;

@WebServlet("/concert")
private class ConcertController extends HttpServlet {
	private static ConcertService service = ConcertService.getInstance();

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
			addConcert(request, response);
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
		if (request.getParameter("concertId") == null) {
			request.setAttribute("errorMsg", "검색하려는 콘서트 id값으로 null이나 공백이 입력되었습니다. 다시 입력해주세요");
		} else {
			try {
				request.setAttribute("getConcert", service.getConcert());
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
		if (request.getParameter("concertId") == null) {
			request.setAttribute("errorMsg", "삭제하려는 콘서트 id값으로 null이나 공백이 입력되었습니다. 다시 입력해주세요");
		} else {
			try {
				request.setAttribute("deleteConcert", service.deleteConcert());
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
			throws ServletException, IOException {
		String url = "showError.jsp";
		if (request.getParameter("concert") == null) {
			request.setAttribute("errorMsg", "삭제하려는 콘서트 id값으로 null이나 공백이 입력되었습니다. 다시 입력해주세요");
		} else {
			try {
				request.setAttribute("addConcert", service.addConcert());
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
		if (request.getParameter("concertId") == null) {
			request.setAttribute("errorMsg", "수정하려는 콘서트 id값으로 null이나 공백이 입력되었습니다. 다시 입력해주세요");
		} else {
			try {
				request.setAttribute("updateConcert", service.updateConcert());
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
		if (request.getParameter("orders") == null) {
			request.setAttribute("errorMsg", "추가하려는 주문내역값으로 null이나 공백이 입력되었습니다. 다시 입력해주세요");
		} else {
			try {
				request.setAttribute("addOrders", service.addOrders());
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
		if (request.getParameter("orderId") == null) {
			request.setAttribute("errorMsg", "삭제하려는 주문내역 id값으로 null이나 공백이 입력되었습니다. 다시 입력해주세요");
		} else {
			try {
				request.setAttribute("deleteOrders", service.deleteOrders());
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
			if(a != null) {
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
		
			SingerDTO singer = new SingerDTO(id, name, detail);
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
		try {
			if(service.deleteSinger(request.getParameter("singerId"))) {
				request.setAttribute("singerAll", service.deleteSinger());
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
		try {
			if(service.deleteSinger(request.getParameter("singerId"))) {
				request.setAttribute("singerAll", service.deleteSinger());
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
		if (request.getParameter("concertSinger") == null) {
			request.setAttribute("errorMsg", "추가하려는 콘서트싱어 id값으로 null이나 공백이 입력되었습니다. 다시 입력해주세요");
		} else {
			try {
				request.setAttribute("addConcertSinger", service.addConcertSinger());
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
		if (request.getParameter("concertSingerId") == null) {
			request.setAttribute("errorMsg", "삭제하려는 콘서트싱어 id값으로 null이나 공백이 입력되었습니다. 다시 입력해주세요");
		} else {
			try {
				request.setAttribute("deleteConcertSinger", service.addConcdeleteConcertSingerertSinger());
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
		if (request.getParameter("concertSingerId") == null) {
			request.setAttribute("errorMsg", "검색하려는 콘서트싱어 id값으로 null이나 공백이 입력되었습니다. 다시 입력해주세요");
		} else {
			try {
				request.setAttribute("getConcertSinger", service.getConcertSinger());
				url = "showSuccess.jsp";
			} catch (Exception e) {
				request.setAttribute("errMsg", e.getMessage());
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher(url).forward(request, response);
	
	

}
