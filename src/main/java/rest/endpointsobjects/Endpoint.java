package rest.endpointsobjects;

import rest.helpers.RestInternalHelper;

// In derived classes: Methods which returns something should return deep copy of object
public abstract class Endpoint {
    RestInternalHelper restHelper = new RestInternalHelper();
}
