package Springlog.Springlog.Services;


import Springlog.Springlog.ConfigSec.JwtService;
import Springlog.Springlog.domain.Role;
import Springlog.Springlog.domain.UserDomain;
import Springlog.Springlog.Repouser.Repouser;
import Springlog.Springlog.Repouser.RoleRepository;
import Springlog.Springlog.dto.AuthResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class Userservice {
    @Autowired
    Repouser repouser ;

    @Autowired
    RoleRepository roleRepository ;

    @Autowired
    PasswordEncoder passwordEncoder ;

    @Autowired
    AuthenticationManager authenticationManager ;

    @Autowired
    JwtService jwtService ;

    public Userservice(Repouser repouser, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.repouser = repouser;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public ResponseEntity<String> register(UserDomain user) {

        if(repouser.findByUsername(user.getUsername()).isPresent()){
            return new ResponseEntity<>("Username is taken", HttpStatus.BAD_REQUEST);

        }
        UserDomain usr = new UserDomain();

        usr.setUsername(user.getUsername());
        usr.setPassword(passwordEncoder.encode(user.getPassword()));

        Role roles = roleRepository.findByName("USER").get();
        usr.setRoles(Collections.singletonList(roles));

        repouser.save(usr);

        return new ResponseEntity<>("success register" ,HttpStatus.OK);

    }



    public ResponseEntity<?> login(UserDomain user) {

        if(repouser.findByUsername(user.getUsername()).isPresent()){
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(),
                            user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtService.generateToken(user);

            return new ResponseEntity<>(new AuthResponseDto(token),HttpStatus.OK);
        }else {
        return new ResponseEntity<>("Invalid credential", HttpStatus.UNAUTHORIZED);
    }
    }




}
