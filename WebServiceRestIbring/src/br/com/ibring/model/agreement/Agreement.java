package br.com.ibring.model.agreement;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.Entity;

import br.com.ibring.model.purchase.Purchase;
import br.com.ibring.model.request.Request;

@XmlRootElement
@Entity
public class Agreement implements Serializable{

	private static final long serialVersionUID = -7036118585479047862L;
	
	@Id
	@GeneratedValue
	@Column(name="id_agreement")
    private int id;
	private boolean requestAccept; 
	private boolean purchaseAccept;
	private boolean requestView;
	private boolean purchaseView;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_request")
    private Request request;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_purchase")
    private Purchase purchase;

	public Agreement(int id, boolean requestAccept, boolean purchaseAccept,
			boolean requestView, boolean purchaseView, Request request,
			Purchase purchase) {
		super();
		this.id = id;
		this.requestAccept = requestAccept;
		this.purchaseAccept = purchaseAccept;
		this.requestView = requestView;
		this.purchaseView = purchaseView;
		this.request = request;
		this.purchase = purchase;
	}

	public Agreement() {	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isRequestAccept() {
		return requestAccept;
	}

	public void setRequestAccept(boolean requestAccept) {
		this.requestAccept = requestAccept;
	}

	public boolean isPurchaseAccept() {
		return purchaseAccept;
	}

	public void setPurchaseAccept(boolean purchaseAccept) {
		this.purchaseAccept = purchaseAccept;
	}

	public boolean isRequestView() {
		return requestView;
	}

	public void setRequestView(boolean requestView) {
		this.requestView = requestView;
	}

	public boolean isPurchaseView() {
		return purchaseView;
	}

	public void setPurchaseView(boolean purchaseView) {
		this.purchaseView = purchaseView;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}
	
	
	
	
    
}