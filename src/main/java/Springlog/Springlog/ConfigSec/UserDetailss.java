package Springlog.Springlog.ConfigSec;

import Springlog.Springlog.Entity.Role;
import Springlog.Springlog.Entity.User;
import Springlog.Springlog.Repouser.Repouser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserDetailss implements UserDetailsService {

    @Autowired
    // private attribue : private UserRepository userRepository;

    Repouser repouser ;  // Upper case 1st letter class
                            // lowcase case package name
                         // Entity -> domain , repouser -> repository , services -> service , resource : UserResource , config , security


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = (User) repouser.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("Username not found"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),mapRolesToAuthorities(user.getRoles()));
        }


    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
}}


