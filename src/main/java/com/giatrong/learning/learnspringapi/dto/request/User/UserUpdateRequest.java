package com.giatrong.learning.learnspringapi.dto.request.User;

import com.giatrong.learning.learnspringapi.enums.RoleEnum;
import com.giatrong.learning.learnspringapi.enums.SwaggerDefaultValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(description = SwaggerDefaultValue.USER_UPDATE_DESC)
public class UserUpdateRequest {
    
    @Size(min = 2, max = 100, message = SwaggerDefaultValue.FULL_NAME_SIZE)
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = SwaggerDefaultValue.FULL_NAME_PATTERN_OPTIONAL)
    @Schema(description = SwaggerDefaultValue.FULL_NAME_DESC, example = SwaggerDefaultValue.FULL_NAME_UPDATED_EXAMPLE)
    private String fullName;
    
    @Email(message = SwaggerDefaultValue.EMAIL_VALID)
    @Size(max = 100, message = SwaggerDefaultValue.EMAIL_SIZE)
    @Schema(description = SwaggerDefaultValue.EMAIL_DESC, example = SwaggerDefaultValue.EMAIL_UPDATED_EXAMPLE)
    private String email;
    
    @Schema(description = SwaggerDefaultValue.ROLE_DESC, example = SwaggerDefaultValue.ROLE_EXAMPLE)
    private RoleEnum roleEnum;
}
