package ada.research.ecommmono.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class JwtService {

    private static final Logger logger = Logger.getLogger(JwtService.class.getName());

    @Value("${application.security.jwt.secret-key}")
    private String SECRET_KEY;

    @Value("${application.security.jwt.expiration}")
    private Long EXPIRATION;

    public String extractEmail(String token) {
        logger.log(Level.INFO, "JwtService - extractEmail method started");
        String email = extractClaim(token, Claims::getSubject);
        logger.log(Level.INFO, "JwtService - extractEmail method ended");
        return email;
    }

    public Date extractExpiration(String token){
        logger.log(Level.INFO, "JwtService - extractExpiration method started");
        Date expiration = extractClaim(token, Claims::getExpiration);
        logger.log(Level.INFO, "JwtService - extractExpiration method ended");
        return expiration;
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsFunction){
        logger.log(Level.INFO, "JwtService - extractClaim method started");
        final Claims claims = extractAllClaims(token);
        T result = claimsFunction.apply(claims);
        logger.log(Level.INFO, "JwtService - extractClaim method ended");
        return result;
    }

    public String generateToken(UserDetails userDetails){
        logger.log(Level.INFO, "JwtService - generateToken method started");
        String token = generateToken(new HashMap<>(), userDetails);
        logger.log(Level.INFO, "JwtService - generateToken method ended");
        return token;
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ){
        logger.log(Level.INFO, "JwtService - generateToken method started");
        String token = Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
        logger.log(Level.INFO, "JwtService - generateToken method ended");
        return token;
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        logger.log(Level.INFO, "JwtService - isTokenValid method started");
        final String username = extractEmail(token);
        boolean isValid = (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        logger.log(Level.INFO, "JwtService - isTokenValid method ended");
        return isValid;
    }

    private boolean isTokenExpired(String token) {
        logger.log(Level.INFO, "JwtService - isTokenExpired method started");
        boolean isExpired = extractExpiration(token).before(new Date());
        logger.log(Level.INFO, "JwtService - isTokenExpired method ended");
        return isExpired;
    }

    private Claims extractAllClaims(String token){
        logger.log(Level.INFO, "JwtService - extractAllClaims method started");
        Claims allClaims = Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        logger.log(Level.INFO, "JwtService - extractAllClaims method ended");
        return allClaims;
    }

    private Key getSignInKey() {
        logger.log(Level.INFO, "JwtService - getSignInKey method started");
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        Key signInKey = Keys.hmacShaKeyFor(keyBytes);
        logger.log(Level.INFO, "JwtService - getSignInKey method ended");
        return signInKey;
    }
}
