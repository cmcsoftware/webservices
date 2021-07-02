package com.fortech.api;

import com.fortech.dao.AnimalDao;
import com.fortech.model.Animal;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Path("animal")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AnimalRestApi {

    @Inject
    AnimalDao animalDao;

    @GET
    public Response getAll() {
        List animals = animalDao.getAll();
        if (animals.size() == 5) {
            return Response.serverError().entity("Sunt 5 animale, nu e eroare dar sa vedem ce zice").build();
        }
        if (animals.size() == 6) {
            Animal eroare = new Animal();
            eroare.setId(2L);
            eroare.setName("eroare");
            return Response.serverError().entity(eroare).build();
        }
        return Response.ok(animalDao.getAll()).build();
    }

    @GET
    @Path("{id}")
    public Response getAnimal(@PathParam("id") Long id) {
        Animal animal = animalDao.findById(id);

        return Response.ok(animal).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, Animal animal) {
        Animal updateAnimal = animalDao.findById(id);

        updateAnimal.setName(animal.getName());
        animalDao.update(updateAnimal);

        return Response.ok().build();
    }

    @POST
    public Response create(Animal animal) {
        try {
            animalDao.create(animal);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().entity("Ceva eroare la insert").build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        Animal animal = animalDao.findById(id);

        animalDao.delete(animal);

        return Response.ok().build();
    }

}
