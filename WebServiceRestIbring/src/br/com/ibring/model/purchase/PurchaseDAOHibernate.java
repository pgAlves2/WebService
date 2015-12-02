package br.com.ibring.model.purchase;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.ibring.model.request.Request;
import br.com.ibring.model.user.User;
import br.com.ibring.util.TimestampAdapter;

public class PurchaseDAOHibernate implements PurchaseDAO{

	private Session	session;

	public void setSession(Session session) {
		this.session = session;
	}
		
	
	public void save(Purchase purchase) {
		this.session.save(purchase);
	}

	
	public void update(Purchase purchase) {
		this.session.update(purchase);
	}

	
	public void delete(Purchase purchase) {
		this.session.delete(purchase);
	}

	
	public Purchase load(Integer id) {
		return (Purchase) this.session.get(Purchase.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Purchase> list() {
		return this.session.createCriteria(Purchase.class).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Purchase> listMatch(Request request){
		
		String hql = "select purchase from Purchase as purchase"+
				     " join purchase.user as user"+
				     " where purchase.typeStore = :typeStore"+
				     " and purchase.datetime between :date_after and :date_later"+
				     " and user.latitude between min_lat(user.latitude, 1.0) and max_lat(user.latitude, 1.0)"+
				     " and user.longitude between min_lng(user.latitude, user.longitude, 1.0) and max_lng(user.latitude, user.longitude, 1.0)"+
				     " and distance_between(user.latitude, user.longitude, :req_latitude, :req_longitude) <= 1";
		
	    Calendar calendar = Calendar.getInstance();
	    
	    calendar.setTimeInMillis(request.getDate().getTime());
	    calendar.add(Calendar.MINUTE, 45);
	    Timestamp later = new Timestamp(calendar.getTime().getTime());
	    
	    calendar.setTimeInMillis(request.getDate().getTime());
	    calendar.add(Calendar.MINUTE, -45);
	    Timestamp after = new Timestamp(calendar.getTime().getTime());
		
		Query query = this.session.createQuery(hql);
		
		query.setInteger("typeStore", request.getTypeStore());
		query.setString("date_after", new SimpleDateFormat(TimestampAdapter.FORMAT).format(after));
		query.setString("date_later", new SimpleDateFormat(TimestampAdapter.FORMAT).format(later));
		query.setDouble("req_latitude", request.getUser().getLatitude());
		query.setDouble("req_longitude", request.getUser().getLongitude());
		
		return (List<Purchase>) query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Purchase> listOpen(User user, Timestamp date){
		
		String hql = "select purchase from Purchase as purchase"+
				     " join purchase.user as user"+
				     " where user.id = :id"+
				     " and purchase.datetime > :date"+
				     " or purchase.id in (select agreement.purchase.id from Agreement as agreement"+
							               " join agreement.purchase as purchase"+
							               " where agreement.requestAccept = true"+
							               " and agreement.purchaseAccept = true)";
		
		Query query = this.session.createQuery(hql);	
		query.setInteger("id", user.getId());
		query.setString("date", new SimpleDateFormat(TimestampAdapter.FORMAT).format(date));
		
		return (List<Purchase>) query.list();
	}



}
