package com.spring.springboot.entity;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data
@Table(value = "todo")
public class ToDo {
    @PrimaryKey
    private UUID id;
    private String title;
    private String description;
}
