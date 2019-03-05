package com.example;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Employe;
import com.example.model.Service;
import com.example.repository.permis.RepoEmployepermis;
import com.example.repository.permis.RepoServicepermis;
@Controller 
@RequestMapping(value="/model_permis")
public class ControllerReppermis {
 	
	@Autowired 
	private   RepoEmployepermis red ;
	@Autowired 
	private   RepoServicepermis rsd ;
private static String namedb; 
	
	// Modele selectioner
	@RequestMapping("/model" )
	public String model(Model model,@RequestParam(name = "m") String namemodel) throws IOException, SQLException {
		//	ArrayList <String> list=TestserverApplication.showDatabase() ;

		namemodel =namemodel.replaceAll("\\s","");
		namedb=namemodel;
 		model.addAttribute("modelName",namedb);
 
		return "showModel";

	} 
	//Employe
		@RequestMapping(value="/Employe" ,method=RequestMethod.GET)
		public String formEmploye(Model model) {
			List<Employe> list = red.findAll();
			model.addAttribute("enployee",new Employe());
			model.addAttribute("enployees",list);
	 	return "addenployee";
		}
		//Service
			@RequestMapping(value="/Service" ,method=RequestMethod.GET)
			public String formService(Model model) {
				List<Service> list = rsd.findAll();
				model.addAttribute("service",new Employe());
				model.addAttribute("services",list);
		 	return "addservice";
			}
			
			@RequestMapping(value="/saveEnployee" ,method=RequestMethod.POST)
			public String saveEnployee(Employe ep) {
				red.save(ep);
				return "redirect:Employe";
			}	
			//saveService
			@RequestMapping(value="/saveService" ,method=RequestMethod.POST)
			public String saveService(Service srv) {
				rsd.save(srv);
				return "redirect:Service";
			}
			@RequestMapping(value="/bpmn")
			public String bpmnModele()  {
		 		return "bpmn";
			}


}
