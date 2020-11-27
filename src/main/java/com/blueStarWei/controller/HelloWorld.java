package com.blueStarWei.controller;

import com.blueStarWei.bean.Person;
import com.blueStarWei.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/hello")
public class HelloWorld {

    @Autowired
    private PersonMapper mapper;

    @RequestMapping("/id/{id}")
    public String say(@PathVariable int id){
        return mapper.getName(id);
    }

    @RequestMapping("/person/{name}/{age}")
    public String getPerson(@PathVariable("name") String name, @PathVariable("age") int age){
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("name",name);
        params.put("age", age);
        Person person = this.mapper.getPerson(params);
        return person != null ? person.toString() : "No Result";
    }
}
