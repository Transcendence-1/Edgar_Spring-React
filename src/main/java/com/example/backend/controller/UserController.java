package com.example.backend.controller;

import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8080"})
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserRepository UserRepository;

    @GetMapping
    public List<User> getAllUsers(){
        return UserRepository.findAll();
    }

    // build create User REST API
    @PostMapping
    public User createUser(@RequestBody User User) {
        return UserRepository.save(User);
    }

    // build get User by id REST API
    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable  long id) throws Exception {
        User User = UserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id:" + id));
        Crypto crypto = new Crypto();
        // Retrieve encrypted password from the User object
        String encryptedPassword = User.getPasswordHash();

        // Decrypt the password using the encryption key and algorithm
        String decryptedPassword = crypto.decrypt(encryptedPassword);

        // Set the decrypted password back into the User object
        User.setPasswordHash(decryptedPassword);
        return ResponseEntity.ok(User);
    }

    // build update User REST API
    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id,@RequestBody User UserDetails) throws Exception {
        User updateUser = UserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + id));

        updateUser.setUsername(UserDetails.getUsername());
        updateUser.setEmail(UserDetails.getEmail());
        updateUser.setPassword(UserDetails.getPasswordHash());

        UserRepository.save(updateUser);

        return ResponseEntity.ok(updateUser);
    }

    // build delete User REST API
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable long id){

        User User = UserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + id));

        UserRepository.delete(User);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
//
//    @GetMapping("/my-endpoint")
//    @CrossOrigin(origins = "http://localhost:3000")
//    public String myEndpoint() {
//        return "Hello World!";
//    }
}
