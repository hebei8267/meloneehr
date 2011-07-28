package com.ppiaobuy.entity.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.ppiaobuy.modules.hibernate.entity.AuditableEntity;

/**
 * 角色
 */
@Entity
@Table(name = "T_ROLE")
public class Role extends AuditableEntity {

    private String name;

    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
