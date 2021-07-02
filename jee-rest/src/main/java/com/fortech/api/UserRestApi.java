package com.fortech.api;

import com.fortech.dao.UserDao;
import com.fortech.model.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRestApi {

    @Inject
    UserDao userDao;

    @GET
    public Response getAll() {
        return Response.ok(userDao.getAll()).build();
    }

    @GET
    @Path("{id}")
    public Response getUser(@PathParam("id") Long id) {
        User user = userDao.findById(id);

        return Response.ok(user).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, User user) {
        User updateUser = userDao.findById(id);

        updateUser.setName(user.getName());
        updateUser.setSurname(user.getSurname());
        userDao.update(updateUser);

        return Response.ok().build();
    }

    @POST
    public Response create(User user) {
        userDao.create(user);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        User user = userDao.findById(id);

        userDao.delete(user);

        return Response.ok().build();
    }

}
