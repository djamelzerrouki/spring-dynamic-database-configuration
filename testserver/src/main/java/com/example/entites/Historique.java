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
public class Historique implements Serializable{




	public Historique(Long servicid, Long dosseird, Date datenes) {
		super();
		this.servicid = servicid;
		this.dosseird = dosseird;
		this.datenes = datenes;
	}
	public Historique() {
		super();
		// TODO Auto-generated constructor stub
	} 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	private Long servicid;
	
	private Long dosseird;
	@DateTimeFormat(pattern="yyyy-MM-dd")   
	private Date datenes;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Long getDosseird() {
		return dosseird;
	}
	public void setDosseird(Long dosseird) {
		this.dosseird = dosseird;
	}
	@Override
	public String toString() {
		return "Historique [servicid=" + servicid + ", dosseird=" + dosseird + ", datenes=" + datenes + ", id=" + id
				+ "]";
	}



}
