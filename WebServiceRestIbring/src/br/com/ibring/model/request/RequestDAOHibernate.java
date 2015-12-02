package br.com.ibring.model.request;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.ibring.model.purchase.Purchase;
import br.com.ibring.model.user.User;
import br.com.ibring.util.TimestampAdapter;

public class RequestDAOHibernate implements RequestDAO{

	private Session	session;

	public void setSession(Session session) {
		this.session = session;
	}
		
	
	public void save(Request request) {
		this.session.save(request);
	}

	
	public void update(Request request) {
		this.session.update(request);
	}

	
	public void delete(Request request) {
		this.session.delete(request);
	}

	
	public Request load(Integer id) {
		return (Request) this.session.get(Request.class, id);
	}

	
	@SuppressWarnings("unchecked")
	public List<Request> list() {
		return this.session.createCriteria(Request.class).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Request> listMatch(Purchase purchase){
		
		String hql = "select request from Request as request"+
				     " join request.user as user"+
				     " where request.typeStore = :typeStore"+
				     " and request.datetime between :date_after and :date_later"+
				     " and user.latitude between min_lat(user.latitude, 1.0) and max_lat(user.latitude, 1.0)"+
				     " and user.longitude between min_lng(user.latitude, user.longitude, 1.0) and max_lng(user.latitude, user.longitude, 1.0)"+
				     " and request.id not in (select agreement.request.id from Agreement as agreement"+
										       " join agreement.request as request"+
								               " where (agreement.requestAccept = true"+
								               " and agreement.purchaseAccept = true)"+
								               " or (agreement.requestAccept = true"+
								               " and agreement.purchaseView = false))"+    
				     " and distance_between(user.latitude, user.longitude, :pur_latitude, :pur_longitude) <= 1";
		
		Calendar calendar = Calendar.getInstance();
		
	    calendar.setTimeInMillis(purchase.getDate().getTime());
	    calendar.add(Calendar.MINUTE, 45);
	    Timestamp later = new Timestamp(calendar.getTime().getTime());
	    
	    calendar.setTimeInMillis(purchase.getDate().getTime());
	    calendar.add(Calendar.MINUTE, -45);
	    Timestamp after = new Timestamp(calendar.getTime().getTime());
		
		Query query = this.session.createQuery(hql);
		query.setInteger("typeStore", purchase.getTypeStore());
		query.setString("date_after", new SimpleDateFormat(TimestampAdapter.FORMAT).format(after));
		query.setString("date_later", new SimpleDateFormat(TimestampAdapter.FORMAT).format(later));
		query.setDouble("pur_latitude", purchase.getUser().getLatitude());
		query.setDouble("pur_longitude", purchase.getUser().getLongitude());
		
		return (List<Request>) query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Request> listOpen(User user, Timestamp date){
		
		String hql = "select request from Request as request"+
				     " join request.user as user"+
				     " where user.id = :id"+
				     " and request.datetime > :date"+
				     " or request.id in (select agreement.request.id from Agreement as agreement"+
						               " join agreement.request as request"+
						               " where agreement.requestAccept = true"+
						               " and agreement.purchaseAccept = true)";
		
		Query query = this.session.createQuery(hql);	
		query.setInteger("id", user.getId());
		query.setString("date", new SimpleDateFormat(TimestampAdapter.FORMAT).format(date));
		
		return (List<Request>) query.list();
	}

}
