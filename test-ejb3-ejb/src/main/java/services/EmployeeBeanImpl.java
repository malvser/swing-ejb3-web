package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import model.Employees;

/**
 *
 * @author serg
 */


@Stateless
@Remote (EmployeeBean.class)
public class EmployeeBeanImpl implements EmployeeBean {
   
    
    @PersistenceContext(unitName = "JPADB")
    private EntityManager em;

    @Override
    @Transactional
    public Employees add(Employees employee){
        return em.merge(employee);
    }

    @Override
    public Employees get(long id){
        return em.find(Employees.class, id);
    }

    @Override
    @Transactional
    public void update(Employees employee){
        add(employee);
    }

    @Override
    public void delete(long id){
        em.remove(get(id));
    }    
    

    @Override
    public List<Employees> getAll(){
        
        return em.createQuery("SELECT e FROM Employees e Where e.originalid IS NULL", Employees.class).getResultList();
    }
    
  
    private static final Logger LOG = Logger.getLogger(EmployeeBeanImpl.class.getName());
}
