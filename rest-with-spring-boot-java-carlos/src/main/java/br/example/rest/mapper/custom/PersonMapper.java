package br.example.rest.mapper.custom;

import br.example.rest.data.vo.v2.PersonVo2;
import br.example.rest.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {


    public PersonVo2 convertEntityToVo(Person person){
        PersonVo2 vo = new PersonVo2();
        vo.setId(person.getId());
        vo.setAddress(person.getAddress());
        vo.setGender(person.getGender());
        vo.setBirthDay(new Date());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());

        return vo;
    }
    public Person convertVoToEntity(PersonVo2 person){
        Person entity = new Person();
        entity.setId(person.getId());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());

        return entity;
    }
}
