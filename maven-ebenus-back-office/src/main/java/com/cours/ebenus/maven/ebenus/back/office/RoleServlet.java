package com.cours.ebenus.maven.ebenus.back.office;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cours.ebenus.maven.ebenus.dao.entities.Role;
import com.cours.ebenus.maven.ebenus.dao.entities.User;
import com.cours.ebenus.maven.ebenus.service.IServiceFacade;
import com.cours.ebenus.maven.ebenus.service.ServiceFacade;

/**
 * Servlet implementation class RoleServlet
 */
@WebServlet("/RoleServlet")
public class RoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static IServiceFacade serviceFacade = null;
	private static List<Role> roles = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoleServlet() {
        super();
        serviceFacade = new ServiceFacade();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
    	 User u = (User) session.getAttribute("user");
     	 
    	if (u == null) {
    		response.sendRedirect(this.getServletContext().getContextPath() + "/LoginServlet");
    	} else {
        	String action = request.getParameter("action");
        	System.out.println(action);
        	
        	if(action == null) {
        		listRole(request,response);
        	}else if(action.equalsIgnoreCase("create")) {
        		createView(request, response);
        	}else if(action.equalsIgnoreCase("update")) {
        		int id = Integer.parseInt(request.getParameter("id"));
        		updateView(request, response, id);
        	}else if(action.equalsIgnoreCase("delete")) {
        		int id = Integer.parseInt(request.getParameter("id"));
        		delete(request, response, id);
        	}
    	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String action = request.getParameter("action");
    	System.out.println(action);
    	
    	if(action.equalsIgnoreCase("create")) {
    		create(request, response);
    	}else if(action.equalsIgnoreCase("update")) {
    		int id = Integer.parseInt(request.getParameter("id"));
    		update(request, response, id);
    	}
	}
	
    private void listRole(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
    	roles = serviceFacade.getRoleDao().findAllRoles();
    	request.setAttribute("roles", roles);
    	this.getServletContext().getRequestDispatcher("/pages/role/list.jsp").forward(request, response);
    }
    
    
    
    private void createView(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/role/createUpdateRole.jsp");
        dispatcher.forward(request, response);
    }
    
    private void create(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
    	Role r = new Role();
    	r.setName(request.getParameter("name"));
    	serviceFacade.getRoleDao().createRole(r);
    	
    	listRole(request, response);
    }
    
    
    
    private void updateView(HttpServletRequest request, HttpServletResponse response, int id) throws  IOException, ServletException {
    	Role role = serviceFacade.getRoleDao().findRoleById(id);
    	request.setAttribute("role", role);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/role/createUpdateRole.jsp");
        dispatcher.forward(request, response);
    }
    
    private void update(HttpServletRequest request, HttpServletResponse response, int id) throws  IOException, ServletException {
    	Role role = serviceFacade.getRoleDao().findRoleById(id);
    	role.setName(request.getParameter("name"));
    	serviceFacade.getRoleDao().updateRole(role);
    	
    	listRole(request, response);
    }
    
    
    
    private void delete(HttpServletRequest request, HttpServletResponse response, int id) throws  IOException, ServletException {
    	Role role = serviceFacade.getRoleDao().findRoleById(id);
    	serviceFacade.getRoleDao().deleteRole(role);
    	
    	listRole(request, response);
    }
}
