package com.yiyuclub.springsecurity.dao;

import com.yiyuclub.springsecurity.models.IdeaUserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IdeaUserModelRepository extends JpaRepository<IdeaUserModel, Integer>, JpaSpecificationExecutor<IdeaUserModel> {
    IdeaUserModel findByUsername(String usernmae);
}