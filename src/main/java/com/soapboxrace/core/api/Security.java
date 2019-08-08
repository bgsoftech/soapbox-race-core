package com.soapboxrace.core.api;

import com.soapboxrace.core.api.util.Secured;
import com.soapboxrace.core.bo.TokenSessionBO;
import com.soapboxrace.jaxb.http.FraudConfig;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/security")
public class Security {

    @EJB
    private TokenSessionBO tokenSessionBO;

    @GET
    @Secured
    @Path("/fraudConfig")
    @Produces(MediaType.APPLICATION_XML)
    public FraudConfig fraudConfig(@HeaderParam("userId") Long userId) {
        FraudConfig fraudConfig = new FraudConfig();
        fraudConfig.setEnabledBitField(12);
        fraudConfig.setGameFileFreq(1000000);
        fraudConfig.setModuleFreq(360000);
        fraudConfig.setStartUpFreq(1000000);
        fraudConfig.setUserID(userId);
        return fraudConfig;
    }

    @POST
    @Secured
    @Path("/generateWebToken")
    @Produces(MediaType.APPLICATION_XML)
    public String generateWebToken(@HeaderParam("userId") Long userId,
                                   @HeaderParam("securityToken") String securityToken) {
        return "<string>" + tokenSessionBO.generateWebToken(userId, securityToken) + "</string>";
    }
}
