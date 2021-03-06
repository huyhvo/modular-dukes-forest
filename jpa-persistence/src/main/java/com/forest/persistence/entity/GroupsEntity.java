/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package com.forest.persistence.entity;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

import com.forest.model.Group;
import com.forest.model.Person;

/**
 *
 * @author ievans
 */
@Entity
@Table(name = "GROUPS")
@NamedQueries({
    @NamedQuery(name = "Groups.findAll", query = "SELECT g FROM GroupsEntity g"),
    @NamedQuery(name = "Groups.findById", query = "SELECT g FROM GroupsEntity g WHERE g.id = :id"),
    @NamedQuery(name = "Groups.findByName", query = "SELECT g FROM GroupsEntity g WHERE g.name = :name"),
    @NamedQuery(name = "Groups.findByDescription", query = "SELECT g FROM GroupsEntity g WHERE g.description = :description")})
public class GroupsEntity extends Group {
    
    private static final long serialVersionUID = 1205082528194257031L;
    
    public GroupsEntity() {
    }

    public GroupsEntity(Integer id) {
        this.id = id;
    }

    public GroupsEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50, message="{groups.name}")
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Size(max = 300, message="{groups.description}")
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @XmlTransient
    @ManyToMany(targetEntity=PersonEntity.class, mappedBy = "groupsList")
    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupsEntity)) {
            return false;
        }
        GroupsEntity other = (GroupsEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.forest.entity.Groups[ id=" + id + " ]";
    }
    
}
