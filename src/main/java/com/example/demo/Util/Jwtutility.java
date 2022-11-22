package com.example.demo.Util;

import com.example.demo.Service.UserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.el.parser.BooleanNode;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class Jwtutility implements Serializable {

    private static final long serialVersionUID = 5335747160327767357L;

    private static final long tokenvalidity = 10 * 60 * 60;

    private String secret = "secret";


    public String getUsernamefromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims,T> claimresolver){
        Claims claims  = getallclaimsfromtoken(token);
        return claimresolver.apply(claims);
    }

    private Claims getallclaimsfromtoken(String token) {
     return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }


    private Boolean istokenexpired(String token){
        Date expiration = getexpirationdatefromtoken(token);
        return  expiration.before(new Date());
    }

    private Date getexpirationdatefromtoken(String token) {
      return getClaimFromToken(token,Claims::getExpiration);
    }

    //generate token
    public  String generatetoken(UserDetail userDetail){
        Map<String, Object> claims  = new HashMap<>();
        return  dogeneratetoken(claims,userDetail.getUsername());
    }

    private String dogeneratetoken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenvalidity * 1000))
                .signWith(SignatureAlgorithm.HS256,secret).compact();
    }

    //validate token
    public Boolean validatetoken(String token, UserDetail userDetail) {
        String username = getUsernamefromToken(token);
        return (username.equals(userDetail.getUsername()) && istokenexpired(token));
    }
}
