package com.example.rs;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.hibernate.validator.constraints.Range;

import com.example.model.Person;
import com.example.validation.EnableBeanValidation;

@Path("/v2/people")
@EnableBeanValidation
public class ValidatingPeopleRestService {
    @Produces( { MediaType.APPLICATION_JSON } )
    @GET
    public @Valid List<Person> getAll(@Range(min = 1, max = 10) @QueryParam("count") final int count) {
        return Collections.nCopies(count, new Person("a@b.com", "A", "B"));
    }
}
