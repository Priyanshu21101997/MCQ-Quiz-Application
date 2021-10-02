package com.example.FinalQuiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.FinalQuiz.domain.Quiz;
import com.example.FinalQuiz.service.QuizService;

@Controller
public class QuizController {
	
	@Autowired
    private QuizService service;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<Quiz> listquesdata = service.listAll();
        model.addAttribute("listquesdata", listquesdata);
        System.out.print("Get / ");	
        return "index";
    }

    @GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("quesdata", new Quiz());
        return "new";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveQuiz(@ModelAttribute("quesdata") Quiz std) {
        service.save(std);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditQuizPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("new");
        Quiz std = service.get(id);
        mav.addObject("quesdata", std);
        return mav;
        
    }
    @RequestMapping("/delete/{id}")
    public String deletequesdata(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/";
    }
    
    @RequestMapping("/fetchallQuestions")
	@ResponseBody
	public List<Quiz> fetchAllQuestions() {
		//System.out.println(service.getAllQuestions());
        List<Quiz> listquesdata = service.listAll();

		return listquesdata;
	}

}
