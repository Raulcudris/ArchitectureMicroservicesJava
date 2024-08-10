package com.shoppingmakiia.PaymentServiceApplication.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.security.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long usuarioId;
    private BigDecimal monto;
    private String metodoPago;
    private Timestamp fecha;
}
