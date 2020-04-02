package com.yiyuclub.springsecurity.config;

import com.yiyuclub.springsecurity.dao.IdeaUserModelRepository;
import com.yiyuclub.springsecurity.models.IdeaRoleModel;
import com.yiyuclub.springsecurity.models.IdeaUserModel;
import com.yiyuclub.springsecurity.models.PermissonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;

@Component("PermissionConfig")
public class PermissionConfig {
    @Autowired
    private IdeaUserModelRepository userModelRepository;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();

        boolean hasPermission = false;

        if (principal instanceof UserDetails) {
            String username = principal.getUsername();

            IdeaUserModel user = userModelRepository.findByUsername(username);
            if (user != null) {
                for (IdeaRoleModel role : user.getRoles()) {
                    for (PermissonModel permisson : role.getPermisson()) {
                        if (request.getRequestURI().contains(permisson.getPermissonName())) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
            }

        }

        return hasPermission;
    }
}
