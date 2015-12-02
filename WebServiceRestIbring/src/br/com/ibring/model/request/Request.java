package br.com.ibring.model.request;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.persistence.Entity;

import br.com.ibring.model.user.User;
import br.com.ibring.util.TimestampAdapter;

@XmlRootElement
@Entity
public class Request implements Serializable{

	private static final long serialVersionUID = -1365993050265707917L;
	
	@Id
	@GeneratedValue
	@Column(name="id_request")
    private int id;
	private int typeStore;
	@XmlJavaTypeAdapter( TimestampAdapter.class)
	private Timestamp datetime;
	private String productList;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_user")
	private User user;

	public Request(int id, int typeStore, Timestamp datetime, String productList,
			User user) {
		super();
		this.id = id;
		this.typeStore = typeStore;
		this.datetime = datetime;
		this.productList = productList;
		this.user = user;
	}

	public Request() {	
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTypeStore() {
		return typeStore;
	}
	public void setTypeStore(int typeStore) {
		this.typeStore = typeStore;
	}
	
	public Timestamp getDate() {
		return datetime;
	}

	public void setDate(Timestamp datetime) {
		this.datetime = datetime;
	}

	public String getProductList() {
		return productList;
	}
	public void setProductList(String productList) {
		this.productList = productList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}