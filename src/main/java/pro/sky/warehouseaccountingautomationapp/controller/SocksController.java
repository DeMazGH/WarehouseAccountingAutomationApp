package pro.sky.warehouseaccountingautomationapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.warehouseaccountingautomationapp.dto.SocksDto;
import pro.sky.warehouseaccountingautomationapp.model.Socks;
import pro.sky.warehouseaccountingautomationapp.service.SocksService;

@RestController
@RequestMapping("/api/socks")
@RequiredArgsConstructor
public class SocksController {

    private SocksService socksService;

    /*POST /api/socks/income
Регистрирует приход носков на склад.

Параметры запроса передаются в теле запроса в виде JSON-объекта со следующими атрибутами:

color — цвет носков, строка (например, black, red, yellow);
cottonPart — процентное содержание хлопка в составе носков, целое число от 0 до 100 (например, 30, 18, 42);
quantity — количество пар носков, целое число больше 0.
     */

    @PostMapping("/income")
    public ResponseEntity<?> registerArrivalOfSocks(@RequestBody SocksDto socksDto) {
        socksService.registerArrivalOfSocks(socksDto);


        return ResponseEntity.ok().build();
    }
}
