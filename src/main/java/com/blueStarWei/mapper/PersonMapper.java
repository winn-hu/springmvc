package com.blueStarWei.mapper;

import com.blueStarWei.bean.Person;

import java.util.Map;

public interface PersonMapper {

    int addPerson(Person person);

    String getName(int id);

    Person getPerson(Map<String,Object> params);
}
