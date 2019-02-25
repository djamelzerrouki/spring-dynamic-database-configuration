package com.example.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.hsansbach.ecommerce.process.ProcessKey;
import de.hsansbach.ecommerce.process.model.UserTaskModel;

@Controller
@RequestMapping("/processes")
public class ProcessesController extends AbstractController {

	@GetMapping()
	public String processes(@ModelAttribute UserTaskModel userTaskModel) {
		return "processes";
	}

	@PostMapping("/userTask")
	public ModelAndView startProcess(@AuthenticationPrincipal User user, @Valid UserTaskModel processModel, BindingResult result,
			RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return new ModelAndView("processes", "formErrors", result.getAllErrors());
		}

		Map<String, Object> variables = new HashMap<>();
		variables.put("assignee", user.getUsername());
		variables.put("text", processModel.getText());
		String processInstanceId = camundaProcessService.startProcess(ProcessKey.USER_TASK, variables);

		redirect.addFlashAttribute("globalMessage", "Successfully started process 'User Task' with id " + processInstanceId + ".");
		return new ModelAndView("redirect:/processes");
	}

	@PostMapping("/helloWorld")
	public ModelAndView startProcess(RedirectAttributes redirect) {
		String processInstanceId = camundaProcessService.startProcess(ProcessKey.HELLO_WORLD);

		redirect.addFlashAttribute("globalMessage", "Successfully started process 'Hello World' with id " + processInstanceId + ".");
		return new ModelAndView("redirect:/processes");
	}
}
