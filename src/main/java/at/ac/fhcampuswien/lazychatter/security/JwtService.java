package at.ac.fhcampuswien.lazychatter.security;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.Map;

@Service
public class JwtService {
    @Value("${jwt.public.key}")
    private RSAPublicKey key;
    @Value("${jwt.private.key}")
    private RSAPrivateKey priv;

    public String extractUsername(String token){
        return (String) extractAllClaims(token).get("sub");
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

        return jwtEncoder().encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public boolean validateToken(String token){
        return jwtDecoder().decode(token).getExpiresAt().isAfter(Instant.now());
    }

    private Map<String, Object> extractAllClaims(String token){
        return jwtDecoder().decode(token).getClaims();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(key).build();
    }

    @Bean
    JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(this.key).privateKey(this.priv).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }
}
