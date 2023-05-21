package at.ac.fhcampuswien.lazychatter.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@Service
public class JwtService {
    @Autowired
    private JwtDecoder jwtDecoder;

    @Autowired
    private JwtEncoder jwtEncoder;

    public String extractUsername(String token){
        return (String) extractAllClaims(token).get("name");
    }

    public String generateToken(UserDetails userDetails){
        Instant now = Instant.now();
        StringBuilder sb = new StringBuilder();
        for(GrantedAuthority auth: userDetails.getAuthorities()){
            sb.append(auth.getAuthority()+",");
        }
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(60*60*24))
                .subject(userDetails.getUsername())
                .claim("authorities", sb.toString())
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public boolean validateToken(String token){
        return jwtDecoder.decode(token).getExpiresAt().isAfter(Instant.now());
    }

    private Map<String, Object> extractAllClaims(String token){
        return jwtDecoder.decode(token).getClaims();
    }
}
