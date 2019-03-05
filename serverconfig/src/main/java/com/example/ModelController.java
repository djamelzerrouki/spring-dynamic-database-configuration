package com.example;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.exomple.configfile.Config;
@Controller 
@RequestMapping(value="/Modele")
public class ModelController {
	private  static String namedb;

// la page prancipale 
	@RequestMapping(value="/home")
	public String home(Model model) {
		model.addAttribute("modeldata",Config.showDatabase());
		return "home";
	}

// pour la selection de modele ou DB
	@RequestMapping("/model" )
	public String model(Model model,@RequestParam(name = "m") String namemodel) throws IOException, SQLException {
 
		namemodel =namemodel.replaceAll("\\s","");
		namedb=namemodel;
 		model.addAttribute("modelName",namedb);
 
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

	@PostMapping("/postEndpoint" )
	public String pidUserSubmit(@RequestParam(name = "namemodel") String namemodel) throws IOException, SQLException {
		if(namemodel!=null) {
			namemodel =namemodel.replaceAll("\\s","");
			namedb=namemodel;
			Config.configDB(namemodel);
			 	return "creatTable"; 
			 	}
		else {
			return "redirect:addModele";

		}
	}
//postCreatModele
	
	@PostMapping("/postCreatModele" )
	public String postCreatModele(@RequestParam(name = "namemodel") String namemodel,@RequestParam(name = "nbr") Number nbr) throws Exception {
 		int index =Integer.parseInt(nbr+"");
		System.out.println(nbr);
 		if(namemodel!=null && index>0) {
			namemodel =namemodel.replaceAll("\\s","");
			namedb=namemodel;
			Config.configAll(namemodel,index);
			return "redirect:home"; 
			}
		else {
			return "redirect:addModele";

		}
	}
	@PostMapping("/postCreatTable" )
	public String getNombrCh(@RequestParam(name = "nbr") Number nbr) throws Exception {
 		int index =Integer.parseInt(nbr+"");
		if(index>0) {
 				
			Config.configDossier("Dossier",index);
			return "redirect:home";}
		else {
			return "redirect:addModele";

		}
	}
//dropdb
	@RequestMapping(value="/drop" ,method=RequestMethod.GET)
	public String dropdb(Model model) throws IOException {
		Config.dropDataBase(namedb);
// khasi gindir drop negla3 datasource me propertise
		//	Config.updateInitialApplicationProperties();
		return "redirect:home";
	}
	
	 



}
