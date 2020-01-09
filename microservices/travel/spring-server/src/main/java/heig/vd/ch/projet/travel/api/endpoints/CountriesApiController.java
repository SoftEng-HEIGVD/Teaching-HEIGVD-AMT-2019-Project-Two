package heig.vd.ch.projet.travel.api.endpoints;

import heig.vd.ch.projet.travel.api.CountriesApi;
import heig.vd.ch.projet.travel.api.model.Country;
import heig.vd.ch.projet.travel.entities.CountryEntity;
import heig.vd.ch.projet.travel.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class CountriesApiController implements CountriesApi {

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    HttpServletRequest request;

    @Override
    public ResponseEntity<List<Country>> getCountries() {
        List<Country> countries = countryRepository.findAll().map(this::toCountry).toList();
        return ResponseEntity.ok(countries);
    }

    /*Utils*/
    private  Country toCountry(CountryEntity countryEntity) {
        Country country = new Country();
        country.setIdCountry(countryEntity.getIdCountry());
        country.setName(countryEntity.getName());

        return country;
    }
}
