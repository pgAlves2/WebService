package br.com.ibring.model.purchase;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;

import br.com.ibring.model.request.Request;
import br.com.ibring.model.user.User;
import br.com.ibring.util.DAOFactory;


public class PurchaseService {

	private PurchaseDAO	purchaseDAO;	
	
	public PurchaseService() {
		this.purchaseDAO = DAOFactory.createPurchaseDAO();
	}
	
	public PurchaseService(Session session) {
		purchaseDAO = new PurchaseDAOHibernate();
		((PurchaseDAOHibernate)purchaseDAO).setSession(session);
	}
	
	public Purchase load(Integer id) {
		return this.purchaseDAO.load(id);
	}
	
	public void save(Purchase purchase) {
		Integer codigo = purchase.getId();
		if (codigo == null || codigo == 0) {
			this.purchaseDAO.save(purchase);
		} else {
			this.purchaseDAO.update(purchase);
		}
	}
	
	public void delete(Purchase purchase) {
		this.purchaseDAO.delete(purchase);
	}

	public List<Purchase> list() {
		return this.purchaseDAO.list();
	}
	
	public List<Purchase> listMatch(Request request) {
		return this.purchaseDAO.listMatch(request);
	}
	
	public List<Purchase> listOpen(User user, Timestamp date){
		return this.purchaseDAO.listOpen(user, date);
	}
	
}
