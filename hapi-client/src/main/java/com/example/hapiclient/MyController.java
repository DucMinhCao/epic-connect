package com.example.hapiclient;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.client.interceptor.BearerTokenAuthInterceptor;
import org.hl7.fhir.r4.model.Patient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient")
public class MyController {

    private static final String EPIC_URL = "https://fhir.epic.com/interconnect-fhir-oauth/api/FHIR/R4/";

    @GetMapping(produces = { "application/xml", "text/xml" }, consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public String getPatient() {
        FhirContext fhirContext = FhirContext.forR4();
        IGenericClient clientFactory = fhirContext.newRestfulGenericClient(EPIC_URL);

        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJ1cm46b2lkOmZoaXIiLCJjbGllbnRfaWQiOiI3YTkyZTY4NC01MGU3LTQxNTQtYTdlYS05YjA4OTU5MDNlZTAiLCJlcGljLmVjaSI6InVybjplcGljOk9wZW4uRXBpYy1jdXJyZW50IiwiZXBpYy5tZXRhZGF0YSI6ImYwd3VHa2wwSElRWk1tLVkwUVRZeG5RNUFwcnl3Tzg2al9XZ0lJellKQ0NUTWRrSFRTV1pTdVBtNW05TTRoVUdsX0hoQURyQ2xZRGQ1NGVlclU4VXNYV1lGS3ZxdkFXM3Q0QVZ2cnY5aFh6eEM5dEVZbkJxbm1oSVNaNUxhODBFIiwiZXBpYy50b2tlbnR5cGUiOiJhY2Nlc3MiLCJleHAiOjE2MjQ1Mjc4NjAsImlhdCI6MTYyNDUyNDI2MCwiaXNzIjoidXJuOm9pZDpmaGlyIiwianRpIjoiOTEwNmNjYTItY2JmZS00NTNjLWI5NjYtYjRkMzQzOWViNDhiIiwibmJmIjoxNjI0NTI0MjYwLCJzdWIiOiJleGZvNkU0RVhqV3NuaEExT0dWRWxndzMifQ.D8QU9Qwe2PRYqWG2dP9tcNy6ZQi-hMsz0C8CyXtDYXQ3nxAjkL_ns3b5e1jFCu4zzwvSqklzmfuPipCcj9mSPnqlV3Jgn6RJRlDxd6L3ZVCANeEe5GGFWDBXRbDapiqGrLr6QT3iAMuYME_YqydJH5W_uL3EU2AcSvnEv1rdmHFjKlt_Tsp1nh6Wbm9Nq8w4pIBSQ6VdTKqnuCWKIvYObm900xz17VBi33eHu5rMO-y8opR8ASz63LVFKLRKRuSU2r89yCSMQgwfZe-6GpMR-NwwT8_77oWOayfsaNMnugxmC2VGVUBrfc5u4iAcLbxNTBTV6s1iBYOldDxADZSnOw";

        BearerTokenAuthInterceptor authInterceptor = new BearerTokenAuthInterceptor(token);

        clientFactory.registerInterceptor(authInterceptor);


        Patient patient = clientFactory.read().resource(Patient.class).withId("erXuFYUfucBZaryVksYEcMg3").execute();

        String string = fhirContext.newXmlParser().setPrettyPrint(true).encodeResourceToString(patient);
        return string;
    }
}
