package rest.endpointsobjects;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import rest.helpers.RestInternalHelper;

public abstract class Endpoint {

    RestInternalHelper restHelper = new RestInternalHelper();
}
