package pro.sky.warehouseaccountingautomationapp.service;

import pro.sky.warehouseaccountingautomationapp.dto.SocksDto;

public interface SocksDataValidator {
    boolean socksDtoIsValid(SocksDto socksDto);

    boolean socksReleaseDataIsConsistent(SocksDto socksDto);
}
