package heig.vd.ch.projet.auth.api.service;

import heig.vd.ch.projet.auth.entities.UserEntity;

public interface IJWTService {
    DecodedToken verifyToken(String token);
    String createToken(UserEntity userEntity);
}
