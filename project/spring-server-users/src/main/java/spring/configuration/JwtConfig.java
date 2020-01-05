package spring.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    public final static String JWT_SHARED_SECRET = "secret";
}
