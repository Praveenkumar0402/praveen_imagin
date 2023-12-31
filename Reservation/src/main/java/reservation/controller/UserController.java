package reservation.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import reservation.entity.User;
import reservation.exceptions.UserNotFoundException;
import reservation.model.JwtRequest;
import reservation.model.JwtResponse;
import reservation.services.JwtService;
import reservation.services.UserService;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;
    @Autowired
    JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        this.doAuthenticate(request.getEmail(), request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.jwtService.generateToken(userDetails);
        JwtResponse response = JwtResponse.builder()
                .accessToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    private void doAuthenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password!!");
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

    @PostMapping("/create")
    public User cretaeuser(@RequestBody @Valid User user) {
        return userService.create(user);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAll() throws UserNotFoundException {
        List<User> userDto = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> update(@PathVariable("id") int id, @RequestBody @Valid User userDto) throws UserNotFoundException {
        User userDto1 = userService.updateUser(id, userDto);
        return ResponseEntity.status(HttpStatus.OK).body(userDto1);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> remove(@PathVariable("id") int id) throws UserNotFoundException {
        User userDto = userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }


    @GetMapping("/generate")
    public void generateExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        String headerKey = "Content-Deposition";
        String headerValue = "attachment;filename=user.xlsx";
        response.setHeader(headerKey, headerValue);
        userService.generateExcel(response);
    }

}
