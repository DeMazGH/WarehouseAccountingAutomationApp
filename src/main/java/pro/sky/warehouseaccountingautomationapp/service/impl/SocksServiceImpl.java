package pro.sky.warehouseaccountingautomationapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sky.warehouseaccountingautomationapp.dto.SocksDto;
import pro.sky.warehouseaccountingautomationapp.mapper.SocksMapper;
import pro.sky.warehouseaccountingautomationapp.model.Socks;
import pro.sky.warehouseaccountingautomationapp.repository.SocksRepository;
import pro.sky.warehouseaccountingautomationapp.service.SocksService;

import java.util.List;

import static pro.sky.warehouseaccountingautomationapp.constant.Constant.*;

@Service
@RequiredArgsConstructor
public class SocksServiceImpl implements SocksService {

    private final SocksRepository socksRepository;
    private final SocksMapper socksMapper;


    @Override
    public void registerArrivalOfSocks(SocksDto socksDto) {
        Socks socksInStock = socksRepository.findSocksByColorIgnoreCaseAndCottonPart(socksDto.getColor(), socksDto.getCottonPart());

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
        Socks socksInStock = socksRepository.findSocksByColorIgnoreCaseAndCottonPart(socksDto.getColor(), socksDto.getCottonPart());
        socksInStock.setQuantity(socksInStock.getQuantity() - socksDto.getQuantity());
        socksRepository.save(socksInStock);
    }

    @Override
    public Long getTotalNumberOfSocks(String color, String operation, Integer cottonPart) {
        if (operation.equalsIgnoreCase(MORE_THAN)) {
            List<Socks> socks = socksRepository.findSocksByColorAndCottonPartGreaterThan(color, cottonPart);
            return socks == null ? 0 : socks.stream()
                    .mapToLong(Socks::getQuantity)
                    .sum();
        } else if (operation.equalsIgnoreCase(LESS_THAN)) {
            List<Socks> socks = socksRepository.findSocksByColorAndCottonPartLessThan(color, cottonPart);
            return socks == null ? 0 : socks.stream()
                    .mapToLong(Socks::getQuantity)
                    .sum();
        } else {
            Socks socks = socksRepository.findSocksByColorIgnoreCaseAndCottonPart(color, cottonPart);
            return socks == null ? 0 : socks.getQuantity();
        }
    }
}
