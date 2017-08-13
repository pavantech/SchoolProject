package com.studentproject.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import com.studentproject.model.Register;
import com.studentproject.validator.RegisterValidator;
import com.studentproject.validator.LoginValidator;
import com.studentproject.model.Login;
import com.studentproject.DAO.RegisterDAO;

@Controller
public class MainController {
	
	@Autowired
	RegisterDAO redao;
	
	@Autowired
	   @Qualifier("RegisterValidator")
	   private Validator registervalidator;
	
	@Autowired
	@Qualifier("LoginValidator")
	   private Validator loginvalidator;

	   @InitBinder("userForm")
	   private void initBinderregister(WebDataBinder binder) {
	      binder.setValidator(registervalidator);
	   }
	   @InitBinder("loginForm")
	   private void initBinderlogin(WebDataBinder binder) {
	      binder.setValidator(loginvalidator);
	   }


	private static int counter = 0;
	private static final String VIEW_INDEX = "index";
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(MainController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(ModelMap model) {
		return VIEW_INDEX;
	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String signin(ModelMap model) {
		model.addAttribute("loginForm", new Login());
		return "sign-in";
	}
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String signup(ModelMap model) {
		model.addAttribute("userForm", new Register());
		return "Sign-up";
	}
	
	 @ModelAttribute("userForm")
	   public Register createStudentModel() {	
	      return new Register();
	   }
	 @ModelAttribute("loginForm")
	   public Login loginModel() {	
	      return new Login();
	   }

	@RequestMapping(value = "/addStudent", method = RequestMethod.POST)
	public String User(ModelMap model, @ModelAttribute("userForm") @Validated Register re, 
		      BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
	         return "Sign-up";
	      }
		redao.registerUser(re);
		model.addAttribute("message", re.getFirstname());
		model.addAttribute("counter", ++counter);
		logger.debug("[welcome] counter : {}", counter);

		// Spring uses InternalResourceViewResolver and return back index.jsp
		return  VIEW_INDEX;

	}
	
	@RequestMapping(value = "/request", method = RequestMethod.POST)
	public String login(ModelMap model, @ModelAttribute("loginForm") @Validated Login login, 
		      BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
	         return "sign-in";
	      }
		List<Register> li=redao.getAllGuests();
		for(Register i : li)
		{
			System.out.println(i.getUsername());
			System.out.println(i.getPassword());
		}
		List<Register> li2=redao.validateLogin(login.getUsername(),login.getPassword());
		int itemCount = li2.size();
		if(itemCount==1)
		{
			System.out.println(itemCount);
			model.addAttribute("lists", li);
			model.addAttribute("counter", ++counter);
			logger.debug("[welcome] counter : {}", counter);
			return  VIEW_INDEX;
		}
		else
		{
			System.out.println("Invalid details");
			model.addAttribute("lists", li);
			model.addAttribute("counter", ++counter);
			logger.debug("[welcome] counter : {}", counter);
			return  VIEW_INDEX;
		}
		

		// Spring uses InternalResourceViewResolver and return back index.jsp
		

	}
	
	

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String welcomeName(@PathVariable String name, ModelMap model) {

		model.addAttribute("message", "Welcome " + name);
		model.addAttribute("counter", ++counter);
		logger.debug("[welcomeName] counter : {}", counter);
		return VIEW_INDEX;

	}

}
