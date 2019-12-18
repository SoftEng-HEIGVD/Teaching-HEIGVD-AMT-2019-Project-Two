package heig.vd.ch.projet.auth.api.service;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class DecodedToken {
    String email;
    String role;
}
