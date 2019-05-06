package com.spring.springboot.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data
@Table(value = "todo")
public class ToDo {

    @ApiModelProperty(notes = "id of toDo",name="id",required=true)
    @PrimaryKey
    private UUID id;

    @ApiModelProperty(notes = "title of toDo",name="title",required=true)
    private String title;

    @ApiModelProperty(notes = "description of toDo",name="description",required=true)
    private String description;
}
