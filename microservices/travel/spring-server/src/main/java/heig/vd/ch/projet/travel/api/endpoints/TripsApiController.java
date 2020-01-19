package heig.vd.ch.projet.travel.api.endpoints;

import heig.vd.ch.projet.travel.api.TripsApi;
import heig.vd.ch.projet.travel.api.model.Country;
import heig.vd.ch.projet.travel.api.model.Reason;
import heig.vd.ch.projet.travel.api.model.Trip;
import heig.vd.ch.projet.travel.api.model.TripDTO;
import heig.vd.ch.projet.travel.api.service.DecodedToken;
import heig.vd.ch.projet.travel.entities.CountryEntity;
import heig.vd.ch.projet.travel.entities.ReasonEntity;
import heig.vd.ch.projet.travel.entities.TripEntity;
import heig.vd.ch.projet.travel.entities.UserEntity;
import heig.vd.ch.projet.travel.repositories.CountryRepository;
import heig.vd.ch.projet.travel.repositories.ReasonRepository;
import heig.vd.ch.projet.travel.repositories.TripRepository;
import heig.vd.ch.projet.travel.repositories.UserRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class TripsApiController implements TripsApi {

    @Autowired
    TripRepository tripRepository;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    ReasonRepository reasonRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    HttpServletRequest request;

    @Override
    public ResponseEntity<Void> createTrip(@ApiParam(value = "" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization,
                                           @ApiParam(value = "", required = true) @Valid @RequestBody Trip trip) {
        DecodedToken decodedToken = (DecodedToken) request.getAttribute("decodedToken");

        //Check if user exist. If not we create him
        addUserIfNotExist(decodedToken.getEmail());

        //Create a trip
        TripEntity tripEntity = toTripEntity(trip,decodedToken.getEmail());

        //Save the trip
        tripRepository.save(tripEntity);

        //Return a created status (201)
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> deleteTrip(@ApiParam(value = "" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization,
                                           @ApiParam(value = "" ,required=true) @PathVariable("idTrip") Integer idTrip) {

        try {
            DecodedToken decodedToken = (DecodedToken) request.getAttribute("decodedToken");

            //Get the existing trip by id
            TripEntity tripEntity = tripRepository.findById(idTrip).get();
            if(tripEntity.getEmail().equals(decodedToken.getEmail()) || decodedToken.getRole().equals("admin")){

                //Delete the trip
                tripRepository.delete(tripEntity);

                //Return an no content status (204)
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }else {
                //Return an forbidden (403)
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }catch (NoSuchElementException e){
            //Return an not found status status (404)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<TripDTO> getTripById(@ApiParam(value = "" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization,
                                               @ApiParam(value = "idTrip" ,required=true) @PathVariable("idTrip") Integer idTrip) {
        try {
            DecodedToken decodedToken = (DecodedToken) request.getAttribute("decodedToken");

            //Check if user exist. If not we create him
            addUserIfNotExist(decodedToken.getEmail());

            //Get the page of tripEntity
            TripEntity tripEntity = tripRepository.findById(idTrip).get();

            if(tripEntity.getEmail().equals(decodedToken.getEmail()) || decodedToken.getRole().equals("admin")){
                TripDTO tripDTO = toTripDTO(tripEntity);

                //Return an array of trip and an ok status (200)
                return ResponseEntity.ok(tripDTO);
            }else{
                //Return an forbidden (403)
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }catch (IllegalArgumentException e){
            //Return an bad request status (400)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    public ResponseEntity<List<TripDTO>> getTrips(@ApiParam(value = "" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization,
                                                  @ApiParam(value = "", defaultValue = "0") @Valid @RequestParam(value = "offset", required = false, defaultValue="0") Integer offset,
                                                  @ApiParam(value = "", defaultValue = "10") @Valid @RequestParam(value = "limit", required = false, defaultValue="10") Integer limit) {

        try {
            DecodedToken decodedToken = (DecodedToken) request.getAttribute("decodedToken");

            //Check if user exist. If not we create him
            addUserIfNotExist(decodedToken.getEmail());

            //Get the page of tripEntity
            Page<TripEntity> tripEntities = tripRepository.findAllByEmailEquals(decodedToken.getEmail(), PageRequest.of(offset,limit));

            //Get all trips by email
            List<TripDTO> tripDTOS = tripEntities.get().map(tripEntity -> toTripDTO(tripEntity)).collect(Collectors.toList());


            //List<TripDTO> tripDTOS = tripRepository.findAllByEmailEquals(decodedToken.getEmail()).map(this::toTripDTO).toList();

            //Return an array of trip and an ok status (200)
            return ResponseEntity.ok(tripDTOS);
        }catch (IllegalArgumentException e){
            //Return an bad request status (400)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    public ResponseEntity<Void> updateTrip(@ApiParam(value = "" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization,
                                           @ApiParam(value = "idTrip" ,required=true) @PathVariable("idTrip") Integer idTrip,
                                           @ApiParam(value = "", required = true) @Valid @RequestBody Trip trip) {
        try {
            DecodedToken decodedToken = (DecodedToken) request.getAttribute("decodedToken");

            //Get the existing trip by id
            TripEntity tripEntity = tripRepository.findById(idTrip).get();

            if(tripEntity.getEmail().equals(decodedToken.getEmail()) || decodedToken.getRole().equals("admin")){


                ReasonEntity reasonEntity = reasonRepository.findById(trip.getReason().getIdReason()).get();
                CountryEntity countryEntity = countryRepository.findById(trip.getCountry().getIdCountry()).get();

                //Update the trip
                tripEntity.setDate(Date.valueOf(trip.getDate()));
                tripEntity.setVisited(trip.getVisited());
                tripEntity.setReasonEntity(reasonEntity);
                tripEntity.setCountryEntity(countryEntity);

                //Save the trip
                tripRepository.save(tripEntity);

                //Return an accept status (202)
                return ResponseEntity.accepted().build();
            }else {
                //Return an forbidden (403)
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }catch (NoSuchElementException e){
            //Return an not found status status (404)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /*Utils*/
    private void addUserIfNotExist(String email){
        if(!userRepository.existsById(email)){
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail(email);
            userRepository.save(userEntity);
        }
    }

    private  TripDTO toTripDTO(TripEntity tripEntity) {
        TripDTO trip = new TripDTO();
        trip.setIdTrip(tripEntity.getIdTrip());
        trip.setVisited(tripEntity.isVisited());
        trip.setDate(tripEntity.getDate().toString());
        trip.setUserEmail(tripEntity.getEmail());

        Reason reason = new Reason();
        reason.setIdReason(tripEntity.getReasonEntity().getIdReason());
        reason.setName(tripEntity.getReasonEntity().getName());
        trip.setReason(reason);

        Country country = new Country();
        country.setIdCountry(tripEntity.getCountryEntity().getIdCountry());
        country.setName(tripEntity.getCountryEntity().getName());
        trip.setCountry(country);

        return trip;
    }

    private  TripEntity toTripEntity(Trip trip,String userEmail) {
        TripEntity tripEntity = new TripEntity();

        tripEntity.setVisited(trip.getVisited());
        tripEntity.setDate(Date.valueOf(trip.getDate()));
        tripEntity.setEmail(userEmail);

        ReasonEntity reasonEntity = reasonRepository.findById(trip.getReason().getIdReason()).get();
        tripEntity.setReasonEntity(reasonEntity);

        CountryEntity countryEntity = countryRepository.findById(trip.getCountry().getIdCountry()).get();
        tripEntity.setCountryEntity(countryEntity);

        return tripEntity;
    }
}
