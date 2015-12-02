package br.com.ibring.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.ibring.model.agreement.Agreement;
import br.com.ibring.model.agreement.AgreementService;
import br.com.ibring.model.purchase.Purchase;
import br.com.ibring.model.request.Request;
import br.com.ibring.util.Constants;
import br.com.ibring.util.GsonUtil;
import br.com.ibring.exception.NoContentException;

@Path("/agreement")
public class AgreementResource {
	
    @GET
    @Path("/getList")
    @Produces("application/json")
    public String getAgreementList(){ 
    	return GsonUtil.getGsonInstance().toJson(new AgreementService().listar());
    }
    
    @POST
    @Path("/getListByRequest")
    @Produces("application/json")
    @Consumes("application/json")
    public String getAgreementList(Request request){ 
    	return GsonUtil.getGsonInstance().toJson(new AgreementService().list(request));
    }
    
    @POST
    @Path("/getListByPurchase")
    @Produces("application/json")
    @Consumes("application/json")
    public String getAgreementList(Purchase purchase){ 
    	return GsonUtil.getGsonInstance().toJson(new AgreementService().list(purchase));
    }
    
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public String getAgreement(@PathParam("id") Integer id){
    	Agreement agreement = new AgreementService().load(id);
    	if(agreement == null)
    		throw new NoContentException("Agreement not found!");  
    	return GsonUtil.getGsonInstance().toJson(agreement);
    }
    
    @POST
    @Path("/save")
    @Produces("application/json")
    @Consumes("application/json")
    public String saveAgreement(Agreement agreement) {
    	
    	try {
    		new AgreementService().save(agreement);
    		return Constants.RESULT_SUCCESS;
		} catch (Exception e) {
			return "{Error :" + e.getMessage() + "}";
		}
    	
    }

}
