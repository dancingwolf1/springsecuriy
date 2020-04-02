package com.yiyuclub.springsecurity.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "permisson")
@Entity
@Data
public class PermissonModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "permisson_id", insertable = false, nullable = false)
    private Integer permissonId;

    @Column(name = "permisson_name")
    private String permissonName;

    @Column(name = "permisson_remake")
    private String permissonRemake;

    
}