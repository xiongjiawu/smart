package com.xiongjiawu.smartaccountbook.common.dto.user;


import com.xiongjiawu.smartaccountbook.common.model.User;
import com.xiongjiawu.smartaccountbook.common.model.UserAuth;

import java.io.Serializable;

/**
 * 用户登录响应信息 bo
 */
public class UserLoginRespDto implements Serializable {

    private static final long serialVersionUID = 7323985275443823293L;

    /**
     * 用户信息
     */
    private User user;

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * 用户认证信息
     */
    private UserAuth userAuth;

    public UserAuth getUserAuth() {
        return this.userAuth;
    }

    public void setUserAuth(UserAuth userAuth) {
        this.userAuth = userAuth;
    }

//	private List<String> roleCodes;
//
//	public List<String> getRoleCodes() {
//		return roleCodes;
//	}
//
//	public void setRoleCodes(List<String> roleCodes) {
//		this.roleCodes = roleCodes;
//	}

//    private Role role;
//
//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }
//
//    private Organization organization;
//
//    public Organization getOrganization() {
//        return organization;
//    }
//
//    public void setOrganization(Organization organization) {
//        this.organization = organization;
//    }
//
//    private Department department;
//
//    public Department getDepartment() {
//        return department;
//    }
//
//    public void setDepartment(Department department) {
//        this.department = department;
//    }


}
