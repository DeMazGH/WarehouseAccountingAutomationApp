package pro.sky.warehouseaccountingautomationapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sky.warehouseaccountingautomationapp.dto.SocksDto;
import pro.sky.warehouseaccountingautomationapp.repository.SocksRepository;
import pro.sky.warehouseaccountingautomationapp.service.SocksService;

@Service
@RequiredArgsConstructor
public class SocksServiceImpl implements SocksService {

    private SocksRepository socksRepository;


    @Override
    public void registerArrivalOfSocks(SocksDto socksDto) {

    }
}
