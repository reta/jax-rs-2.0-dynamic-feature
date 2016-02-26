package com.example.rs;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.hibernate.validator.constraints.Range;

import com.example.model.Person;

@Path("/v1/people")
public class PeopleRestService {
    /**
     * The @Range validation won't be triggered because the validation feature is not configured for this JAX-RS API
     */
    @Produces( { MediaType.APPLICATION_JSON } )
    @GET
    public List<Person> getAll(@Range(min = 1, max = 10) @QueryParam("count") final int count) {
        return Collections.nCopies(count, new Person("a@b.com", "A", "B"));
    }
}
