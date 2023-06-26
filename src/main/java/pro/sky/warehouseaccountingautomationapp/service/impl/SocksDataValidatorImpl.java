package pro.sky.warehouseaccountingautomationapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sky.warehouseaccountingautomationapp.dto.SocksDto;
import pro.sky.warehouseaccountingautomationapp.model.Socks;
import pro.sky.warehouseaccountingautomationapp.repository.SocksRepository;
import pro.sky.warehouseaccountingautomationapp.service.SocksDataValidator;

import static pro.sky.warehouseaccountingautomationapp.constant.Constant.*;

@Service
@RequiredArgsConstructor
public class SocksDataValidatorImpl implements SocksDataValidator {

    private final SocksRepository socksRepository;

    /**
     * Метод проверяет валидность данных о партии носков.
     * @param socksDto данные о партии носков.
     * @return true - если данные валидны, false - если не валидны.
     */
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

    /**
     * Метод проверяет согласованность данных для отпуска партии носков.
     * @param socksDto данные о партии носков.
     * @return true - если данные согласованы, false - если не согласованы.
     */
    @Override
    public boolean socksReleaseDataIsConsistent(SocksDto socksDto) {
        Socks socksInStock = socksRepository.findSocksByColorIgnoreCaseAndCottonPart(socksDto.getColor(), socksDto.getCottonPart());
        if (socksInStock == null) {
            return false;
        } else return socksInStock.getQuantity() >= socksDto.getQuantity();
    }

    /**
     * Метод проверяет валидность параметров переданных в метод {@code getTotalNumberOfSocks}.
     * @param color цвет носков.
     * @param operation оператор сравнения значения количества хлопка в составе носков.
     * @param cottonPart значение процента хлопка в составе носков из сравнения.
     * @return true - если данные валидны, false - если не валидны.
     */
    @Override
    public boolean getTotalNumberIsValid(String color, String operation, Integer cottonPart) {
        if (color == null || operation == null || cottonPart == null) {
            return false;
        } else if (color.isBlank()) {
            return false;
        } else if (cottonPart < 0 || cottonPart > 100) {
            return false;
        } else return operation.equalsIgnoreCase(MORE_THAN)
                    || operation.equalsIgnoreCase(LESS_THAN)
                    || operation.equalsIgnoreCase(EQUAL);
    }
}
