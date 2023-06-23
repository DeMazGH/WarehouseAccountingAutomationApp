package pro.sky.warehouseaccountingautomationapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sky.warehouseaccountingautomationapp.dto.SocksDto;
import pro.sky.warehouseaccountingautomationapp.model.Socks;
import pro.sky.warehouseaccountingautomationapp.repository.SocksRepository;
import pro.sky.warehouseaccountingautomationapp.service.SocksDataValidator;

@Service
@RequiredArgsConstructor
public class SocksDataValidatorImpl implements SocksDataValidator {

    private final SocksRepository socksRepository;

    @Override
    public boolean socksDtoIsValid(SocksDto socksDto) {
        if (socksDto == null) {
            return false;
        } else if (socksDto.getColor() == null || socksDto.getQuantity() == null || socksDto.getCottonPart() == null) {
            return false;
        } else if (socksDto.getColor().isBlank()) {
            return false;
        } else if (socksDto.getCottonPart() < 0 || socksDto.getCottonPart() > 100) {
            return false;
        } else return socksDto.getQuantity() > 0;
    }

    @Override
    public boolean socksReleaseDataIsConsistent(SocksDto socksDto) {
        Socks socksInStock = socksRepository.findSocksByColorIgnoreCaseAndCottonPart(socksDto.getColor(), socksDto.getCottonPart());
        if (socksInStock == null) {
            return false;
        } else return socksInStock.getQuantity() >= socksDto.getQuantity();
    }
}
