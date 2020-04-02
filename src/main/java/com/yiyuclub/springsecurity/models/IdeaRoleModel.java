package com.yiyuclub.springsecurity.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "idea_role")
@Data
@Entity
public class IdeaRoleModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "role_id", insertable = false, nullable = false)
    private Integer roleId;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_remake")
    private String roleRemake;

    @ManyToMany(cascade = CascadeType.ALL,fetch =  FetchType.EAGER)
    @JoinTable(name = "role_permisson",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permisson_id")
    )
    private List<PermissonModel> permisson;
}