package com.example.web;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
		List<Enployee> list = er.findAll();
		Modele m=new Modele();
	       model.addAttribute("model",m);
	       
	       TestserverApplication.configModale(m.getNamemodel());
	       model.addAttribute("modeldata",TestserverApplication.showDatabase());

 	return "creatmodel";
 }
	 
	@PostMapping("/postEndpoint" )
	public String pidUserSubmit(@RequestParam(name = "namemodel") String namemodel) throws IOException {
	       TestserverApplication.configModale(namemodel);

		return "creatTable";
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
	
 try {
	TestserverApplication.configModale("bbbbbbbbbbbb");
System.out.println("bbbbb");
 } catch (IOException e) {
	// TODO Auto-generated catch block
	System.out.println(e);
}


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
