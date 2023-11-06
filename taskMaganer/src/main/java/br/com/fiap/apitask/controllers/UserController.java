package br.com.fiap.apitask.controllers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import br.com.fiap.apitask.dto.AuthRequest;
import br.com.fiap.apitask.dto.UserCreatedDto;
import br.com.fiap.apitask.dto.UserInfoDto;
import br.com.fiap.apitask.models.UserData;
import br.com.fiap.apitask.services.UpdatedJwtService;
import br.com.fiap.apitask.services.UpdatedUserInfoService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class UserController {

    @Autowired
    private UpdatedUserInfoService userInfoService;
    @Autowired
    private UpdatedJwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public String authenticateAndGetToken (@RequestBody AuthRequest authRequest) {
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password()));
            if (authentication.isAuthenticated()) {
                return jwtService.generateToken(authRequest.username());
            }
        }catch (Exception e){
            throw new UsernameNotFoundException("Error: " + e.getMessage());
        }
        return null;
    }

    @GetMapping
    @RequestMapping("/users")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Page<UserCreatedDto>> listAllUsers (Pageable pageable) {
        return ResponseEntity.ok(userInfoService.listAllUsers(pageable));
    }

    @PostMapping("/register")
    public ResponseEntity signUp (@RequestBody UserInfoDto data, UriComponentsBuilder uriBuilder) {
        UserData user = new UserData(data);
        userInfoService.addUser(user);

        var uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserCreatedDto(user));
    }


}
