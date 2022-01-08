package com.phucle.spring.jpa.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.phucle.spring.jpa.entity.Hobby;
import com.phucle.spring.jpa.entity.Province;
import com.phucle.spring.jpa.entity.Hobby;
import com.phucle.spring.jpa.service.BaseService;

@Controller
@RequestMapping("/hobby")
public class HobbyController {
	@Autowired
    private BaseService<Hobby> hobbiesService;
	
	@GetMapping("/list")
	public String listHobbys(Model theModel) {
		List<Hobby> theHobby = hobbiesService.getAll();
		theModel.addAttribute("hobbies", theHobby);	
		return "list-hobbies";
	}
	
	@GetMapping("/showForm")
	public String showFormForAdd(Model theModel) {
		//LOG.debug("inside show Hobby-form handler method");
		Hobby theHobby = new Hobby();
		theModel.addAttribute("hobby", theHobby);
		
		return "hobby-form";
	}
	
	@PostMapping("/saveHobby")
	public String saveHobby(@Valid @ModelAttribute("hobby") Hobby theHobby,BindingResult theBindingResult,Model theModel) {
		if (theBindingResult.hasErrors()) {
			return "hobby-form";
		}
		else {
			List<Hobby> check = hobbiesService.getAll();
			for(Hobby pro : check) {
				if(pro.getName().equals(theHobby.getName())){
				
					theModel.addAttribute("message","value already exists");
					return "hobby-form";
				}
			}
		hobbiesService.save(theHobby);	
		return "redirect:/hobby/list";
		}
	}
	
	@GetMapping("/updateForm")
	public String showFormForUpdate(@RequestParam("hobbyId") int theId,
									Model theModel)  {
		Hobby theHobby = hobbiesService.getByID(theId);	
		theModel.addAttribute("hobby", theHobby);
		return "hobby-form";
	}
	@GetMapping("/delete")
	public String deleteHobby(@RequestParam("hobbyId") int theId)  {
		hobbiesService.deleteById(theId);
		return "redirect:/hobby/list";
	}
}
