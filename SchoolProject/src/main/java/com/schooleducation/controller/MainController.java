package com.schooleducation.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;

import com.core.singletons.BuildStatus;
import com.schooleducation.DAO.RegisterDAO;
import com.schooleducation.Threads.BuildThread;
import com.schooleducation.model.Login;
import com.schooleducation.model.Register;
import com.schooleducation.validator.RegisterValidator;

@Controller
public class MainController {
	private BuildThread build;
	private boolean isFirstReq = false;
	private ThreadGroup buildGroup = new ThreadGroup("VitehcBuild");
	
	@Autowired
	RegisterDAO redao;
	
	String purpose;
	int buildCount;
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

	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(MainController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(ModelMap model) {
		return "index";
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
		String result=redao.registerUser(re);
		model.addAttribute("message", result);
		// Spring uses InternalResourceViewResolver and return back index.jsp
		return  "index";

	}
	 @RequestMapping("/ajax")
	    public ModelAndView helloAjaxTest() {
	        return new ModelAndView("ajax", "message", "Crunchify Spring MVC with Ajax and JQuery Demo..");
	    }
	
	
	  
	 /*@RequestMapping(value = "/ajaxtest/{name}", method = RequestMethod.GET)
	    public @ResponseBody
	    String getTime(@PathVariable String name) throws IOException {
		 System.out.println("enter in to build");
		 //if (!isFirstReq) {
			 System.out.println("Started build");
			isFirstReq = true;
			build = new BuildThread(name);
			Thread innThread = new Thread(build);
			innThread.start();
		//}
		 
		 
		 
		 return build.getBuildThread().toString();
	 }*/
	 @RequestMapping(value = "/startBuild/{name}", method = RequestMethod.GET)
		public @ResponseBody 
		String startBuild(@PathVariable String name) throws IOException, InterruptedException {
			System.out.println(name);
		
			String buildName=name+buildCount;
				//isFirstReq = true;
			    int active = Thread.activeCount();
	            System.out.println("currently active threads: " + active);
	            //Thread.sleep(2000l);
				build = new BuildThread(buildName);
				Thread innThread = new Thread(buildGroup,build,buildName);
				buildCount++;
				innThread.start();
				System.out.println("Main thread: " + innThread.getName() + "(" + innThread.getId() + ")");
			System.out.println(build.buildId());
			BuildStatus buildStatus = BuildStatus.getBuildStatuIns();
			 Map<String, StringBuffer> mapBuildStatus = buildStatus.getMapBuildStatus();
			 //String threadName = Thread.currentThread().getName();
			 System.out.println("checking Before status");
			

			 for (String key: mapBuildStatus.keySet()) {
			     System.out.println("key : " + key);
			    
			 }
			return  "Build Started with name : "+buildName;
		}
		
		@RequestMapping(value = "/getBuildStatus/{buildName}", method = RequestMethod.GET)
		public @ResponseBody 
		String getBuildStatus(@PathVariable String buildName) throws IOException, InterruptedException {
			
			BuildStatus buildStatus = BuildStatus.getBuildStatuIns();
			 Map<String, StringBuffer> mapBuildStatus = buildStatus.getMapBuildStatus();
			 //String threadName = Thread.currentThread().getName();
			 System.out.println("checking build status");
			

			 for (String key: mapBuildStatus.keySet()) {
			     System.out.println("key : " + key);
			    
			 }
			 if (mapBuildStatus.containsKey(buildName)) {
				
				return mapBuildStatus.get(buildName).toString();
			}
			 else {
				 return "Invalid Build Name";
			 }
			
			
			
		}
		
		
		
		
		
		
	
	@RequestMapping(value = "/request", method = RequestMethod.POST)
	public String login(ModelMap model, @ModelAttribute("loginForm") @Validated Login login, 
		      BindingResult bindingResult) {
		
		
		if (bindingResult.hasErrors()) {
	         return "sign-in";
	      }
		List<Register> li2=redao.validateLogin(login.getUsername(),login.getPassword());
		int itemCount = li2.size();
	
		for(Register reg:li2)
		{  
			purpose=reg.getPurpose();
		}
		if(itemCount==1)
		{
			if(purpose.endsWith("trainer")) 
			{
			System.out.println(purpose);
			System.out.println(itemCount);
			return  "DashBoard";
			}
			else
			{
				return  "StudentDashboard";	
			}
		}
		else
		{
			return  "index";
		}
}
	
}
