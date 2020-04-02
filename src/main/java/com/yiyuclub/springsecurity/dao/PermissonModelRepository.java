package com.yiyuclub.springsecurity.dao;

import com.yiyuclub.springsecurity.models.PermissonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PermissonModelRepository extends JpaRepository<PermissonModel, Integer>, JpaSpecificationExecutor<PermissonModel> {

}