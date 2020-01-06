package ch.heigvd.amt.project2.configuration;

import org.springframework.security.core.GrantedAuthority;

public class MyGrantedAuthority implements GrantedAuthority {
    String grantedAuthortity;

    public MyGrantedAuthority(String grantedAuthortity) {
        this.grantedAuthortity = grantedAuthortity;
    }

    @Override
    public String getAuthority() {
        return grantedAuthortity;
    }
}