package br.com.ibring.android;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.Gson;

import br.com.ibring.model.agreement.Agreement;
import br.com.ibring.util.GsonUtil;

public class DownstreamMessaging {
	
	public static Integer TOREQUEST = 1;
	public static Integer TOPURCHASE = 2;
	public static String REQUEST = "request";
	public static String PURCHASE = "purchase";
	
	private static DownstreamMessaging downstreamMessaging;
	
	public DownstreamMessaging(){
	}
	
	public void sendAgreement(Agreement agreement, Integer to) throws Exception{
		
		Gson gson = GsonUtil.getGsonInstance();
		
		CloseableHttpClient client = HttpClients.createDefault();
	    HttpPost httpPost = new HttpPost("https://gcm-http.googleapis.com/gcm/send");
	 
	    String json = "{ ";
	    json += "\"data\": {";
	    
	    switch (to) {
		case 1:
			json += "\"type\": \""+REQUEST+"\",";
			break;
		case 2:
			json += "\"type\": \""+PURCHASE+"\",";
			break;
	    }
	    
		json += "\"agreement\":";
	    json += gson.toJson(agreement);
	    
	    json += "}";
	    
	    switch (to) {
		case 1:
			json += ",\"to\" : \""+ agreement.getRequest().getUser().getAndroidToken()+"\"";
			break;
		case 2:
			json += ",\"to\" : \""+ agreement.getPurchase().getUser().getAndroidToken()+"\"";
			break;
		}
	    json += "}";
	    
	    StringEntity entity = new StringEntity(json);
	    httpPost.setEntity(entity);
	    httpPost.setHeader("Content-type", "application/json");
	    httpPost.setHeader("Authorization", "key=AIzaSyAPfpa03tbPzKbmPQMsRPr2tplk1i8SSZs");
	 
	    CloseableHttpResponse response = client.execute(httpPost);
	    System.out.println(response.getStatusLine().getStatusCode()+response.getStatusLine().getReasonPhrase());
	    
	    client.close();
	}
	
	public static synchronized DownstreamMessaging getDownstreamMessagingInstance(){
		if (downstreamMessaging == null)
			downstreamMessaging = new DownstreamMessaging();
		
		return downstreamMessaging;
	}

}
