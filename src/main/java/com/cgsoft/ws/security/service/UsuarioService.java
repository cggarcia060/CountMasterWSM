package com.cgsoft.ws.security.service;



import com.cgsoft.ws.dto.Mensaje;
import com.cgsoft.ws.exceptions.CustomException;
import com.cgsoft.ws.security.config.BeansConfig;
import com.cgsoft.ws.security.dto.*;
import com.cgsoft.ws.security.entity.Proceso;
import com.cgsoft.ws.security.entity.Rol;
import com.cgsoft.ws.security.entity.Usuario;
import com.cgsoft.ws.security.enums.RolNombre;
import com.cgsoft.ws.security.jwt.JwtProvider;
import com.cgsoft.ws.security.repository.UsuarioRepository;
import com.cgsoft.ws.utils.ScriptionElement;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;

@Service
@Transactional
public class UsuarioService {
    private final static Logger logger = LoggerFactory.getLogger(UsuarioService.class);
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    BeansConfig beansConfig;
    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    ProcesoService procesoService;

    @Autowired
    ScriptionElement scriptionElement;

    public Usuario getByNombreUsuario(String nombreUsuario){
        return usuarioRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(()-> new CustomException(HttpStatus.NOT_FOUND,"ese usuario no existe"));
    }

    public Optional<Usuario> getByNombreUsuarioOrEmail(String nombreOrEmail){
        return usuarioRepository.findByNombreUsuarioOrEmail(nombreOrEmail, nombreOrEmail);
    }

    public Optional<Usuario> getByTokenPassword(String tokenPassword){
        return usuarioRepository.findByTokenPassword(tokenPassword);
    }

    public boolean existsByNombreUsuario(String nombreUsuario){
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

    public boolean existsByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public JwtDto login(LoginUsuario loginUsuario){
        String usuario;
        String password;
        try {
         usuario=scriptionElement.decrypt(loginUsuario.getNombreUsuario());
         password= scriptionElement.decrypt(loginUsuario.getPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken( usuario,password ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        return new JwtDto(jwt);
    }

    public JwtDto refresh(JwtDto jwtDto) throws ParseException {

        String token = jwtProvider.refreshToken(jwtDto);
        return new JwtDto(token);
    }

    public Mensaje save(NuevoUsuario nuevoUsuario){

        if(usuarioRepository.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            throw new CustomException(HttpStatus.BAD_REQUEST, "ese nombre de usuario ya existe");
        if(usuarioRepository.existsByEmail(nuevoUsuario.getEmail()))
            throw new CustomException(HttpStatus.BAD_REQUEST, "ese email de usuario ya existe");
        Usuario usuario =
                Usuario.builder().nombre(nuevoUsuario.getNombre()).apellido(nuevoUsuario.getApellido()).nombreUsuario(nuevoUsuario.getNombreUsuario())
                        .email(nuevoUsuario.getEmail()).roles(nuevoUsuario.getRoles())
                        .procesos(nuevoUsuario.getProcesos()).identificacion(nuevoUsuario.getIdentificacion())
                        .password(passwordEncoder.encode(nuevoUsuario.getPassword())).build();
        usuarioRepository.save(usuario);
        return new Mensaje(usuario.getNombreUsuario() + " ha sido creado");
    }


    public   Map<String, String> listAllUsuario() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString=objectMapper.writeValueAsString(usuarioRepository.findAll());
        try{
            Map<String,String> usuarios=new HashMap<String,String>();
            usuarios.put("usuarios", ScriptionElement.encrypt(jsonString));
            return  usuarios;
        }catch (Exception e){
            throw new CustomException(HttpStatus.BAD_REQUEST,"Hubo un error al cargar los usuarios.");
        }

    }
    public  Map<String, String> getUsuariosByProcess(RequestContainer requestContainer) throws JsonProcessingException {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString;

            if (requestContainer.getRol().getRolNombre().equals("ROLE_SUPERADMIN")){
                jsonString=objectMapper.writeValueAsString(
                        usuarioRepository.findAllByProcesos(requestContainer.getProceso()));
            }else{
                jsonString=objectMapper.writeValueAsString(
                        usuarioRepository.findAllByProcesosAndNombreUsuarioNot(requestContainer.getProceso(),"superadmin"));
            }

            try{
                Map<String,String> usuarios=new HashMap<String,String>();
                usuarios.put("usuarios", ScriptionElement.encrypt(jsonString));
                return  usuarios;
            }catch (Exception e){
                throw new CustomException(HttpStatus.BAD_REQUEST,"Hubo un error al cargar los usuarios.");
            }
    }
    public Mensaje delete(Long id){
        usuarioRepository.deleteById(id);
        return new Mensaje("Usuario eleminado");
    }


    public Usuario getUsuarioById(Long id){
      return  usuarioRepository.findById(id)
              .orElseThrow(()->new CustomException(HttpStatus.NOT_FOUND,"ese usuario no existe"));
    }


    public Mensaje update(UsuarioDto dto){

        if(usuarioRepository.existsByNombreUsuarioAndIdNot(dto.getNombreUsuario(),dto.getId()))
            throw new CustomException(HttpStatus.BAD_REQUEST, "ese nombre de usuario ya existe");
        if(usuarioRepository.existsByEmailAndIdNot(dto.getEmail(), dto.getId()))
            throw new CustomException(HttpStatus.BAD_REQUEST, "ese email de usuario ya existe");
        if (dto.getProcesos().isEmpty())
            throw new CustomException(HttpStatus.BAD_REQUEST, "El proceso es obligatorio");
        if (dto.getRoles().isEmpty())
            throw new CustomException(HttpStatus.BAD_REQUEST, "El rol es obligatorio");
        Usuario usuario=getUsuarioById(dto.getId());
        usuario.setNombreUsuario(dto.getNombreUsuario());
        usuario.setApellido(dto.getApellido());
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setProcesos(dto.getProcesos());
        usuario.setRoles(dto.getRoles());
        usuarioRepository.save(usuario);
        return new Mensaje(usuario.getNombreUsuario() + " ha sido actualizado");
    }


}