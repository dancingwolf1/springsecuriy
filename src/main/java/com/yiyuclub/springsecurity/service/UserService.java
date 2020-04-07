package com.yiyuclub.springsecurity.service;

import com.yiyuclub.springsecurity.config.UserInfo;
import com.yiyuclub.springsecurity.dao.IdeaUserModelRepository;
import com.yiyuclub.springsecurity.models.IdeaRoleModel;
import com.yiyuclub.springsecurity.models.IdeaUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private IdeaUserModelRepository ideaUserModelRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //查询数据库
        IdeaUserModel userModel = new IdeaUserModel();
        userModel.setUsername(s);
        Example example = Example.of(userModel);
        Optional one = ideaUserModelRepository.findOne(example);

        //验证是否为空
        if(!one.isPresent()){
            throw new UsernameNotFoundException("用户名不存在");
        }

        userModel = (IdeaUserModel)one.get();
        UserInfo userInfo = new UserInfo();

        //为userdsDetail赋值
        userInfo.setUsername(userModel.getUsername());
        userInfo.setPassword(userModel.getSalt()+userModel.getPassword());
        userInfo.setRoles(userModel.getRoles());

        return userInfo;
    }
}
