package pro.sky.warehouseaccountingautomationapp.controller;

import lombok.RequiredArgsConstructor;
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

    @PostMapping("/income")
    public ResponseEntity<?> registerArrivalOfSocks(@RequestBody SocksDto socksDto) {
        if (socksDataValidator.socksDtoIsValid(socksDto)) {
            socksService.registerArrivalOfSocks(socksDto);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/outcome")
    public ResponseEntity<?> registerReleaseOfSocks(@RequestBody SocksDto socksDto) {
        if (socksDataValidator.socksDtoIsValid(socksDto) && socksDataValidator.socksReleaseDataIsConsistent(socksDto)) {
            socksService.registerReleaseOfSocks(socksDto);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<Long> getTotalNumberOfSocks(
            @RequestParam String color,
            @RequestParam String operation,
            @RequestParam Integer cottonPart) {
        if (socksDataValidator.getTotalNumberIsValid(color, operation, cottonPart)) {
             return ResponseEntity.ok(socksService.getTotalNumberOfSocks(color, operation, cottonPart));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
