package sn.ept.dic2.dev_mobile.tp.personapp.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import sn.ept.dic2.dev_mobile.tp.personapp.models.NewPerson;
import sn.ept.dic2.dev_mobile.tp.personapp.models.Person;

public interface ProjectDevMobileAPIService {

    @GET("/api/{key}/personnes")
    Call<List<Person>> getAllPersons(@Path("key") String key);

    @GET("/api/{key}/personnes/{email}")
    Call<Person> getPersonByID(@Path("key") String key,@Path("email") String email);

    @PUT("/api/{key}/personnes")
    Call<NewPerson> createPerson(@Path("key") String key,@Body NewPerson person);

    @PUT("/api/{key}/personnes/{email}")
    Call<Person> updatePerson(@Path("key") String key,  @Path("email") String email, @Body Person person);

    @DELETE("/api/{key}/personnes/{email}")
    Call<Response<String>> deletePerson(@Path("key") String key,@Path("email") String email);

}
