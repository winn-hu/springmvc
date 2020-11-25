package com.blueStarWei.controller;

import com.blueStarWei.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWorld {

    @Autowired
    private PersonMapper person;

    @RequestMapping("/id/{id}")
    public String say(@PathVariable int id){
        return person.getName(id);
    }
}
