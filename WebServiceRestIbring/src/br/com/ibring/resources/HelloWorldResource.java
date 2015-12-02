package br.com.ibring.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


import br.com.ibring.android.DownstreamMessaging;
import br.com.ibring.model.agreement.Agreement;
import br.com.ibring.model.purchase.Purchase;
import br.com.ibring.model.purchase.PurchaseService;
import br.com.ibring.model.request.Request;
import br.com.ibring.model.request.RequestService;

@Path("/helloworld")
public class HelloWorldResource {

    @GET
    @Produces("text/plain")
    public String showHelloWorld() {
     return "Olá mundo!";   
    }
    
    @GET
    @Path("/min_lat")
    @Produces("text/plain")
    public String calMinlat() {
    	Double from = -22.913488;
    	Double distance = 1.0;
    	Double result = from - Math.toDegrees(distance/6371.0);	
    	return String.valueOf(result);
    }
    
    @GET
    @Path("/max_lat")
    @Produces("text/plain")
    public String calMaxlat() {
    	Double from = -22.913488;
    	Double distance = 1.0;
    	
    	Double result = from + Math.toDegrees(distance/6371.0);
    	
    	return String.valueOf(result);
    }
    
    @GET
    @Path("/min_long")
    @Produces("text/plain")
    public String calMinLong() {
    	Double fromLat = -22.913488;
    	Double fromLong = -43.227632;
    	Double distance = 1.0;
    	
    	Double result = fromLong - Math.toDegrees(Math.asin(distance/6371.0)/Math.cos(Math.toRadians(fromLat)));
    	
    	return String.valueOf(result);
    }

    @GET
    @Path("/max_long")
    @Produces("text/plain")
    public String calMaxLong() {
    	Double fromLat = -22.913488;
    	Double fromLong = -43.227632;
    	Double distance = 1.0;
    	
    	Double result = fromLong + Math.toDegrees(Math.asin(distance/6371.0)/Math.cos(Math.toRadians(fromLat)));
    	
    	return String.valueOf(result);
    }
    
    @GET
    @Path("/notification")
    @Produces("text/plain")
    public String sendNotification() throws Exception {
    	DownstreamMessaging downstreamMessaging = new DownstreamMessaging();
    	
    	RequestService requestService = new RequestService();
    	PurchaseService purchaseService = new PurchaseService();
    	
    	Request request = requestService.load(1);
    	Purchase purchase = purchaseService.load(1);
    	
    	Agreement agreement = new Agreement(0, false, false, false, false, request, purchase);
    	
    	
    	
    	downstreamMessaging.sendAgreement(agreement, DownstreamMessaging.TOREQUEST);
    	return "ok";
    }
    
    
    
    
}
 