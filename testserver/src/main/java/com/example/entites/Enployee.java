package com.example.entites;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
public class Enployee implements Serializable{

    public Enployee() {
		super();
	}
	public Enployee(String name, String password, Long servicid, Date datenes) {
		super();
		this.name = name;
		this.password = password;
		this.servicid = servicid;
		this.datenes = datenes;
	}
	private String name;
	private String password;
	private Long servicid;
	@DateTimeFormat(pattern="yyyy-MM-dd")   
    private Date datenes;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
     
    public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getServicid() {
		return servicid;
	}
	public void setServicid(Long servicid) {
		this.servicid = servicid;
	}
	public Date getDatenes() {
		return datenes;
	}
	public void setDatenes(Date datenes) {
		this.datenes = datenes;
	}
	public String toString(){
        return id+" | " + name+ " | "+ datenes +" | "+ password;
    }
 
}
