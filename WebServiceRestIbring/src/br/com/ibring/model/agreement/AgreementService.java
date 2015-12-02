package br.com.ibring.model.agreement;

import java.util.List;

import org.hibernate.Session;

import br.com.ibring.model.purchase.Purchase;
import br.com.ibring.model.request.Request;
import br.com.ibring.util.DAOFactory;


public class AgreementService {

	private AgreementDAO agreementDAO;	
	
	public AgreementService() {
		this.agreementDAO = DAOFactory.createAgreementDAO();
	}
	
	public AgreementService(Session session) {
		agreementDAO = new AgreementDAOHibernate();
		((AgreementDAOHibernate)agreementDAO).setSession(session);
	}
	
	public Agreement load(Integer id) {
		return this.agreementDAO.load(id);
	}
	
	public void save(Agreement agreement) {
		Integer codigo = agreement.getId();
		if (codigo == null || codigo == 0) {
			this.agreementDAO.save(agreement);
			//
		} else {
			this.agreementDAO.update(agreement);
		}
	}
	
	public void delete(Agreement agreement) {
		this.agreementDAO.delete(agreement);
	}

	public List<Agreement> listar() {
		return this.agreementDAO.list();
	}
	
	public List<Agreement> list(Request request){
		return this.agreementDAO.list(request);
	}
	
	public List<Agreement> list(Purchase purchase){
		return this.agreementDAO.list(purchase);
	}
	
}
