package Springlog.Springlog.Controlleruser;

import Springlog.Springlog.domain.UserDomain;
import Springlog.Springlog.Repouser.Repouser;
import Springlog.Springlog.Repouser.RoleRepository;
import Springlog.Springlog.Services.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/z")
    public String z(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if (authority.getAuthority().equals("ADMIN")) {
                    return "ADMIN";
                }else if (authority.getAuthority().equals("USER")) {
                    authority.getAuthority();
                    return "USER";
                }
                else {
                    return "No auth";
                }
            }


        }

        return "welcome u have the permission to join here ";
    }

    /*class LoginVm
    {
        private String login;
        private String password;
        private boolean rememberMe;
    }*/


}
