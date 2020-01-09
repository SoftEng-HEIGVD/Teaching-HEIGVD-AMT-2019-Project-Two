package heig.vd.ch.projet.travel.api.service;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class DecodedToken {
    String email;
    String role;
}
