package pro.sky.warehouseaccountingautomationapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.warehouseaccountingautomationapp.dto.SocksDto;
import pro.sky.warehouseaccountingautomationapp.service.SocksDataValidator;
import pro.sky.warehouseaccountingautomationapp.service.SocksService;

@RestController
@RequestMapping("/api/socks")
@RequiredArgsConstructor
public class SocksController {

    private final SocksService socksService;
    private final SocksDataValidator socksDataValidator;

    @Operation(
            summary = "Метод регистрирует приход носков на склад",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные о партии носков, для регистрации прибытия на склад. " +
                            "Все поля обязательны для заполнения. Описание аттрибутов: " +
                            "color — цвет носков, строка; " +
                            "cottonPart — процентное содержание хлопка в составе носков, целое число от 0 до 100; " +
                            "quantity — количество пар носков, целое число больше 0.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SocksDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Удалось добавить приход"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Параметры запроса отсутствуют или имеют некорректный формат"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Произошла ошибка на сервере"
                    )
            }
    )
    @PostMapping("/income")
    public ResponseEntity<?> registerArrivalOfSocks(@RequestBody SocksDto socksDto) {
        if (socksDataValidator.socksDtoIsValid(socksDto)) {
            socksService.registerArrivalOfSocks(socksDto);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(
            summary = "Метод регистрирует отпуск носков со склада",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные о партии носков, для регистрации отпуска со склада. " +
                            "Все поля обязательны для заполнения. Описание аттрибутов: " +
                            "color — цвет носков, строка; " +
                            "cottonPart — процентное содержание хлопка в составе носков, целое число от 0 до 100; " +
                            "quantity — количество пар носков, целое число больше 0, должно быть меньше или равно " +
                            "количеству пар носков оставшихся на складе.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SocksDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Удалось добавить расход"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Параметры запроса отсутствуют или имеют некорректный формат"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Произошла ошибка на сервере"
                    )
            }
    )
    @PostMapping("/outcome")
    public ResponseEntity<?> registerReleaseOfSocks(@RequestBody SocksDto socksDto) {
        if (socksDataValidator.socksDtoIsValid(socksDto) && socksDataValidator.socksReleaseDataIsConsistent(socksDto)) {
            socksService.registerReleaseOfSocks(socksDto);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(
            summary = "Возвращает общее количество носков на складе, " +
                    "соответствующих переданным в параметрах критериям запроса",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "запрос выполнен, " +
                                    "результат в теле ответа в виде строкового представления целого числа"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Параметры запроса отсутствуют или имеют некорректный формат"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Произошла ошибка на сервере"
                    )
            }
    )
    @GetMapping
    public ResponseEntity<Long> getTotalNumberOfSocks(
            @Parameter(description = "Цвет носков, строка. Без учета регистра.")
            @RequestParam String color,
            @Parameter(description = "Оператор сравнения значения количества хлопка в составе носков, " +
                    "одно значение из: moreThan, lessThan, equal. Без учета регистра.")
            @RequestParam String operation,
            @Parameter(description = "Значение процента хлопка в составе носков из сравнения. Целое число от 0 до 100")
            @RequestParam Integer cottonPart) {
        if (socksDataValidator.getTotalNumberIsValid(color, operation, cottonPart)) {
            return ResponseEntity.ok(socksService.getTotalNumberOfSocks(color, operation, cottonPart));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
