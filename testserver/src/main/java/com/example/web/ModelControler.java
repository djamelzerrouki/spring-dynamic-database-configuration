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
import com.example.dao.EnployeeRepository;
import com.example.entites.Enployee;


@Controller 
@RequestMapping(value="/Modele")
public class ModelControler {
@Autowired
	EnployeeRepository er;
 
	
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
		TestserverApplication.configModale(namemodel);
		TestserverApplication.creatEntity("Dossier",5);
		TestserverApplication.createNewTable(namemodel,5);
		          //  TestserverApplication .restart();
 
		//TestserverApplication.main(TestserverApplication.args);
		return "creatTable";}
		else {
			return "redirect:addModele";
			
		}
	}
	
	//addTable
	@RequestMapping(value="/addTable" ,method=RequestMethod.POST)
	public String formAddTable(Model model) {
		List<Enployee> list = er.findAll();
 	       model.addAttribute("enployee",new Enployee());
	       model.addAttribute("enployees",list);
 	return "creatTable";
	}
	@RequestMapping(value="/saveEnployee" ,method=RequestMethod.POST)
	public String save(Enployee ep) {
        er.save(ep);
		return "redirect:formAdd";
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
	@RequestMapping(value="/model")
	public String model(Model model)  {
	
 
	//TestserverApplication.configModale("bbbbbbbbbbbb");
  


 		return "work";
	}
	
	public class Modele{
	public String getNamemodel() {
			return namemodel;
		}

		public void setNamemodel(String namemodel) {
			this.namemodel = namemodel;
		}

	String namemodel;
	
}
	
}
