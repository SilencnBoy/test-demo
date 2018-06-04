package com.xul.util;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xul.entity.User;
import com.xul.service.IUserService;

@Service("MyShiroUitl")
@Transactional
public class MyShiroUitl extends AuthorizingRealm {

	@Autowired
	private IUserService dao;
	
	 /**
	  * TODO 授权信息
	  */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		/*String loginName = (String) principals.fromRealm(getName()).iterator().next();
		Users user = dao.getByUsername(loginName);
		if (user != null) {
			/// 用来存放所有查出来的角色和权限信息
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			// 用户的角色集合
			info.setRoles(user.getRolesName());
			System.out.println("useRoleNmae");
			Set<Role> roleList = user.getUserRole();
			for (Role role : roleList)
			{
				System.out.println("权限" + role.getRoleName());
				info.addStringPermissions(role.getPermissionsName());
			}
			return info;
		}*/
		return null;
	}
	
	/**
	 * TODO 验证登录
	 * 
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		 //获取基于用户名和密码的令牌  
        //实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的  
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        //从数据库中查询用户用信息
        User user = dao.getUsersByname(token.getUsername());
        if (user != null) {
            System.out.println("password"+user.getPassword());
             //此处无需比对,比对的逻辑Shiro会做,我们只需返回一个和令牌相关的正确的验证信息  
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
        } else {
             //没有返回登录用户名对应的SimpleAuthenticationInfo对象时,就会在LoginController中抛出UnknownAccountException异常  
            return null;
        }
	}

}
