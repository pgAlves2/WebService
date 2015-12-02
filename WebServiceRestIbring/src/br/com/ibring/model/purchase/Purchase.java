package br.com.ibring.model.purchase;

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
public class Purchase implements Serializable{

	private static final long serialVersionUID = 155726682731872392L;
	
	@Id
	@GeneratedValue
	@Column(name="id_purchase")
    private int id;
	private int typeStore;
	@XmlJavaTypeAdapter( TimestampAdapter.class)
	private Timestamp datetime;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_user")
	private User user;
	

	public Purchase(int id, int typeStore, Timestamp datetime, User user) {
		super();
		this.id = id;
		this.typeStore = typeStore;
		this.datetime = datetime;
		this.user = user;
	}

	public Purchase() {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
    
}