package br.com.ibring.resources;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.hibernate.Session;

import br.com.ibring.model.agreement.Agreement;
import br.com.ibring.model.agreement.AgreementService;
import br.com.ibring.model.purchase.Purchase;
import br.com.ibring.model.purchase.PurchaseService;
import br.com.ibring.model.request.Request;
import br.com.ibring.model.request.RequestService;
import br.com.ibring.model.user.User;
import br.com.ibring.util.Constants;
import br.com.ibring.util.GsonUtil;
import br.com.ibring.util.HibernateUtil;
import br.com.ibring.util.TimestampAdapter;
import br.com.ibring.android.DownstreamMessaging;
import br.com.ibring.exception.NoContentException;

@Path("/purchase")
public class PurchaseResource {
    
    @GET
    @Path("/getList")
    @Produces("application/json")
    public String getPurchaseList(){ 
    	return GsonUtil.getGsonInstance().toJson(new PurchaseService().list());
    }
    
    @POST
    @Path("/getListOpen/{date}")
    @Produces("application/json")
    @Consumes("application/json")
    public String getPurchaseListOpen(User user, @PathParam("date") String date) throws ParseException{ 
    	SimpleDateFormat sdf = new SimpleDateFormat(TimestampAdapter.FORMAT);  
    	return GsonUtil.getGsonInstance().toJson(new PurchaseService().listOpen(user, new Timestamp(sdf.parse(date).getTime())));
    }
    
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public String getPurchase(@PathParam("id") Integer id){
    	Purchase purchase = new PurchaseService().load(id);
    	if(purchase == null)
    		throw new NoContentException("Purchase not found!");  
    	return GsonUtil.getGsonInstance().toJson(purchase);
    }
    
    @POST
    @Path("/save")
    @Produces("application/json")
    @Consumes("application/json")
    public String savePurchase(Purchase purchase) {
    	try {  
	    	PurchaseService purchaseService = new PurchaseService();
	    	purchaseService.save(purchase);
	    	
	    	new Thread(){
    			@Override
    			public void run() {
	    			DownstreamMessaging downstreamMessaging  = DownstreamMessaging.getDownstreamMessagingInstance(); 	
			    	
			    	Session session = HibernateUtil.getSessionFactory().openSession();
			    	session.beginTransaction();
			    	
		    		AgreementService agreementService = new AgreementService(session);
		    		RequestService requestService = new RequestService(session);
		    		
			    	Agreement agreement;
			    	
				    List<Request> requestMatch = requestService.listMatch(purchase);
				    for (Request request : requestMatch) {
				    	agreement = new Agreement(0, false, false, false, false, request, purchase);
			    		agreementService.save(agreement);
			    		try {
							downstreamMessaging.sendAgreement(agreement, DownstreamMessaging.TOREQUEST);
				    		downstreamMessaging.sendAgreement(agreement, DownstreamMessaging.TOPURCHASE);
						} catch (Exception e) {
							e.printStackTrace();
						}
			    	}
    		    	session.getTransaction().commit();
    		    	session.close(); 	
    			}
    		}.start();
    		
	    	
	    	return Constants.RESULT_SUCCESS;
		} catch (Exception e) {
			return "{Error :" + e.getMessage() + "}";
		}
    }

}
