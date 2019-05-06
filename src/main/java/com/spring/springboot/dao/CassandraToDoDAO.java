package com.spring.springboot.dao;

import com.spring.springboot.entity.ToDo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CassandraToDoDAO extends CrudRepository<ToDo, UUID> {

    ToDo findToDoById(UUID id);
}
