package ch.heigvd.amt.project2.security;

public class SecurityConstants {
    public static final String SECRET = "woWkw9K?Y8U+dn7";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/sign-up";
}