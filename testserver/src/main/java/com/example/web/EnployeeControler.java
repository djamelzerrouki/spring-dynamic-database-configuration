package com.example.web;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.TestserverApplication;
import com.example.dao.EnployeeRepository;
import com.example.entites.Enployee;


@Controller 
@RequestMapping(value="/Enployee")
public class EnployeeControler {
@Autowired
	EnployeeRepository er;
	@RequestMapping(value="/Index")
	public String Index(Model model) {
		List<Enployee> list = er.findAll();
       model.addAttribute("enployees",list);
		return "enployee";
	}
	@RequestMapping(value="/formAdd" ,method=RequestMethod.GET)
	public String formaddEnployee(Model model) {
		List<Enployee> list = er.findAll();
	       model.addAttribute("enployee",new Enployee());
	       model.addAttribute("enployees",list);
 	return "addenployee";
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
	
	
}
