/*package com.kirill.man.web.controller;

import com.kirill.man.web.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/")
public class AdminRestController {

    @GetMapping(value = "faq")
    public ResponseEntity<List<User>> printUsers(){
        List<User> list = new ArrayList<>();
        list.add(new User(1l,"first","last","123",22,"@mail"));
        list.add(new User(1l,"first1","last1","123",22,"@mail"));
        list.add(new User(1l,"first2","last2","123",22,"@mail"));
        return ResponseEntity.ok().body(list);
    }
}*/
