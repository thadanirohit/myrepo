package com.artek.repository;

import java.util.List;
import java.util.ListIterator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.artek.model.Project;

@Repository
public class ProjectRepository {
	
	@Autowired
	private HibernateTemplate template;
	
	public List<Project> getProducts(){
		List<Project> projects = null;
		projects = (List<Project>) template.find("from Project");
		
		/*if(projects.size() > 0){
			
			ListIterator projectIterator = projects.listIterator();
			
			while(projectIterator.hasNext()){
				System.out.println(projectIterator.next());
			}
		}*/
				
		return projects;
	}
	
	public int saveProject(Project project){
		
		int id = -1;
		
		try{
			id = (Integer) template.save(project);
		}catch(Exception e){
			System.out.println(e);
			id = -1;
		}
		
		return id;
	}
	
	public int checkProjectName(String prjctName){
		List prevProjects = null;		
		prevProjects = template.find("from Project where projectName=?", prjctName);
		return prevProjects.size();
	}
	
	public int projectRepoAjaxDel(int projectKey){
		
		SessionFactory factory = template.getSessionFactory();
		Session session = factory.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		Query q = session.createQuery("delete from Project where projectId = ?");
		q.setParameter(0, projectKey);
		
		int result = q.executeUpdate();
		
		transaction.commit();
		session.close();
				
		System.out.println(result);
		
		return result;		
	}
	
}