package br.com.ibring.model.agreement;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.ibring.model.purchase.Purchase;
import br.com.ibring.model.request.Request;

public class AgreementDAOHibernate implements AgreementDAO{

	private Session	session;

	public void setSession(Session session) {
		this.session = session;
	}
		
	
	public void save(Agreement agreement) {
		this.session.save(agreement);
	}

	
	public void update(Agreement agreement) {
		this.session.update(agreement);
	}

	
	public void delete(Agreement agreement) {
		this.session.delete(agreement);
	}

	
	public Agreement load(Integer id) {
		return (Agreement) this.session.get(Agreement.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Agreement> list() {
		return this.session.createCriteria(Agreement.class).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Agreement> list(Request request){
		
		String hql = "select agreement from Agreement as agreement"+
				     " join agreement.request as request"+
				     " where agreement.request.id = :id";
		
		Query query = this.session.createQuery(hql);	
		query.setInteger("id", request.getId());
		
		return (List<Agreement>) query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Agreement> list(Purchase purchase){
		
		String hql = "select agreement from Agreement as agreement"+
				     " join agreement.purchase as purchase"+
				     " where agreement.purchase.id = :id";
		
		Query query = this.session.createQuery(hql);	
		query.setInteger("id", purchase.getId());
		
		return (List<Agreement>) query.list();
	}
	
}
