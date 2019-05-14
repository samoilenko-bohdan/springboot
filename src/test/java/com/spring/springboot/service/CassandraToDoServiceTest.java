package com.spring.springboot.service;

import com.spring.springboot.entity.ToDo;
import org.cassandraunit.spring.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestExecutionListeners(listeners = {
        CassandraUnitDependencyInjectionTestExecutionListener.class,
        CassandraUnitTestExecutionListener.class,
        ServletTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class
})
@EmbeddedCassandra(timeout = 60000)
@CassandraDataSet(value = {"script.cql"}, keyspace = "weather")
@TestPropertySource({"classpath:application.properties"})
public class CassandraToDoServiceTest {
    @Autowired
    CassandraToDoService cassandraToDoService;
    @Before

    @Test
    public void create() {
        ToDo toDo = new ToDo();
        cassandraToDoService.create(toDo);
        List<ToDo> todos =  cassandraToDoService.getAll();
        assertNotNull(cassandraToDoService.getById(toDo.getId()));
    }
}