package br.com.ibring.model.request;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;

import br.com.ibring.model.purchase.Purchase;
import br.com.ibring.model.user.User;
import br.com.ibring.util.DAOFactory;


public class RequestService {

	private RequestDAO	requestDAO;	
	
	public RequestService() {
		this.requestDAO = DAOFactory.createRequestDAO();
	}
	
	public RequestService(Session session) {
		requestDAO = new RequestDAOHibernate();
		((RequestDAOHibernate)requestDAO).setSession(session);
	}
	
	public Request load(Integer id) {
		return this.requestDAO.load(id);
	}
	
	public void save(Request request) {
		Integer codigo = request.getId();
		if (codigo == null || codigo == 0) {
			this.requestDAO.save(request);
		} else {
			this.requestDAO.update(request);
		}
	}
	
	public void delete(Request request) {
		this.requestDAO.delete(request);
	}

	public List<Request> list() {
		return this.requestDAO.list();
	}
	
	public List<Request> listMatch(Purchase purchase){
		return this.requestDAO.listMatch(purchase);
	}
	
	public List<Request> listOpen(User user, Timestamp date){
		return this.requestDAO.listOpen(user, date);
	}
	
}
