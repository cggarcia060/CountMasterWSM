package com.cgsoft.ws.security.jwt;


import com.cgsoft.ws.exceptions.CustomException;
import com.cgsoft.ws.security.dto.JwtDto;
import com.cgsoft.ws.security.entity.Permisos;
import com.cgsoft.ws.security.entity.Proceso;
import com.cgsoft.ws.security.entity.Rol;
import com.cgsoft.ws.security.entity.UsuarioPrincipal;
import com.cgsoft.ws.security.enums.RolNombre;
import com.cgsoft.ws.security.service.PermisosService;
import com.cgsoft.ws.security.service.ProcesoService;
import com.cgsoft.ws.security.service.RolService;
import com.cgsoft.ws.security.service.UsuarioService;
import com.cgsoft.ws.utils.ScriptionElement;
import com.nimbusds.jose.Header;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.SignedJWT;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtProvider {
    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Autowired
    PermisosService permisosService;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    @Autowired
    ScriptionElement scriptionElement;

    @Autowired
    ProcesoService procesoService;
    @Autowired
    RolService rolService;
    public String generateToken(Authentication authentication){
        logger.error("entro a generateToken "+authentication);
        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
        List<String> procesos = usuarioPrincipal.getProcesos().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        List<String> roles = usuarioPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        ArrayList<Proceso> listProceso=new ArrayList<>();
        ArrayList<Rol> listRoles=new ArrayList<>();

        for(String proceso:procesos){
            listProceso.add(procesoService.getProcesoByNombre(proceso));
        }
        for(String rol :roles){
            listRoles.add(rolService.getByRolNombre(rol));
        }

        String token=Jwts.builder()
                .setSubject(usuarioPrincipal.getUsername())
                .claim("roles", listRoles)
                .claim("procesos",listProceso)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration ))
                .signWith(getSecret(secret))
                .compact();
        try{
        return  scriptionElement.encrypt(token);
        }catch (Exception e){
            throw new CustomException(HttpStatus.BAD_REQUEST,"hubo un error al iniciar sesion");
        }

    }

    public String getNombreUsuarioFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("token mal formado");
        } catch (UnsupportedJwtException e) {
            logger.error("token no soportado");
        } catch (ExpiredJwtException e) {
            logger.error("token expirado");
        } catch (IllegalArgumentException e) {
            logger.error("token vacío");
        } catch (SignatureException e) {
            logger.error("fail en la firma");
        }
        return false;
    }

    public String refreshToken(JwtDto jwtDto) throws ParseException {
        logger.error("entro a refresh "+jwtDto.getToken());
        String token;
        try {
            token=ScriptionElement.decrypt(jwtDto.getToken());
        }catch (Exception e){
            throw new CustomException(HttpStatus.BAD_REQUEST,"hubo un error en el refrestoken");
        }
        try {
            Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            JWT jwt = JWTParser.parse(token);
            JWTClaimsSet claims = jwt.getJWTClaimsSet();
            String nombreUsuario = claims.getSubject();
            List<String> roles = (List<String>) claims.getClaim("roles");
            String newToken= Jwts.builder()
                    .setSubject(nombreUsuario)
                    .claim("roles", roles)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime() + expiration ))
                    .signWith(getSecret(secret))
                    .compact();
            try{
                return  scriptionElement.encrypt(newToken);
            }catch (Exception es){
                throw new CustomException(HttpStatus.BAD_REQUEST,"hubo un error al iniciar sesion");
            }

        }
        return null;
    }

    private Key getSecret(String secret){
        byte[] secretBytes = Decoders.BASE64URL.decode(secret);
        return Keys.hmacShaKeyFor(secretBytes);
    }

}