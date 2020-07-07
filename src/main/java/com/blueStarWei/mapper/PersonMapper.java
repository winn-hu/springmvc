package com.blueStarWei.mapper;

import com.blueStarWei.bean.Person;

public interface PersonMapper {

    int addPerson(Person person);

    String getName(int id);

    Person getPersonById(int id);
}
