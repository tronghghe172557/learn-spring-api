package com.giatrong.learning.learnspringapi.dto.dtos.User;

import com.giatrong.learning.learnspringapi.enums.Role;
import com.giatrong.learning.learnspringapi.enums.SwaggerDefaultValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = SwaggerDefaultValue.USER_DTO_DESC)
public class UserDto {
    
    @Schema(description = SwaggerDefaultValue.USER_ID_DESC, example = SwaggerDefaultValue.USER_ID_EXAMPLE)
    private Long id;
    
    @Schema(description = SwaggerDefaultValue.USERNAME_UNIQUE_DESC, example = SwaggerDefaultValue.USERNAME_EXAMPLE)
    private String username;
    
    @Schema(description = SwaggerDefaultValue.FULL_NAME_DESC, example = SwaggerDefaultValue.FULL_NAME_EXAMPLE)
    private String fullName;
    
    @Schema(description = SwaggerDefaultValue.EMAIL_DESC, example = SwaggerDefaultValue.EMAIL_EXAMPLE)
    private String email;
    
    @Schema(description = SwaggerDefaultValue.ROLE_DESC, example = SwaggerDefaultValue.ROLE_EXAMPLE)
    private Role role;
}
