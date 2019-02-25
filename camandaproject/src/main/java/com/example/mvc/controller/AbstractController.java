package com.example.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;

import de.hsansbach.ecommerce.process.service.CamundaProcessService;

public abstract class AbstractController {
	
	@Autowired
	protected CamundaProcessService camundaProcessService;

}
