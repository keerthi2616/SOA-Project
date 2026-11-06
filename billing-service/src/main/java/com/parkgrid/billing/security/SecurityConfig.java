package com.parkgrid.billing.security;
import javax.crypto.SecretKey; import javax.crypto.spec.SecretKeySpec; import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Value; import org.springframework.context.annotation.*; import org.springframework.security.config.annotation.web.builders.HttpSecurity; import org.springframework.security.oauth2.jwt.*; import org.springframework.security.oauth2.server.resource.authentication.*; import org.springframework.security.web.SecurityFilterChain;
@Configuration public class SecurityConfig {
 @Bean SecurityFilterChain filterChain(HttpSecurity http,JwtDecoder decoder)throws Exception{http.csrf(c->c.disable()).authorizeHttpRequests(a->a.requestMatchers("/actuator/health","/actuator/info").permitAll().requestMatchers("/api/billing/revenue").hasRole("ADMIN").requestMatchers("/api/billing/**").hasAnyRole("USER","ADMIN").anyRequest().authenticated()).oauth2ResourceServer(o->o.jwt(j->j.jwtAuthenticationConverter(converter())));return http.build();}
 @Bean JwtDecoder jwtDecoder(@Value("${jwt.secret}")String s){SecretKey k=new SecretKeySpec(s.getBytes(StandardCharsets.UTF_8),"HmacSHA256");return NimbusJwtDecoder.withSecretKey(k).build();}
 private JwtAuthenticationConverter converter(){JwtGrantedAuthoritiesConverter g=new JwtGrantedAuthoritiesConverter();g.setAuthoritiesClaimName("roles");g.setAuthorityPrefix("ROLE_");JwtAuthenticationConverter c=new JwtAuthenticationConverter();c.setJwtGrantedAuthoritiesConverter(g);return c;}
}
