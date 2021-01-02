package org.poc.DTOs;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
public class UserDTO {
    private Long id;

    private String name;

    private String email;

    private Date createdAt;

    private Date updatedAt;
}
