package com.artek.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.artek.model.Project;
import com.artek.model.Test;
import com.artek.service.ProjectService;
import com.artek.validation.GetPostValidaor;
import com.artek.validation.ProjectValidator;

@Controller
public class ProjectController {
	
	@Autowired
	private ProjectValidator projectValidator;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private GetPostValidaor gpv;
	
	@RequestMapping("/projects")
	public String showProjects(Map<String,Object> map, ModelMap model){
	//public String showProjects(@ModelAttribute("newProject") Project project, BindingResult result, ModelMap model){
		
		if (!model.containsAttribute("newProject")) {
            model.addAttribute("newProject", new Project());
            System.err.println("no newProject");
        }else{
        	System.err.println("Yes newProject");
        }
		
		System.out.println(model.keySet());
		
		List<Project> projectList = projectService.productService();
		
		if( projectList.size() > 0 ){
			model.addAttribute("areProjectAvailable", "yes");
			model.addAttribute("projectList", projectList);
		}else{
			model.addAttribute("areProjectAvailable", "no");
			model.addAttribute("projectList", projectList);			
		}
		
		return "projects";
	}		
	
	@RequestMapping(value="/saveProject", method=RequestMethod.POST)
	public String saveProject(@ModelAttribute("newProject") Project project, BindingResult result, RedirectAttributes attr, ModelMap model, Map<String,Object> map){		
				
		projectValidator.validate(project, result);
		
		System.err.println(model.keySet());
		
		/*System.out.println(result.getAllErrors());
		
		List<ObjectError> oe = result.getAllErrors();
		
		ListIterator<ObjectError> errorIterator = oe.listIterator();
		
		while(errorIterator.hasNext()){
			ObjectError objcterr = errorIterator.next();
			System.out.println( objcterr.getCode() );
			System.out.println(objcterr.getDefaultMessage());
		}*/
		
		if(!result.hasErrors()){
			int id = projectService.productSaveService(project);
			
			if( id != 0 ){
				if( id != -1 ){
					model.addAttribute("prjctSaveStatus", "yes");
				}else{
					model.addAttribute("prjctSaveStatus", "error");
				}			
			}else{
				model.addAttribute("prjctSaveStatus", "no");
			}
		}
		
		attr.addFlashAttribute("org.springframework.validation.BindingResult.newProject", result);
		attr.addFlashAttribute("newProject",project);
		
		return "redirect:/projects.do";
	}
	
	@RequestMapping("/ajaxDelProject")
	public void ajaxTest(ModelMap model, HttpServletRequest req, HttpServletResponse res){
		
		PrintWriter pw = null;
		
		try {
			pw = res.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int projectKey = Integer.parseInt(req.getParameter("projectKey"));
		
		int projectsDeleted = 5;
		projectsDeleted = projectService.productServiceAjaxDelete(projectKey);				
		
		String isProjectDeleted = "no";
		
		if(projectsDeleted > 0){
			isProjectDeleted = "yes";
		}
		
		pw.print(isProjectDeleted);
		
		/*model.addAttribute("projectKey", projectKey);		
		model.addAttribute("isprojectsDeleted", isProjectDeleted);*/
		
		//return "ajaxDelProject";
	}
	
	// Get and Post from same method check
	@RequestMapping(value = "/getpost", method={RequestMethod.GET,RequestMethod.POST})
	public String serveGetPost(@ModelAttribute("test")Test test, BindingResult result, 
			ModelMap model, RedirectAttributes attr, HttpServletRequest req, 
			HttpServletResponse res){ 		
		
		if(req.getMethod() == "GET"){						
			
			System.out.println(test.getName());
			System.out.println(model.get("test"));
			
			if(model.containsAttribute("errors_redirected")){
				model.put(BindingResult.MODEL_KEY_PREFIX+"test", model.get("errors_redirected"));
			}
			
			/*if (!model.containsAttribute("test")) {
	            model.addAttribute("test", new Test());
	            System.err.println("no test");
	        }else{
	        	System.err.println("Yes test");
	        }*/
			
			System.err.println(model.keySet());
			System.out.println("Get called");
		}
		
		if(req.getMethod() == "POST"){
			
			gpv.validate(test, result);			
			
			System.out.println("Post called");
			
			if(result.hasErrors()){
				
				attr.addFlashAttribute("test", test);
				//attr.addFlashAttribute("org.springframework.validation.BindingResult.test", result);				
				attr.addFlashAttribute("errors_redirected", result);
				
				return "redirect:/getpost.do";
				//return "getandpost";
			}
		}				
				
		return "getandpost";
		
	}
}