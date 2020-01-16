package heig.vd.ch.projet.auth.api.spec.steps;

import heig.vd.ch.projet.auth.ApiException;
import heig.vd.ch.projet.auth.api.DefaultApi;
import heig.vd.ch.projet.auth.api.dto.AuthDTO;
import heig.vd.ch.projet.auth.api.spec.helpers.Environment;

public class UtilsStep {
    static AuthDTO i_have_an_admin_credential(){
        AuthDTO authDTO = new AuthDTO();
        authDTO.setEmail("admin@admin.ch");
        authDTO.setPassword("admin");
        return authDTO;
    }

    static AuthDTO i_have_a_user_credential(){
        AuthDTO authDTO = new AuthDTO();
        authDTO.setEmail("user@user.ch");
        authDTO.setPassword("user");
        return authDTO;
    }
}
