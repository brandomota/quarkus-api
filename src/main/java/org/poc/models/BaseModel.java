package org.poc.models;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date createdAt;

    @Column
    private Date updatedAt;

    public BaseModel() {
        this.createdAt = new Date();
    }
}
