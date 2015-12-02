package br.com.ibring.model.request;

import java.sql.Timestamp;
import java.util.List;

import br.com.ibring.model.purchase.Purchase;
import br.com.ibring.model.user.User;

public interface RequestDAO {

	public void save(Request request);

	public void update(Request request);

	public void delete(Request request);

	public Request load(Integer id);

	public List<Request> list();
	
	public List<Request> listMatch(Purchase purchase);
	
	public List<Request> listOpen(User user, Timestamp date);
	
}
