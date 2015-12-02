package br.com.ibring.util;

import br.com.ibring.model.agreement.AgreementDAO;
import br.com.ibring.model.agreement.AgreementDAOHibernate;
import br.com.ibring.model.purchase.PurchaseDAO;
import br.com.ibring.model.purchase.PurchaseDAOHibernate;
import br.com.ibring.model.request.RequestDAO;
import br.com.ibring.model.request.RequestDAOHibernate;
import br.com.ibring.model.user.UserDAO;
import br.com.ibring.model.user.UserDAOHibernate;

public class DAOFactory {

	public static UserDAO createUserDAO() {
		UserDAOHibernate userDAO = new UserDAOHibernate();
		userDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return userDAO;
	}
	
	public static RequestDAO createRequestDAO() {
		RequestDAOHibernate requestDAO = new RequestDAOHibernate();
		requestDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return requestDAO;
	}
	
	public static PurchaseDAO createPurchaseDAO() {
		PurchaseDAOHibernate purchaseDAO = new PurchaseDAOHibernate();
		purchaseDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return purchaseDAO;
	}
	
	public static AgreementDAO createAgreementDAO() {
		AgreementDAOHibernate agreementDAO = new AgreementDAOHibernate();
		agreementDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return agreementDAO;
	}	
	
}
