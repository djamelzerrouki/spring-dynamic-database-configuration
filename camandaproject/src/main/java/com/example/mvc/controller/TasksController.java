package com.example.mvc.controller;

import java.util.List;

import javax.validation.Valid;

import org.camunda.bpm.engine.task.Task;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.hsansbach.ecommerce.process.model.CompleteTasksModel;

@Controller
@RequestMapping("/tasks")
public class TasksController extends AbstractController {

	@GetMapping()
	public String tasks(@AuthenticationPrincipal User user, @ModelAttribute CompleteTasksModel completeTaskModel, Model model) {
		List<Task> assignedTasks = camundaProcessService.getTasksForAssigne(user.getUsername());
		model.addAttribute("assignedTasks", assignedTasks);

		return "tasks";
	}
	
	@PostMapping()
	public ModelAndView complete(@Valid CompleteTasksModel completeTaskModel, RedirectAttributes redirect) {
		camundaProcessService.completeTask(completeTaskModel.getId());
		
		redirect.addFlashAttribute("globalMessage", "Successfully completed task id " + completeTaskModel.getId() + ".");
		return new ModelAndView("redirect:/tasks");
	}

}
