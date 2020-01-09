package heig.vd.ch.projet.travel.api.endpoints;

import heig.vd.ch.projet.travel.api.TripsApi;
import heig.vd.ch.projet.travel.api.model.Trip;
import heig.vd.ch.projet.travel.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class TripsApiController implements TripsApi {

    @Autowired
    TripRepository tripRepository;

    @Autowired
    HttpServletRequest request;


    @Override
    public ResponseEntity<List<Trip>> getTrips() {
        return null;
    }
}
