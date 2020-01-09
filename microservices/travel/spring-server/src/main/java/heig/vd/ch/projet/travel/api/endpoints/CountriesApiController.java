package heig.vd.ch.projet.travel.api.endpoints;

import heig.vd.ch.projet.travel.api.CountriesApi;
import heig.vd.ch.projet.travel.api.model.Country;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class CountriesApiController implements CountriesApi {
    @Override
    public ResponseEntity<List<Country>> getCountries() {
        return null;
    }
}
