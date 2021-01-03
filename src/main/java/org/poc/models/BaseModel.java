package org.poc.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@MappedSuperclass
public class BaseModel extends PanacheEntityBase {
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
