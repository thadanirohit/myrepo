package com.artek.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artek.model.Project;
import com.artek.repository.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepo;
	
	public List<Project> productService(){
		return projectRepo.getProducts();
	}
	
	public int productSaveService(Project project){
		return projectRepo.saveProject(project);
	}
	
	public int productServiceAjaxDelete(int projectKey){
		return projectRepo.projectRepoAjaxDel(projectKey);
	}
}