package org.poc.DTOs;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.*;

import java.util.Date;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@RegisterForReflection
public class UserDTO {
    private Long id;

    private String name;

    private String email;

    private Date createdAt;

    private Date updatedAt;
}
