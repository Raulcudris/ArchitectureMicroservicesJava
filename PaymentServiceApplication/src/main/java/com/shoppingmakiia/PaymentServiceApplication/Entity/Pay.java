package com.shoppingmakiia.PaymentServiceApplication.Entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.security.Timestamp;

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
