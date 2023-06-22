package pro.sky.warehouseaccountingautomationapp.service;

import pro.sky.warehouseaccountingautomationapp.dto.SocksDto;

public interface SocksService {
    void registerArrivalOfSocks(SocksDto socksDto);

    void registerReleaseOfSocks(SocksDto socksDto);
}
