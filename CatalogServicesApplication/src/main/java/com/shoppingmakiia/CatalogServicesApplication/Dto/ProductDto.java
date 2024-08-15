package com.shoppingmakiia.CatalogServicesApplication.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductDto {
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private String categoria;
}
