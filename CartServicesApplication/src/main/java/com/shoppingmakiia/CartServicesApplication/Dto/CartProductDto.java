package com.shoppingmakiia.CartServicesApplication.Dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartProductDto {
    private Long carritoId;
    private Long productoId;
    private Long cantidad;
}
