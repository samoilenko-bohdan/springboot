package com.spring.springboot.service;

import com.spring.springboot.dao.CassandraDAO;
import com.spring.springboot.entity.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CassandraToDoService implements ToDoService {

    @Autowired
    CassandraDAO cassandraDAO;

    @Override
    public ToDo getById(UUID id) {
        return cassandraDAO.findToDoById(id);
    }

    @Override
    public ToDo create(ToDo entity) {
        entity.setId(UUID.randomUUID());
        return cassandraDAO.save(entity);
    }

    @Override
    public ToDo update(ToDo entity) {
        if (!cassandraDAO.existsById(entity.getId())) {
            throw new IllegalArgumentException("todo does not exist with id "  + entity.getId());
        }
            return cassandraDAO.save(entity);
    }

    @Override
    public ToDo getByTitle(String title) {
        return cassandraDAO.findToDoByTitle(title);
    }

    @Override
    public List<ToDo> getAll() {
        Iterable<ToDo> iterableToDo = cassandraDAO.findAll();
        ArrayList<ToDo> listOfToDo = new ArrayList<>();
        for(ToDo toDo : iterableToDo) {
            listOfToDo.add(toDo);
        }
        return listOfToDo;
    }

    @Override
    public void removeById(UUID id) {
        if (!cassandraDAO.existsById(id)) {
            throw new IllegalArgumentException("todo does not exist with id "  + id);
        }
        cassandraDAO.deleteById(id);
    }
}
