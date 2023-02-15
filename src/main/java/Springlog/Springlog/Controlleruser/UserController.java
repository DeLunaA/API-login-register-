package Springlog.Springlog.Controlleruser;

import Springlog.Springlog.Entity.Role;
import Springlog.Springlog.Entity.User;
import Springlog.Springlog.Repouser.Repouser;
import Springlog.Springlog.Repouser.RoleRepository;
import Springlog.Springlog.Services.Userservice;
import Springlog.Springlog.dto.LoginDto;
import Springlog.Springlog.dto.RegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api")
public class UserController {


    // Log4j

    private Repouser repouser;

    private RoleRepository roleRepository;

    private AuthenticationManager authenticationManager;

    private PasswordEncoder passwordEncoder;
    @Autowired
    Userservice userservice;

    @PostMapping("/register")
    public ResponseEntity<?> Register(@RequestBody User user) {
        return userservice.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody User user) {
        return userservice.login(user);
    }


    class LoginVm
    {
        private String login;
        private String password;
        private boolean rememberMe;
    }


}
