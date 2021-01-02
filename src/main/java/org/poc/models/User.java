package org.poc.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Builder
@Entity
@Table(schema = "public", name = "users")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseModel {

    @Column(unique = true, length = 100)
    private String name;

    @Column(unique = true, length = 100)
    private String email;
}

