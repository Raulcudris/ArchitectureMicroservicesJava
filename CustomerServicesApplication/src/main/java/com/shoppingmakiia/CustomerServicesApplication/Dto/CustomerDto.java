package com.shoppingmakiia.CustomerServicesApplication.Dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerDto {
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String email;
    private String Telefono;
}
