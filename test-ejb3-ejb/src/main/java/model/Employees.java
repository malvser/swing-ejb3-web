/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
 
 
/**
 *
 * @author serg
 */
@Entity
@Table(name = "employees")
//@NamedQuery( name = "Employees.findAll", query = "SELECT e FROM employees e" )
public class Employees implements Serializable {
    
    private static final long serialVersionUID = 7422574264557894633L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    
    @Column(name = "alias")
    private String alias;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "firstName")
    private String firstName;
    
    @Column(name = "secondName")
    private String secondName;
    
    @Column(name = "lastName")
    private String lastName;
    
    @Column(name = "createDate")
    private Date createDate;
    
    @Column(name = "originalid")
    private Long originalid;
    
    @Column(name = "refined")
    private String refined;
    
    @Column(name = "sortNo")
    private Long sortNo;
    
    @Column(name = "hidden")
    private Long hidden;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "salt")
    private String salt;

    public Employees() {
    }

    public Employees(long id, String alias, String name, String firstName, String secondName, String lastName, Date createDate, Long originalid, String refined, Long sortNo, Long hidden, String password, String salt) {
        this.id = id;
        this.alias = alias;
        this.name = name;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.createDate = createDate;
        this.originalid = originalid;
        this.refined = refined;
        this.sortNo = sortNo;
        this.hidden = hidden;
        this.password = password;
        this.salt = salt;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getOriginalid() {
        return originalid;
    }

    public void setOriginalid(Long originalid) {
        this.originalid = originalid;
    }

    public Long getSortNo() {
        return sortNo;
    }

    public void setSortNo(Long sortNo) {
        this.sortNo = sortNo;
    }

    public Long getHidden() {
        return hidden;
    }

    public void setHidden(Long hidden) {
        this.hidden = hidden;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    

    public String getRefined() {
        return refined;
    }

    public void setRefined(String refined) {
        this.refined = refined;
    }

    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", alias=" + alias + ", name=" + name + ", firstName=" + firstName + ", secondName=" + secondName + ", lastName=" + lastName + ", createDate=" + createDate + ", originalid=" + originalid + ", refined=" + refined + ", sortNo=" + sortNo + ", hidden=" + hidden + ", password=" + password + ", salt=" + salt + '}';
    }
    
    
    
    
    
    
}
