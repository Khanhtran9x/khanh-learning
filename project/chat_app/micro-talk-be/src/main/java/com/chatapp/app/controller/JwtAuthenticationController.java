package com.chatapp.app.controller;

import com.chatapp.app.config.JwtTokenUtil;
import com.chatapp.app.model.JwtRequest;
import com.chatapp.app.model.JwtResponse;
import com.chatapp.app.model.User;
import com.chatapp.app.model.UserRole;
import com.chatapp.app.repository.IUserRepository;
import com.chatapp.app.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/*
Expose a POST API /authenticate using the JwtAuthenticationController. The POST API gets username and password in the
body- Using Spring Authentication Manager we authenticate the username and password.If the credentials are valid,
a JWT token is created using the JWTTokenUtil and provided to the client.
 */
@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private IUserRepository userRepository;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(user));
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        User user = userRepository.findUser(userDetails.getUsername());
        Set<UserRole> userRoles = user.getUserRoles();
        List<String> userRoleList = new ArrayList<>();
        for (UserRole userRole: userRoles) {
            userRoleList.add(userRole.getRole().getRoleName());
        }

        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setToken(token);
        jwtResponse.setUser(user);
        jwtResponse.setRoles(userRoleList);

        return ResponseEntity.ok(jwtResponse);
    }


    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}