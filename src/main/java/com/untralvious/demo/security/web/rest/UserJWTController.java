package com.untralvious.demo.security.web.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.untralvious.demo.security.domain.SysUser;
import com.untralvious.demo.security.security.SecurityUtils;
import com.untralvious.demo.security.security.jwt.JWTFilter;
import com.untralvious.demo.security.security.jwt.TokenProvider;
import com.untralvious.demo.security.service.RedisService;
import com.untralvious.demo.security.service.UserService;
import com.untralvious.demo.security.web.rest.vm.LoginVM;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping("/api")
public class UserJWTController {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private final TokenProvider tokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final UserService userService;

    private final RedisService redisService;

    public UserJWTController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, UserService userService, RedisService redisService) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userService = userService;
        this.redisService = redisService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JWTToken> authorize(@Valid @RequestBody LoginVM loginVM) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            loginVM.getUsername(),
            loginVM.getPassword()
        );

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication, loginVM.isRememberMe());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
    }

    @RequestMapping("/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request){
        String token = request.getHeader(AUTHORIZATION_HEADER);
        String userName = SecurityUtils.getCurrentUserLogin().get();
        if(token.isEmpty()) {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
//        Authentication authentication = tokenProvider.getAuthentication(token);
        SysUser sysUser = userService.getUserByLogin(userName);
        if(sysUser!=null) {
            redisService.delKey(sysUser.getLogin());
            SecurityContextHolder.clearContext();
            return new ResponseEntity<Object>(HttpStatus.OK);
        }else {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Object to return as body in JWT Authentication.
     */
    static class JWTToken {

        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }
}
