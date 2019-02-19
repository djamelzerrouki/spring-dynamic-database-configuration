package com.example.entites;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
 
@Entity
public class Service {
 
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;
    private String name;
    
     public long getId() {
        return id;
    }
    public Service() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Service(long id, String name ) {
		super();
		this.id = id;
		this.name = name;
 	}
 
	 
	public Service( String name, String typemodel) {
		super();
 		this.name = name;
 	}
	 
	public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
	@Override
	public String toString() {
		return "Service [id=" + id + ", name=" + name + "]";
	}
     
   
     
}