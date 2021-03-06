package com.huan.HTed.account.dto;

/**Auto Generated By HTed Code Generator**/
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.huan.HTed.mybatis.annotation.ExtensionAttribute;
import org.hibernate.validator.constraints.Length;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.huan.HTed.system.dto.BaseDTO;
@ExtensionAttribute(disable=true)
@Table(name = "sys_user_role")
public class UserRole extends BaseDTO {

     public static final String FIELD_SUR_ID = "surId";
     public static final String FIELD_USER_ID = "userId";
     public static final String FIELD_ROLE_ID = "roleId";


     @Id
     @GeneratedValue
     private Long surId;

     private Long userId; //用户ID

     private Long roleId; //角色ID
     
     @Transient
     private String roleName; //roleName;


     public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setSurId(Long surId){
         this.surId = surId;
     }

     public Long getSurId(){
         return surId;
     }

     public void setUserId(Long userId){
         this.userId = userId;
     }

     public Long getUserId(){
         return userId;
     }

     public void setRoleId(Long roleId){
         this.roleId = roleId;
     }

     public Long getRoleId(){
         return roleId;
     }

     }
