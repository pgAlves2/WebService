package br.com.ibring.model.purchase;

import java.sql.Timestamp;
import java.util.List;

import br.com.ibring.model.request.Request;
import br.com.ibring.model.user.User;

public interface PurchaseDAO {

	public void save(Purchase purchase);

	public void update(Purchase purchase);

	public void delete(Purchase purchase);

	public Purchase load(Integer id);

	public List<Purchase> list();
	
	public List<Purchase> listMatch(Request request);
	
	public List<Purchase> listOpen(User user, Timestamp date);
	
}
