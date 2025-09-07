package com.giatrong.learning.learnspringapi.dto.dtos.Role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Role data transfer object")
public class RoleDto {
    
    @Schema(description = "Role ID", example = "1")
    private Long id;
    
    @Schema(description = "Role code", example = "ADMIN")
    private String code;
    
    @Schema(description = "Role name", example = "Administrator")
    private String name;
    
    @Schema(description = "Role description", example = "System administrator role")
    private String description;
}
