package com.shoppingmakiia.PaymentServiceApplication.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayDto {
    private Long usuarioId;
    private BigDecimal monto;
    private String metodoPago;
    private LocalDate fecha;
}
