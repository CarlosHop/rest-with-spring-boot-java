package br.example.rest.services;

import br.example.rest.data.vo.v1.PersonVo;
import br.example.rest.data.vo.v2.PersonVo2;
import br.example.rest.exceptions.ResourceNotFoundException;
import br.example.rest.mapper.ModelMapper;
import br.example.rest.mapper.custom.PersonMapper;
import br.example.rest.model.Person;
import br.example.rest.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;
    @Autowired
    PersonMapper mapper;

    public List<PersonVo> findAll() {

        logger.info("Finding all people!");

        return ModelMapper.parseListObjects(repository.findAll(), PersonVo.class);
    }


    public PersonVo findById(Long id) {

        logger.info("Finding one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        return ModelMapper.parseObject(entity, PersonVo.class);
    }

    public PersonVo create(PersonVo person) {

        logger.info("Creating one person!");
        var entity = ModelMapper.parseObject(person, Person.class);
        var vo = ModelMapper.parseObject(repository.save(entity), PersonVo.class);
        return vo;
    }

    public PersonVo2 createV2(PersonVo2 person) {

        logger.info("Creating one person with V2!");
        var entity = mapper.convertVoToEntity(person);
        var vo = mapper.convertEntityToVo(repository.save(entity));
        return vo;
    }

    public PersonVo update(PersonVo person) {

        logger.info("Updating one person!");

        var entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = ModelMapper.parseObject(repository.save(entity), PersonVo.class);
        return vo;
    }

    public void delete(Long id) {

        logger.info("Deleting one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }
}
