package Springlog.Springlog.Services;


import Springlog.Springlog.ConfigSec.httpsec;
import Springlog.Springlog.Entity.Role;
import Springlog.Springlog.Entity.User;
import Springlog.Springlog.Repouser.Repouser;
import Springlog.Springlog.Repouser.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;

@Service
public class Userservice {
    @Autowired
    Repouser repouser ;

    @Autowired
    RoleRepository roleRepository ;



    @Autowired
    PasswordEncoder passwordEncoder ;

    @Autowired
    AuthenticationManager authenticationManager ;

    public Userservice(Repouser repouser, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.repouser = repouser;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }



    public ResponseEntity<String> register(User user) {

        if(repouser.findByUsername(user.getUsername()).isPresent()){
            return new ResponseEntity<>("Username is taken", HttpStatus.BAD_REQUEST);

        }
        User usr = new User();

        usr.setUsername(user.getUsername());
        usr.setPassword(passwordEncoder.encode(user.getPassword()));

        Role roles = roleRepository.findByName("USER").get();
        usr.setRoles(Collections.singletonList(roles));

        repouser.save(usr);

        return new ResponseEntity<>("success register" ,HttpStatus.OK);

    }

    public ResponseEntity<?> login(User user) {

        if(repouser.findByUsername(user.getUsername()).isPresent()){
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(),
                            user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new ResponseEntity<>("Signed successfully",HttpStatus.OK);

        }else   {

        return new ResponseEntity<>("Invalid", HttpStatus.BAD_REQUEST);
    }





}}
