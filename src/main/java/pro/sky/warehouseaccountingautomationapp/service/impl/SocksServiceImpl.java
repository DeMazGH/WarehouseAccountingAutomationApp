package pro.sky.warehouseaccountingautomationapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sky.warehouseaccountingautomationapp.dto.SocksDto;
import pro.sky.warehouseaccountingautomationapp.mapper.SocksMapper;
import pro.sky.warehouseaccountingautomationapp.model.Socks;
import pro.sky.warehouseaccountingautomationapp.repository.SocksRepository;
import pro.sky.warehouseaccountingautomationapp.service.SocksService;

import java.util.List;

import static pro.sky.warehouseaccountingautomationapp.constant.Constant.LESS_THAN;
import static pro.sky.warehouseaccountingautomationapp.constant.Constant.MORE_THAN;

@Service
@RequiredArgsConstructor
public class SocksServiceImpl implements SocksService {

    private final SocksRepository socksRepository;
    private final SocksMapper socksMapper;

    /**
     * Метод регистрирует приход носков на склад. Ищет сущность носков в БД по цвету и проценту содержания в них хлопка.
     * Если сущность не найдена создает новую с переданными параметрами и сохраняет ее в БД,
     * если найдена добавляет количество пар носков переданные в параметрах и сохраняет изменения.
     *
     * @param socksDto данные о партии носков.
     */
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

    /**
     * Метод регистрирует отпуск носков со склада. Ищет сущность носков в БД по цвету и проценту содержания в них хлопка.
     * Уменьшает количество пар носков переданные в параметрах и сохраняет изменения.
     *
     * @param socksDto данные о партии носков.
     */
    @Override
    public void registerReleaseOfSocks(SocksDto socksDto) {
        Socks socksInStock = socksRepository.findSocksByColorIgnoreCaseAndCottonPart(socksDto.getColor(), socksDto.getCottonPart());
        socksInStock.setQuantity(socksInStock.getQuantity() - socksDto.getQuantity());
        socksRepository.save(socksInStock);
    }

    /**
     * Метод возвращает общее количество носков на складе, соответствующих переданным в параметрах критериям запроса.
     * В зависимости от оператора сравнения производится поиск и подсчет данных из БД.
     *
     * @param color      цвет носков.
     * @param operation  оператор сравнения значения количества хлопка в составе носков.
     * @param cottonPart значение процента хлопка в составе носков из сравнения.
     * @return общее количество носков на складе({@code Long})
     */
    @Override
    public Long getTotalNumberOfSocks(String color, String operation, Integer cottonPart) {
        if (operation.equalsIgnoreCase(MORE_THAN)) {
            List<Socks> socks = socksRepository.findSocksByColorAndCottonPartGreaterThan(color, cottonPart);
            return socks.stream()
                    .mapToLong(Socks::getQuantity)
                    .sum();
        } else if (operation.equalsIgnoreCase(LESS_THAN)) {
            List<Socks> socks = socksRepository.findSocksByColorAndCottonPartLessThan(color, cottonPart);
            return socks.stream()
                    .mapToLong(Socks::getQuantity)
                    .sum();
        } else {
            Socks socks = socksRepository.findSocksByColorIgnoreCaseAndCottonPart(color, cottonPart);
            return socks == null ? 0 : socks.getQuantity();
        }
    }
}
