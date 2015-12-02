package br.com.ibring.model.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.Entity;

@XmlRootElement
@Entity
public class User implements Serializable{

	private static final long serialVersionUID = -8082190932109335684L;
	
	@Id
	@GeneratedValue
	@Column(name="id_user")
    private int id;
    private String name;
    private String lastname;
    @Column(unique = true)  
    private String faceId;
    private String androidToken;
    private Double latitude;
    private Double longitude;	
    private String number;

	public User(int id, String name, String lastname, String faceId,
			String androidToken, Double latitude, Double longitude,
			String number) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.faceId = faceId;
		this.androidToken = androidToken;
		this.latitude = latitude;
		this.longitude = longitude;
		this.number = number;
	}

	public User() {	
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFaceId() {
		return faceId;
	}

	public void setFaceId(String faceId) {
		this.faceId = faceId;
	}
	
	public String getAndroidToken() {
		return androidToken;
	}

	public void setAndroidToken(String androidToken) {
		this.androidToken = androidToken;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	public boolean hasLocation(){
		return ((this.latitude != null) && (this.longitude != null) && (this.number != null) && (this.number.trim() != ""));
	}
 
}