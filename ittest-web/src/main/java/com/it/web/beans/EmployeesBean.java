package com.it.web.beans;

/**
 *
 * @author andrey
 */

import model.Employees;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Named;
import java.io.Serializable;
import services.EmployeeBean;

/**
 *
 * @author andrey
 *
 */

@Named
@SessionScoped
public class EmployeesBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private EmployeeBean employeeManagerBeanLocale;

    private Employees employee;

    private ListDataModel<Employees> employees;

    public EmployeesBean() {
    }

    @PostConstruct
    public void init() {
        employee = new Employees();
        employees = new ListDataModel<>();
    }

    public Employees getEmployee() {
        employees.setWrappedData(employeeManagerBeanLocale.getAll());
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    public ListDataModel<Employees> getEmployees() {
        return employees;
    }

    public void setEmployees(ListDataModel<Employees> employees) {
        this.employees = employees;
    }
}