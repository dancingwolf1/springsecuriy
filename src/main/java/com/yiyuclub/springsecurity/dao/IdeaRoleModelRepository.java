package com.yiyuclub.springsecurity.dao;

import com.yiyuclub.springsecurity.models.IdeaRoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IdeaRoleModelRepository extends JpaRepository<IdeaRoleModel, Integer>, JpaSpecificationExecutor<IdeaRoleModel> {

    IdeaRoleModel findByRoleName(String roleName);
}