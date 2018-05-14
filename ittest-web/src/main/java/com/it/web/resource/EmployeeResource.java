package com.it.web.resource;

import model.Employees;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import services.EmployeeBean;

/**
 *
 * @author andrey
 *
 */

@Path("employees")
@Stateless
public class EmployeeResource {

    @EJB
    private EmployeeBean employeesBean;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Employees employee) {
        employeesBean.add(employee);
        return Response.created(URI.create("/employees/" + employee.getId())).entity(employee).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("{id:\\d+}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findOne(@PathParam("id") long id) {
        Employees product = employeesBean.get(id);
        if (product == null) {
            Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(product).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employees> findAll() {
        return employeesBean.getAll();
    }

    @PUT
    @Path("{id:\\d+}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void update(@PathParam("id") long id, Employees employee) {
        if (employeesBean.get(id) == null) {
            Response.status(Response.Status.NOT_FOUND).build();
        }
        employee.setId(id);
        employeesBean.update(employee);
    }

    @PUT
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void update(Employees employee) {
        employeesBean.update(employee);
    }

    @DELETE
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void delete(Employees employee) {
        employeesBean.delete(employee.getId());
    }

    @DELETE
    @Path("{id:\\d+}")
    public void delete(@PathParam("id") long id) {
        Employees employee = employeesBean.get(id);
        if (employee == null) {
            Response.status(Response.Status.NOT_FOUND).build();
        }
        employeesBean.delete(employee.getId());
    }
}
