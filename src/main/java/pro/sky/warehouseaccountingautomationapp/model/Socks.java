package pro.sky.warehouseaccountingautomationapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Socks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Color color;

    private Integer cottonPart;
    private Long quantity;
}
