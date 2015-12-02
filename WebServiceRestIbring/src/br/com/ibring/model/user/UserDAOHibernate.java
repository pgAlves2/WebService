package br.com.ibring.model.user;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class UserDAOHibernate implements UserDAO{

	private Session	session;

	public void setSession(Session session) {
		this.session = session;
	}
		
	
	public void save(User user) {
		this.session.save(user);
	}

	
	public void update(User user) {
		this.session.update(user);
	}

	
	public void delete(User user) {
		this.session.delete(user);
	}

	
	public User load(Integer id) {
		return (User) this.session.get(User.class, id);
	}

	@Override
	public User FindByFaceId(String faceId){
		String sql = "select u from User u where u.faceId = :id";
		Query query = this.session.createQuery(sql);
		query.setString("id", faceId);
		return (User) query.uniqueResult();
	}

	
	@SuppressWarnings("unchecked")
	public List<User> list() {
		return this.session.createCriteria(User.class).list();
	}



}
