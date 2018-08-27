package com.example.authservice;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;




@Entity
public class Role implements Serializable{
	private static final long serialVersionUID = 452541L;
	
	@Id
	private Long roleId;
	
	private String roleName;
	

	
	public Role(){}
	
	public Role(String roleName) {
		this.roleName=roleName;
	}
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + "]";
	}



	
	
}
