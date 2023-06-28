package pro.sky.warehouseaccountingautomationapp.service;

import pro.sky.warehouseaccountingautomationapp.dto.SocksDto;

public interface SocksDataValidator {
    boolean socksDtoIsValid(SocksDto socksDto);

    boolean socksReleaseDataIsConsistent(SocksDto socksDto);

    boolean getTotalNumberIsValid(String color, String operation, Integer cottonPart);
}
