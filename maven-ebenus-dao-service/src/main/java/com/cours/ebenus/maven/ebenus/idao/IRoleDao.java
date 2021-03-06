package com.cours.ebenus.maven.ebenus.idao;

import java.util.List;

import com.cours.ebenus.maven.ebenus.dao.entities.Role;

public interface IRoleDao {
	public List<Role> findAllRoles();
	
	public Role findRoleById(int idRole);
	
	public List<Role> findByIdentifiant(String identifiantRole);
	
    public Role createRole(Role role);

    public Role updateRole(Role role);

    public boolean deleteRole(Role role);
}
