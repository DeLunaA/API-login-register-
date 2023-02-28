package Springlog.Springlog.Controlleruser;

import Springlog.Springlog.domain.UserDomain;
import Springlog.Springlog.Repouser.Repouser;
import Springlog.Springlog.Repouser.RoleRepository;
import Springlog.Springlog.Services.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    public UserController() {
    }






    // Log4j
    private Repouser repouser;

    private RoleRepository roleRepository;

    private AuthenticationManager authenticationManager;

    private PasswordEncoder passwordEncoder;
    @Autowired
    Userservice userservice;//****


    @PostMapping("/register")
    public ResponseEntity<?> Register(@RequestBody UserDomain user) {
        return userservice.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody UserDomain user) {
        return userservice.login(user);
    }



    /*class LoginVm
    {
        private String login;
        private String password;
        private boolean rememberMe;
    }*/


}
