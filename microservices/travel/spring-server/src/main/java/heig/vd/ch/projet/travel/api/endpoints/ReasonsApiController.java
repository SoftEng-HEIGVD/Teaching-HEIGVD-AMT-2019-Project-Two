package heig.vd.ch.projet.travel.api.endpoints;

import heig.vd.ch.projet.travel.api.ReasonsApi;
import heig.vd.ch.projet.travel.api.model.Reason;
import heig.vd.ch.projet.travel.entities.ReasonEntity;
import heig.vd.ch.projet.travel.repositories.ReasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class ReasonsApiController implements ReasonsApi{

    @Autowired
    ReasonRepository reasonRepository;

    @Autowired
    HttpServletRequest request;

    @Override
    public ResponseEntity<List<Reason>> getReasons() {
        List<Reason> reasons = reasonRepository.findAll().map(this::toCountry).toList();

        return ResponseEntity.ok(reasons);
    }

    /*Utils*/
    private  Reason toCountry(ReasonEntity reasonEntity) {
        Reason reason = new Reason();
        reason.setIdReason(reasonEntity.getIdReason());
        reason.setName(reasonEntity.getName());

        return reason;
    }
}
