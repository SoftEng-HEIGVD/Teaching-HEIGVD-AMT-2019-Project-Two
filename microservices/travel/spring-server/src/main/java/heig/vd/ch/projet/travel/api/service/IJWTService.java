package heig.vd.ch.projet.travel.api.service;

public interface IJWTService {
    DecodedToken verifyToken(String token);
}
