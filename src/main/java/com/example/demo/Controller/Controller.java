package com.example.demo.Controller;


import com.example.demo.Service.Service;
import com.example.demo.Service.UserDetail;
import com.example.demo.Util.JwtRequest;
import com.example.demo.Util.JwtResponse;
import com.example.demo.Util.Jwtutility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    Service service;

    @Autowired
    Jwtutility jwtutility;

    @Autowired
    AuthenticationManager authenticationManager;


    @GetMapping("/")
    public String Welcome(){
        return "Hello Jason here is a web token";
    }


    @PostMapping(value = "/authenticate")
    public JwtResponse authenticate (@RequestBody JwtRequest jwtRequest){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    jwtRequest.getUsername(),
                    jwtRequest.getPassword()
            ));
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Invalid credentials", e);
        }

        UserDetail username  = (UserDetail) service.loadUserByUsername(jwtRequest.getUsername());

        String token  = jwtutility.generatetoken(username);
        return  new JwtResponse(token);

    }





}
