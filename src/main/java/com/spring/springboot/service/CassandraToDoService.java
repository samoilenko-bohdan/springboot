package com.spring.springboot.service;

import com.spring.springboot.dao.CassandraToDoDAO;
import com.spring.springboot.entity.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CassandraToDoService implements ToDoService {

    @Autowired
    CassandraToDoDAO cassandraToDoDAO;

    @Override
    public ToDo getById(UUID id) {
        return cassandraToDoDAO.findToDoById(id);
    }

    @Override
    public ToDo create(ToDo entity) {
        entity.setId(UUID.randomUUID());
        return cassandraToDoDAO.save(entity);
    }

    @Override
    public ToDo update(ToDo entity) {
        if (!cassandraToDoDAO.existsById(entity.getId())) {
            throw new IllegalArgumentException("todo does not exist with id "  + entity.getId());
        }
            return cassandraToDoDAO.save(entity);
    }

    @Override
    public List<ToDo> getAll() {
        Iterable<ToDo> iterableToDo = cassandraToDoDAO.findAll();
        ArrayList<ToDo> listOfToDo = new ArrayList<>();
        for(ToDo toDo : iterableToDo) {
            listOfToDo.add(toDo);
        }
        return listOfToDo;
    }

    @Override
    public void removeById(UUID id) {
        if (!cassandraToDoDAO.existsById(id)) {
            throw new IllegalArgumentException("todo does not exist with id "  + id);
        }
        cassandraToDoDAO.deleteById(id);
    }
}
