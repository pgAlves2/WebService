package br.com.ibring.model.agreement;

import java.util.List;

import br.com.ibring.model.purchase.Purchase;
import br.com.ibring.model.request.Request;

public interface AgreementDAO {

	public void save(Agreement agreement);

	public void update(Agreement agreement);

	public void delete(Agreement agreement);

	public Agreement load(Integer id);

	public List<Agreement> list();
	
	public List<Agreement> list(Request request);
	
	public List<Agreement> list(Purchase purchase);
	
}

