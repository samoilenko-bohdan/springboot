package com.spring.springboot.controller;

import com.spring.springboot.entity.ToDo;
import com.spring.springboot.service.ToDoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Api(value = "ToDoController", description = "REST ToDo API")
@RestController
@RequestMapping("/todo/")
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    @ApiOperation(value = "Create new toDo", response = ToDo.class, tags = "createToDo")
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ToDo> createToDo(@RequestBody ToDo todo) {
        return new ResponseEntity<>(toDoService.create(todo), HttpStatus.OK);
    }

    @ApiOperation(value = "Get toDo by id", response = ToDo.class, tags = "getToDo")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ToDo> getTodoById(@PathVariable UUID id) {
        ToDo toDo = toDoService.getById(id);
        if (toDo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(toDo, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete toDo by id", response = ToDo.class, tags = "removeToDo")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ToDo> removeToDo(@PathVariable("id") UUID id) {
        ToDo user = toDoService.getById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        toDoService.removeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "update toDo", response = ToDo.class, tags = "updateToDo")
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ToDo> updateToDo(@RequestBody ToDo todo) {
        return new ResponseEntity<>(toDoService.update(todo), HttpStatus.OK);
    }

    @ApiOperation(value = "get all toDos", response = List.class, tags = "getAllUsers")
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ToDo>> getAllUsers() {
        List<ToDo> toDos = toDoService.getAll();
        if (toDos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(toDos, HttpStatus.OK);
    }
}