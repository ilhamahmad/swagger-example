package com.qurmaca.swagger.controller;

import com.qurmaca.swagger.entity.User;
import com.qurmaca.swagger.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@Api(value = "User api")
@RestController
@RequestMapping("/api/user")
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init()
    {
        User user=new User();
        user.setName("ilham");
        user.setSurname("ahmadzada");
        user.setAddress("Azerbaijan");
        user.setId(1L);
        userRepository.save(user);
    }

    @PostMapping
    @ApiOperation(value = "save method")
    public ResponseEntity<?> save(@RequestBody @ApiParam(value = "istifadeci") User user)
    {
        try{
            userRepository.save(user);
           return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping
    @ApiOperation(value = "update method")
    public ResponseEntity<?> update(@RequestBody User user)
    {
        try{
            userRepository.save(user);
           return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception ex)
        {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete method")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        User user =userRepository.getById(id);
        try{
            userRepository.delete(user);
            return ResponseEntity.ok("deleted");
        }catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


    }

    @GetMapping("/{id}")
    @ApiOperation(value = "find user by id")
    public ResponseEntity<?> findById(@PathVariable @ApiParam(value = "user id") Long id)
    {
        return ResponseEntity.ok(userRepository.findById(id));
    }

    @GetMapping
    @ApiOperation(value = "find all users")
    public ResponseEntity<?> findAll()
    {
        return ResponseEntity.ok(userRepository.findAll());
    }
}
