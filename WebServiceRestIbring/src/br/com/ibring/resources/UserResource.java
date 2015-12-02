package br.com.ibring.resources;

import java.sql.Timestamp;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.google.gson.Gson;

import br.com.ibring.model.agreement.Agreement;
import br.com.ibring.model.agreement.AgreementService;
import br.com.ibring.model.purchase.Purchase;
import br.com.ibring.model.purchase.PurchaseService;
import br.com.ibring.model.request.Request;
import br.com.ibring.model.request.RequestService;
import br.com.ibring.model.user.UserService;
import br.com.ibring.exception.NoContentException;
import br.com.ibring.model.user.User;
import br.com.ibring.util.Constants;

@Path("/user")
public class UserResource {
	
	@SuppressWarnings("deprecation")
	@GET
	@Path("/pop")
	@Produces("application/json")
	public String popUser(){	
		Timestamp t1 =  new Timestamp(2015-1900, 11, 25, 19, 32, 0, 0);
		Timestamp t2 =  new Timestamp(2015-1900, 11, 25, 19, 30, 30, 0);
		Timestamp t3 =  new Timestamp(2015-1900, 11, 25, 18, 30, 0, 0);
	
		User u1 = new User(0,"Pedro","Alves", "pedro@gmail.com", "dsds", -22.0163043,-47.8721723,"1988");
		User u2 = new User(0, "Anelyse","Cortez", "anelyse@gmail.com", "dsds", -22.040482, -47.903074 , "17");
		User u3 = new User(0, "Matheus","Silva", "matheus@gmail.com", "dsds",  -22.007698, -47.870534 , "22");
		User u4 = new User(0, "Adriano","Silva", "adriano@gmail.com", "dsds",  -22.0037292,-47.8575486 , "22");
		UserService us = new UserService();
		us.save(u1);
		us.save(u2);
		us.save(u3);
		us.save(u4);
		
		Purchase p11 = new Purchase(0 , 0, t1, u1);
		Purchase p12 = new Purchase(0 , 0, t2, u1);
		Purchase p13 = new Purchase(0 , 0, t3, u1);
		Purchase p14 = new Purchase(0 , 0, t3, u1);
	
		PurchaseService ps = new PurchaseService();
		ps.save(p11);
		ps.save(p12);
		ps.save(p13);
		ps.save(p14);
		
		Request r11 = new Request(0, 0, t1 , "2 - pão; 1 - leite",u2);
		Request r12 = new Request(0, 1, t3 , "2 - pão;",u3);
		//Request r13 = new Request(0, 1, t2 , "2 - pão;",u3);
		//Request r14 = new Request(0, 0, t3 , "1 - leite",u4);
		
		RequestService rs = new RequestService();
		rs.save(r11);
		rs.save(r12);
		//rs.save(r13);
		//rs.save(r14);
		
		Agreement a1 = new Agreement(0, false, false, false, false, r11, p12);
		Agreement a2 = new Agreement(0, true, true, false, false, r12, p14);
		
		AgreementService as = new AgreementService();
		as.save(a1);
		as.save(a2);
		
		
		
		return "Sucess";
	}

    @GET
    @Path("/getList")
    @Produces("application/json")
    public String getUserList(){ 
    	return new Gson().toJson(new UserService().listar());
    }
    
    @GET
    @Path("/{faceId}")
    @Produces("application/json")
    public String getUser(@PathParam("faceId") String faceId){
    	User user = new UserService().FindByFaceId(faceId);
     
    	if(user == null)
    		throw new NoContentException("User not found!");  
    	return new Gson().toJson(user);
    }
    
    @POST
    @Path("/save")
    @Produces("application/json")
    @Consumes("application/json")
    public String saveUser(User user) {
    	try{
	    	new UserService().save(user);
	    	return new Gson().toJson(user);
	    } catch (Exception e) {
			return "{Error :" + e.getMessage() + "}";
		}
    }

}
