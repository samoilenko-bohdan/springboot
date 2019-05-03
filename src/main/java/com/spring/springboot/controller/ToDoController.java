package com.spring.springboot.controller;

import com.spring.springboot.dao.CassandraDAO;
import com.spring.springboot.entity.ToDo;
import com.spring.springboot.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/todo/")
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ToDo> createToDo(@RequestBody ToDo todo) {
        return new ResponseEntity<>(toDoService.create(todo), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ToDo> getTodoById(@PathVariable UUID id) {
        ToDo toDo = toDoService.getById(id);
        if (toDo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(toDo, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ToDo> removeToDo(@PathVariable("id") UUID id) {
        ToDo user = toDoService.getById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        toDoService.removeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ToDo> updateToDo(@RequestBody ToDo todo) {
        return new ResponseEntity<>(toDoService.update(todo), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ToDo>> getAllUsers() {
        List<ToDo> toDos = toDoService.getAll();
        if (toDos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(toDos, HttpStatus.OK);
    }
}