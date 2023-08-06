package project.socialNetwork.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.socialNetwork.auth.request.LoginRequest;
import project.socialNetwork.auth.request.SignupRequest;
import project.socialNetwork.auth.response.JWTTokenResponse;
import project.socialNetwork.auth.response.MessageResponse;
import project.socialNetwork.config.security.JwtTokenUtils;
import project.socialNetwork.service.UserService;
import project.socialNetwork.service.impl.UserDetailService;
import project.socialNetwork.validations.ResponseErrorValidation;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/auth/")
@CrossOrigin
public class AuthController {
    private final JwtTokenUtils jwtTokenUtils;
    private final UserDetailService userDetailService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final ResponseErrorValidation responseErrorValidation;


    @Autowired
    public AuthController(JwtTokenUtils jwtTokenUtils, UserDetailService userDetailService, UserService userService, AuthenticationManager authenticationManager, ResponseErrorValidation responseErrorValidation) {
        this.jwtTokenUtils = jwtTokenUtils;
        this.userDetailService = userDetailService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.responseErrorValidation = responseErrorValidation;
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest,
                                                   BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) {
            return errors;
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        UserDetails userDetails = userDetailService.loadUserByUsername(loginRequest.getEmail());
        String token = jwtTokenUtils.generateToken(userDetails);
        List<String> role = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JWTTokenResponse(token, role));
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody SignupRequest signupRequest,
                                               BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) {
            return errors;
        }
        userService.createUser(signupRequest);
        return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    }
}
