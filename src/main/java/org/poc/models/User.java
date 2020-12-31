package org.poc.models;

import lombok.Builder;
import lombok.Data;
import java.util.*;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Builder
@Entity
@Table(schema = "public", name = "users")
public class User extends BaseModel {

    @Column(unique = true, length = 100)
    private String name;

    @Column(unique = true, length = 100)
    private String email;
}

