package pro.sky.warehouseaccountingautomationapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sky.warehouseaccountingautomationapp.dto.SocksDto;
import pro.sky.warehouseaccountingautomationapp.exception.SocksReleaseDataIsNotConsistentException;
import pro.sky.warehouseaccountingautomationapp.mapper.SocksMapper;
import pro.sky.warehouseaccountingautomationapp.model.Socks;
import pro.sky.warehouseaccountingautomationapp.repository.SocksRepository;
import pro.sky.warehouseaccountingautomationapp.service.SocksDataValidator;
import pro.sky.warehouseaccountingautomationapp.service.SocksService;

@Service
@RequiredArgsConstructor
public class SocksServiceImpl implements SocksService {

    private final SocksRepository socksRepository;
    private final SocksMapper socksMapper;
    private final SocksDataValidator socksDataValidator;


    @Override
    public void registerArrivalOfSocks(SocksDto socksDto) {
        Socks socksInStock = socksRepository.findSocksByColorAndCottonPart(socksDto.getColor(), socksDto.getCottonPart());

        if (socksInStock == null) {
            Socks newBatchOfSocks = socksMapper.socksDtoToSocks(socksDto);
            socksRepository.save(newBatchOfSocks);
        } else {
            socksInStock.setQuantity(socksInStock.getQuantity() + socksDto.getQuantity());
            socksRepository.save(socksInStock);
        }
    }

    @Override
    public void registerReleaseOfSocks(SocksDto socksDto) {
        if (socksDataValidator.socksReleaseDataIsConsistent(socksDto)) {
            Socks socksInStock = socksRepository.findSocksByColorAndCottonPart(socksDto.getColor(), socksDto.getCottonPart());
            socksInStock.setQuantity(socksInStock.getQuantity() - socksDto.getQuantity());
            socksRepository.save(socksInStock);
        } else {
            throw new SocksReleaseDataIsNotConsistentException();
        }
    }
}
