package com.blueStarWei.controller;

import com.blueStarWei.bean.Person;
import com.blueStarWei.mapper.PersonMapper;
import com.blueStarWei.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    public String say(@PathVariable int id) {
        return mapper.getName(id);
    }

    @RequestMapping("/person/{name}/{age}")
    public Person getPerson(@PathVariable("name") String name, @PathVariable("age") int age) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        params.put("age", age);
        Person person = this.mapper.getPerson(params);
        return person;
    }

    @RequestMapping("/apiPost")
    public Map<String, Object> apiPost(@RequestBody Map<String, Object> params) {
        Person person = this.mapper.getPerson(params);
        Map<String, Object> result = JsonUtil.json2map(person.toString());
        return result;
    }

}
