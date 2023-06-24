package pro.sky.warehouseaccountingautomationapp.service;

import pro.sky.warehouseaccountingautomationapp.dto.SocksDto;

public interface SocksService {
    void registerArrivalOfSocks(SocksDto socksDto);

    void registerReleaseOfSocks(SocksDto socksDto);

    Long getTotalNumberOfSocks(String color, String operation, Integer cottonPart);
}
