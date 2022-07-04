package probono.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import probono.exception.MessageException;
import probono.exception.NotExistException;
import probono.model.dto.ActivistDTO;
import probono.model.dto.ProbonoDTO;
import probono.model.dto.ProbonoProjectDTO;
import probono.model.dto.RecipientDTO;

public class ProbonoService {

	private static ProbonoService instance = new ProbonoService();

	private ProbonoService() {
	}

	public static ProbonoService getInstance() {
		return instance;
	}
	
	private static ActivistDAO activistDAO = ActivistDAO.getInstance();
	private static ProbonoDAO probonoDAO = ProbonoDAO.getInstance();
	private static ProbonoProjectDAO probonoProjectDAO = ProbonoProjectDAO.getInstance();
	private static RecipientDAO recipientDAO = RecipientDAO.getInstance();
	
	//Probono - CRUD
	public static void notExistProbono(String probonoId) throws NotExistException, SQLException{
		ProbonoDTO probono = probonoDAO.getProbono(probonoId);
		if(probono == null){
			throw new NotExistException("검색하진 재능기부 정보가 없습니다.");
		}
	}
	
	//모든 probono 정보 반환
	public static ArrayList<ProbonoDTO> getAllProbonos() throws SQLException{
		return probonoDAO.getAllProbonos();
	}
	//probono id로 검색
	public static ProbonoDTO getProbono(String probonoId) throws SQLException, NotExistException{
		ProbonoDTO probono = probonoDAO.getProbono(probonoId);
		if(probono == null){
			throw new NotExistException("검색하신 재능기부 정보가 없습니다.");
		}
		return probono;
	}
	//새로운 probono 저장
	public static boolean addProbono(ProbonoDTO probono) throws SQLException{
		return probonoDAO.addProbono(probono);
	}
	//기존 probono 수정
	public static boolean updateProbono(String probonoId, String probonoPurpose) throws SQLException, NotExistException{
		notExistProbono(probonoId);
		return probonoDAO.updateProbono(probonoId, probonoPurpose);
	}
	//probono 삭제
	public boolean deleteProbono(String probonoId) throws SQLException, NotExistException{
		notExistProbono(probonoId);
		return probonoDAO.deleteProbono(probonoId);
	}
		
	
	
	//Activist - CRUD
	public static void notExistActivist(String activistId) throws NotExistException, SQLException{
		ActivistDTO activist = activistDAO.getActivist(activistId);
		if(activist == null){
			throw new NotExistException("검색하는 재능 기부자가 미 존재합니다.");
		}
	}
	public boolean addActivist(ActivistDTO activist) throws MessageException{
		boolean result = false;
		try{
			result = activistDAO.addActivist(activist);
		}catch(SQLException s){
			throw new MessageException("이미 존재하는 ID입니다 다시 시도 하세요");
		}
		return result;
	}
	
	//재능 기부자 수정 메소드[ActivistDAO의 updateActivist()]
	public boolean updateActivist(String activistId, String major) throws SQLException, NotExistException{		
		notExistActivist(activistId);
		boolean result = activistDAO.updateActivist(activistId, major);
		if(!result){
			throw new NotExistException("재능 기부자 정보 갱신 실패");
		}
		return result;
	}
	
	
	//재능 기부자 삭제 메소드[ ActivistDAO.deleteActivist()]
	public boolean deleteActivist(String activistId) throws SQLException, NotExistException{
		notExistActivist(activistId);
		boolean result = activistDAO.deleteActivist(activistId);
		if(!result){
			throw new NotExistException("재능 기부자 정보 삭제 실패");
		}
		return result;
	}
	
	public ActivistDTO getActivist(String activistId) throws SQLException, NotExistException{
		ActivistDTO activist = activistDAO.getActivist(activistId);
		if(activist == null){
			throw new NotExistException("검색하는 재능 기부자가 미 존재합니다.");
		}
		return activist;
	}
	
	public ArrayList<ActivistDTO> getAllActivists() throws SQLException{
		return activistDAO.getAllActivists();
	}
	
	//Recipient - CRUD
	public static void notExistRecipient(String recipientId) throws NotExistException, SQLException{
		RecipientDTO recipient = recipientDAO.getRecipient(recipientId);
		if(recipient == null){
			throw new NotExistException("검색하는 재능 수해자가 미 존재합니다.");
		}
	}
	public boolean addRecipient(RecipientDTO recipient) throws SQLException{
		return recipientDAO.addRecipient(recipient);
	}
	public boolean updateRecipient(String recipientId, String receiveHopeContent) throws SQLException, NotExistException{
		notExistRecipient(recipientId);
		return recipientDAO.updateRecipient(recipientId, receiveHopeContent);
	}
	public boolean deleteRecipient(String recipientId) throws SQLException, NotExistException{
		notExistRecipient(recipientId);
		return recipientDAO.deleteRecipient(recipientId);
	}
	public RecipientDTO getRecipient(String recipientId) throws SQLException{
		return recipientDAO.getRecipient(recipientId);
	}
	public ArrayList<RecipientDTO> getAllRecipients() throws SQLException{
		return recipientDAO.getAllRecipients();
	}
	
	
	//ProjectProjectDAO - CRUD
	public static void notExistProbonoProject(int probonoProjectId) throws NotExistException, SQLException{
		ProbonoProjectDTO probonoProject = probonoProjectDAO.getProbonoProject(probonoProjectId);
		if(probonoProject == null){
			throw new NotExistException("검색하는 재능기부 프로젝트가 미 존재합니다.");
		}
	}
	
	//새로운 Probono Project 등록
	public static boolean addProbonoProject(ProbonoProjectDTO probonoProject) throws SQLException{
		return probonoProjectDAO.addProbonoProject(probonoProject);
	}
	//존재하는 Probono Project의 재능 기부자 수정
	public static boolean updateProbonoProjectActivist(int probonoProjectId, String activistId) throws SQLException, NotExistException{
		notExistProbonoProject(probonoProjectId);
		return probonoProjectDAO.updateProbonoProjectActivist(probonoProjectId, activistId);
	}
	
	//존재하는 Probono Project의 재능 수해자 수정
	public static boolean updateProbonoProjectReceive(int probonoProjectId, String  receiveId) throws SQLException, NotExistException{
		notExistProbonoProject(probonoProjectId);
		return probonoProjectDAO.updateProbonoProjectReceive(probonoProjectId, receiveId);
	}
	
	//존재하는 Probono Project 삭제
	public static boolean deleteProbonoProject(int probonoProjectId) throws SQLException, NotExistException{
		notExistProbonoProject(probonoProjectId);
		return probonoProjectDAO.deleteProbonoProject(probonoProjectId);
	}
	
	//특정 Probono Project 검색 
	public static ProbonoProjectDTO getProbonoProject(int probonoProjectId) throws SQLException, NotExistException{
		ProbonoProjectDTO probonoProject = probonoProjectDAO.getProbonoProject(probonoProjectId);
		if(probonoProject == null){
			throw new NotExistException("검색하는 재능기부 프로젝트가 미 존재합니다.");
		}
		return probonoProject;
	}
	
	//모든 Probono Project 검색 
	public ArrayList<ProbonoProjectDTO> getAllProbonoProjects() throws SQLException,NotExistException{
		ArrayList<ProbonoProjectDTO> probonoProjectAll = probonoProjectDAO.getAllProbonoProjects();
		if(probonoProjectAll == null){
			throw new NotExistException("검색하는 재능기부 프로젝트가 미 존재합니다.");
		}
		return probonoProjectAll;
	}
}
