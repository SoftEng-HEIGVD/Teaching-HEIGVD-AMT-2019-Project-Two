package heig.vd.ch.projet.auth.api.service;

public interface IAuthenticateService {
    String hashPassword(String password);
    boolean checkPassord(String password,String hashedPassword);
}
