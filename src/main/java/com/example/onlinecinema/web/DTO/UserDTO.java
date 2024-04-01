package com.example.onlinecinema.web.DTO;

import com.example.onlinecinema.web.validation.OnCreate;
import com.example.onlinecinema.web.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


@Data
@AllArgsConstructor
@Schema($schema = "User DTO")
public class UserDTO {

    @Schema(description = "id", example = "1")
    @NotNull(message = "Id must be not null", groups = {OnUpdate.class})
    private Long id;

    @Schema(description = "Username", example = "kirichka27@gmail.com")
    @NotNull(message = "Username must be not null", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "Username length must be smaller that 255 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String username;

    @Schema(description = "User password", example = "$2a$10$H0YUJXfiPTmjvQ8hLawbPu87p3rJzTyk6oAkU6Ek9kOK4A9Q356vG")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password must be not null",  groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "Password length must be smaller that 255 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String password;

}
