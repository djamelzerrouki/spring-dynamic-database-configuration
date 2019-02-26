package com.example.controller;
 

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.testcamanda;

 @Controller 
@RequestMapping(value="/bpmn")
public class BpmnController {
	private  static String namedb;

// pour la selection de modele ou DB
	@RequestMapping("/model" )
	public String model(Model model) {
		return "showModel";
	}
	

	@RequestMapping(value="/formAdd" ,method=RequestMethod.GET)
	public String formAddEnployee(Model model) {
		return "showModel";
	}


 	@RequestMapping(value="/bpmn")
	public String bpmnModele()  {
 		return "bpmn";
	}
 
	@RequestMapping(value="/addModele" ,method=RequestMethod.GET)
	public String formAddModele(Model model) throws IOException {
		return "creatmodel";
	}
 
//postCreatModele
	
	@PostMapping("/createmodel" )
	public String postCreatModele(@RequestParam(name = "file") String file ) throws Exception {
 		System.out.println(file);
 		if(file!=null) {
 		namedb=file;
			//Config.configAll(namemodel,index);
 		testcamanda.mymethod(file);
			return "showModel"; 
			}
		else {
			return "showModel";

		}
	}
 
	
	 



}

 