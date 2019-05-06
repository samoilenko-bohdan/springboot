package com.spring.springboot.service;

import com.spring.springboot.entity.ToDo;

import java.util.List;
import java.util.UUID;

public interface ToDoService {

    ToDo create(ToDo entity);

    ToDo update(ToDo entity);

    ToDo getById(UUID id);

    List<ToDo> getAll();

    void removeById(UUID id);
}
