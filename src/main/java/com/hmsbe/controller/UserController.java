package com.hmsbe.controller;

import com.hmsbe.exception.ResourceNotFoundException;
import com.hmsbe.repository.UserRepository;
import com.hmsbe.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/auth")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/")
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable Long id) {

        Optional<User> User = userRepo.findById(id);

        if(User.isEmpty()) {
           throw new ResourceNotFoundException("User not found for given ID " + id);
        }

        return ResponseEntity.ok(User);
    }

    @PostMapping("/authenticate")
    public Map<String, String> authenticate(@RequestBody User userParam)  {

        Map<String, String> response = new HashMap<>();
        response.put("status", "fail");

        try {
            if( userParam.getPassword().isBlank() || userParam.getEmail().isBlank() ) {
                response.put("message", "All fields are mandatory");
                return response;
            }

            List<User> existingUser = userRepo.findByEmail(userParam.getEmail());

            if(existingUser.isEmpty()) {
                response.put("message", "Invalid User");
                return response;
            }

            User user = existingUser.getFirst();

            if(!Objects.equals(user.getPassword(), userParam.getPassword())) {
                response.put("message", "Invalid Credentials");
                return response;
            }

            String accessToken = UUID.randomUUID().toString();
            user.setAccessToken(accessToken);
            userRepo.save(user);

            response.put("status", "success");
            response.put("message", "Welcome " + user.getFirstName());
            response.put("id", user.getId().toString());
            response.put("accessToken", accessToken);
        } catch(Exception e) {
            response.put("message", "Unable to login");
            return response;
        }

        return response;
    }

    @PutMapping
    public Map<String, String> updateUser(@RequestBody User User)  {

        Map<String, String> response = new HashMap<>();
        response.put("status", "fail");

        try {
            if( User.getId().toString().isBlank() || User.getFirstName().isBlank() || User.getLastName().isBlank() || User.getEmail().isBlank() ) {
                response.put("message", "All fields are mandatory");
                return response;
            }

            Optional<User> existingUser = userRepo.findById(User.getId());

            if(existingUser.isEmpty()) {
                response.put("message", "Invalid User");
                return response;
            }

            userRepo.save(User);
            response.put("status", "success");
            response.put("message", "User Updated");
        } catch (Exception e) {
            response.put("message", "Unable to save");
            return response;
        }

        return response;
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteUser(@PathVariable Long id)  {
        Map<String, String> response = new HashMap<>();
        response.put("status", "fail");

        try {
            if( id < 1 ) {
                response.put("message", "Invalid Request");
                return response;
            }

            Optional<User> existingUser = userRepo.findById(id);

            if( existingUser.isEmpty() ) {
                response.put("message", "Invalid User");
                return response;
            }

            userRepo.delete(existingUser.get());
            response.put("status", "success");
            response.put("message", "User Deleted");
        } catch (Exception e) {
            response.put("message", "Unable to delete");
            return response;
        }

        return response;
    }

}
