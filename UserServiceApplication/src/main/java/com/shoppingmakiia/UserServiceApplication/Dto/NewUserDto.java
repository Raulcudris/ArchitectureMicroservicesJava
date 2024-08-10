package com.shoppingmakiia.UserServiceApplication.Dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class NewUserDto {
    private String userName;
    private String password;
    private String email;
    private String role;
}
