package com.example.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.TestserverApplication;
import com.example.dao.EmployeRepository;
import com.example.dao.ServiceRepository;
import com.example.entites.Employe;
import com.example.entites.Service;

@Controller 
@RequestMapping(value="/Modele")
public class ModelControler {
	private  static String namedb;
	@Autowired
	EmployeRepository er;
	@Autowired
	ServiceRepository sr;

	@RequestMapping(value="/addModele" ,method=RequestMethod.GET)
	public String formAddModele(Model model) throws IOException {

		model.addAttribute("modeldata",TestserverApplication.showDatabase());

		return "creatmodel";
	}

	@PostMapping("/postEndpoint" )
	public String pidUserSubmit(@RequestParam(name = "namemodel") String namemodel) throws IOException, SQLException {
		//	ArrayList <String> list=TestserverApplication.showDatabase() ;
		if(namemodel!=null) {
			namemodel =namemodel.replaceAll("\\s","");
			namedb=namemodel;
			TestserverApplication.configModale(namemodel);
			//TestserverApplication.creatEntity("Dossier",5);
			////TestserverApplication.createNewTable(namemodel,5);
			//  TestserverApplication .restart();

			//TestserverApplication.main(TestserverApplication.args);
			return "creatTable";}
		else {
			return "redirect:addModele";

		}
	}

	@PostMapping("/postCreatTable" )
	public String getNombrCh(@RequestParam(name = "nbr") long nbr) throws IOException {
 		if(nbr>0) {
		 
			TestserverApplication.creatEntity("Dossier",( int) nbr);

			return "redirect:home";}
		else {
			return "redirect:addModele";

		}
	}

	
	//addTable
	@RequestMapping(value="/addTable" ,method=RequestMethod.POST)
	public String formAddTable(Model model) {
		List<Employe> list = er.findAll();
		model.addAttribute("enployee",new Employe());
		model.addAttribute("enployees",list);
		return "creatTable";
	}

	@RequestMapping(value="/saveEnployee" ,method=RequestMethod.POST)
	public String saveEnployee(Employe ep) {
		er.save(ep);
		return "redirect:Employe";
	}	
	//saveService
	@RequestMapping(value="/saveService" ,method=RequestMethod.POST)
	public String saveService(Service srv) {
		sr.save(srv);
		return "redirect:Service";
	}
	@RequestMapping(value="/drop" ,method=RequestMethod.GET)
	public String dropdb(Model model) throws IOException {
		TestserverApplication.dropDataBase(namedb);
		TestserverApplication.updateInitialApplicationProperties();
		return "redirect:home";
	}
	/*@RequestMapping(value="http://localhost:5000" ,method=RequestMethod.POST)
	public String bpml() {
 		return "index";
 }*/

	@RequestMapping(value="/home")
	public String home(Model model) {
		model.addAttribute("modeldata",TestserverApplication.showDatabase());
		return "home";
	}


	@RequestMapping("/model" )
	public String model(Model model,@RequestParam(name = "m") String namemodel) throws IOException, SQLException {
		//	ArrayList <String> list=TestserverApplication.showDatabase() ;

		namemodel =namemodel.replaceAll("\\s","");
		namedb=namemodel;
		TestserverApplication.updatApplicationProperties(namemodel.substring(6));
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
	//Employe
	@RequestMapping(value="/Employe" ,method=RequestMethod.GET)
	public String formEmploye(Model model) {
		List<Employe> list = er.findAll();
		model.addAttribute("enployee",new Employe());
		model.addAttribute("enployees",list);
 	return "addenployee";
	}
	//Service
		@RequestMapping(value="/Service" ,method=RequestMethod.GET)
		public String formService(Model model) {
			List<Service> list = sr.findAll();
			model.addAttribute("service",new Employe());
			model.addAttribute("services",list);
	 	return "addservice";
		}



}
