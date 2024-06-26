package com.organiccropmastery.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.organiccropmastery.hashing.MD5Hashing;
import com.organiccropmastery.model.Admin;
import com.organiccropmastery.model.User;
import com.organiccropmastery.resource.AdminResource;
import com.organiccropmastery.resource.UserResource;

@Controller
public class UserController {
	
private static Logger logger  = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserResource userResource;
	
	@Autowired
	private AdminResource adminResource;
	
	@GetMapping("/userlogin")
	public String goToAdminLoginPage() {
		logger.info(logger + "Redirecting user to Login page.");
		return "userlogin";
	}
		
	@GetMapping("/userregister")
	public String goToAdminRegisterPage() {
		logger.info(logger + "Redirecting user to Register page.");
		return "userregister";
	}
	
	
	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		session.removeAttribute("active-user");
		session.removeAttribute("user-login");
		mv.addObject("status","Logged out Successfully");
		mv.setViewName("index");
		
		return mv;
	}
	
	@PostMapping("/userregister")
	public ModelAndView registerAdmin(@ModelAttribute User user, Model model) {
		ModelAndView mv = new ModelAndView();
		if(this.userResource.addUser(user) != null ) {
			mv.addObject("status", user.getFirstname()+" Successfully Registered as User");
			mv.setViewName("userlogin");
			logger.info(logger + " User Registered Successfully");
		}
		
		else {
			mv.addObject("status", user.getFirstname()+" Failed to Registered as User");
			mv.setViewName("userregister");
			//LOG.info("Failed to Register as User");
		}
		
		return mv;
	}
	
	@PostMapping("/changepassword")
	public ModelAndView changepassword(@RequestParam("userType") String userType, @RequestParam("pass") String password, @RequestParam("email") String email) {
		ModelAndView mv = new ModelAndView();
		
		if(userType.equals("user")) {
			User user = userResource.getUserByEmail(email);
		    if(user != null) {
		    	user.setPassword(password);
		        user = userResource.updateUser(user);
		        if(user != null) {
		        	mv.addObject("status", "password updated successfully.");
					mv.setViewName("index");
		        }
		    
		    }
		}
		
		else if(userType.equals("admin")) {
			Admin admin  = adminResource.getAdminByEmailId(email);
			if(admin != null) {
				password = MD5Hashing.doHashing(password);
		    	admin.setPassword(password);
		        admin = adminResource.updateAdminById(admin);
		        mv.addObject("status", "password updated successfully.");
				mv.setViewName("index");
			}
		}
		
		return mv;
	}
	@PostMapping("/forgetpassword")
	public ModelAndView forgetPassword( @RequestParam("email") String email,@RequestParam("pass") String password) {
		ModelAndView mv = new ModelAndView();
			
			
			
				User user = userResource.getUserByEmail(email);
				System.out.println(user);
				password=MD5Hashing.doHashing(password);
				user.setPassword(password);
				 user = userResource.updateUser(user);

				 if(user != null) {
			        	mv.addObject("status", "password updated successfully.");
						mv.setViewName("userlogin");
			        }
		
		
		
		return mv;
	}
	@PostMapping("/userlogin")
	public ModelAndView loginAdmin(HttpServletRequest request, @RequestParam String username, @RequestParam String password ) {
		ModelAndView mv = new ModelAndView();
		User user=this.userResource.loginUser(username, password);
		if(user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("active-user", user);
			session.setAttribute("user-login","user");
			mv.addObject("status", username+" Successfully Logged in as user");
			mv.setViewName("index");
			//LOG.info("Logged in as USER");
		}
		
		else {
			mv.addObject("status"," Failed to Login as User");
			mv.setViewName("userlogin");
			//LOG.info("Failed to login as USER");
		}
		
		return mv;
	}
	
	
	 @PostMapping("/usermobilelogin")
	    public ResponseEntity<Object> loginAdminMobile(HttpServletRequest request, @RequestParam String mobileNumber) {
	        User user = loginUserMobile(mobileNumber);
	        if (user != null) {
	            HttpSession session = request.getSession();
	            storeUserInSession(session, user);
	            return ResponseEntity.ok(buildSuccessResponse());
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildErrorResponse());
	        }
	    }

	    private User loginUserMobile(String mobileNumber) {
	        return userResource.loginUserMobile(mobileNumber);
	    }

	    private void storeUserInSession(HttpSession session, User user) {
	        session.setAttribute("active-user", user);
	        session.setAttribute("user-login", "user");
	    }

	    private String buildSuccessResponse() {
	        JSONObject response = new JSONObject();
	        response.put("status", "Success");
	        response.put("redirectURL", "successmobileindex");
	        return response.toString();
	    }

	    private String buildErrorResponse() {
	        JSONObject response = new JSONObject();
	        response.put("status", "Error");
	        response.put("message", "Failed to login as user");
	        return response.toString();
	    }
	
	
	@GetMapping("/onion")
	public String goToOnionPage() {
		//LOG.info("Redirecting to Onion crop Page.");
		return "onion";
	}

	@GetMapping("/sugarcane")
	public String goToPotatoPage() {
		//LOG.info("Redirecting to sugarcane crop Page.");
		return "sugarcane";
	}

	@GetMapping("/tur")
	public String goToTurPage() {
		//LOG.info("Redirecting to tur crop Page.");
		return "tur";
	}
	
	@GetMapping("/usermobileloginpage")
	public String returnUserMobileLoginPage() {
		return "mobilelogin";
	}
	
	//on successfull otp validation show index page 
	@GetMapping("/successmobileindex")
	public String returnUserMobileIndexPage() {
		return "index";
	}
}
