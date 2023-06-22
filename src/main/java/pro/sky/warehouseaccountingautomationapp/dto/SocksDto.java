package pro.sky.warehouseaccountingautomationapp.dto;

import lombok.Data;
import pro.sky.warehouseaccountingautomationapp.model.Color;

@Data
public class SocksDto {

    private Color color;
    private Integer cottonPart;
    private Long quantity;
}
