/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import javax.ejb.Remote;
import model.Employees;
import org.hibernate.annotations.Loader;

/**
 *
 * @author serg
 */
@Remote
public interface EmployeeBean {
    
    Employees add(Employees employee);
    Employees get(long id);
    void update(Employees employee);
    void delete(long id);
    List<Employees> getAll();   
    
}
