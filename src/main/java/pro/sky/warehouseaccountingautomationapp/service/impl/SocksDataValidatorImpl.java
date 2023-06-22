package pro.sky.warehouseaccountingautomationapp.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.warehouseaccountingautomationapp.dto.SocksDto;
import pro.sky.warehouseaccountingautomationapp.service.SocksDataValidator;

@Service
public class SocksDataValidatorImpl implements SocksDataValidator {

    @Override
    public boolean socksDtoIsValid(SocksDto socksDto) {
        if (socksDto == null) {
            return false;
        } else if (socksDto.getColor() == null || socksDto.getQuantity() == null || socksDto.getCottonPart() == null) {
            return false;
        } else if (socksDto.getCottonPart() < 0 || socksDto.getCottonPart() > 100) {
            return false;
        } else return socksDto.getQuantity() > 0;
    }
}
