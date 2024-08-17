package com.shoppingmakiia.AuthServiceApplication.Dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class NewUserDto {
    private String username;
    private String password;
    private String email;
    private String role;
}
