package com.exomple.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Employe;
import com.exomple.configfile.Config;

@Controller 
@RequestMapping(value="/Modele")
public class ModelControler {
	private  static String namedb;
 

	@RequestMapping(value="/addModele" ,method=RequestMethod.GET)
	public String formAddModele(Model model) throws IOException {

		model.addAttribute("modeldata",Config.showDatabase());

		return "creatmodel";
	}
	//C:\Users\djamel\Documents\workspace-spring-tool-suite-4-4.0.1.RELEASE\testserver\src\main\resources\templates\bpmn-js-examples\modeler\public\index.html
	@RequestMapping(value="/bpmn")
	public String bpmnModele()  {
//modeler\public\index.html
		return "bpmn";
	}

	@PostMapping("/postEndpoint" )
	public String pidUserSubmit(@RequestParam(name = "namemodel") String namemodel) throws IOException, SQLException {
		//	ArrayList <String> list=TestserverApplication.showDatabase() ;
		if(namemodel!=null) {
			namemodel =namemodel.replaceAll("\\s","");
			namedb=namemodel;
			Config.configModale(namemodel);
			 	return "creatTable";}
		else {
			return "redirect:addModele";

		}
	}

	@PostMapping("/postCreatTable" )
	public String getNombrCh(@RequestParam(name = "nbr") Number nbr) throws Exception {
 		int index =Integer.parseInt(nbr+"");
		if(index>0) {


 				
			Config.creatEntity("Dossier",index);
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
	
 

 
	/*@RequestMapping(value="http://localhost:5000" ,method=RequestMethod.POST)
	public String bpml() {
 		return "index";
 }*/

	@RequestMapping(value="/home")
	public String home(Model model) {
		model.addAttribute("modeldata",Config.showDatabase());
		return "home";
	}


	@RequestMapping("/model" )
	public String model(Model model,@RequestParam(name = "m") String namemodel) throws IOException, SQLException {
		//	ArrayList <String> list=TestserverApplication.showDatabase() ;

		namemodel =namemodel.replaceAll("\\s","");
		namedb=namemodel;
 		model.addAttribute("modelName",namedb);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "showModel";

	}
	
	@RequestMapping(value="/formAdd" ,method=RequestMethod.GET)
	public String formAddEnployee(Model model) {

		return "showModel";
	}
	 



}
