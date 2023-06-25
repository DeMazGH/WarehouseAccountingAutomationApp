package pro.sky.warehouseaccountingautomationapp.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.warehouseaccountingautomationapp.dto.SocksDto;
import pro.sky.warehouseaccountingautomationapp.repository.SocksRepository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static pro.sky.warehouseaccountingautomationapp.ConstantTest.*;

@ExtendWith(MockitoExtension.class)
class SocksDataValidatorImplTest {

    @InjectMocks
    private SocksDataValidatorImpl out;

    @Mock
    private SocksRepository socksRepository;

    @Test
    void shouldReturnFalseInMethodSocksDtoIsValid() {
        boolean actual;
        SocksDto socksDto;

        actual = out.socksDtoIsValid(null);
        assertFalse(actual);

        socksDto = new SocksDto();
        socksDto.setColor(null);
        socksDto.setCottonPart(SOCKS_DTO_COTTON_PART_10);
        socksDto.setQuantity(SOCKS_DTO_QUANTITY_10);
        actual = out.socksDtoIsValid(socksDto);
        assertFalse(actual);

        socksDto.setColor(SOCKS_DTO_COLOR_RED);
        socksDto.setCottonPart(null);
        socksDto.setQuantity(SOCKS_DTO_QUANTITY_10);
        actual = out.socksDtoIsValid(socksDto);
        assertFalse(actual);

        socksDto.setColor(SOCKS_DTO_COLOR_RED);
        socksDto.setCottonPart(SOCKS_DTO_COTTON_PART_10);
        socksDto.setQuantity(null);
        actual = out.socksDtoIsValid(socksDto);
        assertFalse(actual);

        socksDto.setColor(SOCKS_DTO_COLOR_BLANK);
        socksDto.setCottonPart(SOCKS_DTO_COTTON_PART_10);
        socksDto.setQuantity(SOCKS_DTO_QUANTITY_10);
        actual = out.socksDtoIsValid(socksDto);
        assertFalse(actual);

        socksDto.setColor(SOCKS_DTO_COLOR_RED);
        socksDto.setCottonPart(SOCKS_DTO_COTTON_PART_MINUS_10);
        socksDto.setQuantity(SOCKS_DTO_QUANTITY_10);
        actual = out.socksDtoIsValid(socksDto);
        assertFalse(actual);

        socksDto.setColor(SOCKS_DTO_COLOR_RED);
        socksDto.setCottonPart(SOCKS_DTO_COTTON_PART_120);
        socksDto.setQuantity(SOCKS_DTO_QUANTITY_10);
        actual = out.socksDtoIsValid(socksDto);
        assertFalse(actual);

        socksDto.setColor(SOCKS_DTO_COLOR_RED);
        socksDto.setCottonPart(SOCKS_DTO_COTTON_PART_10);
        socksDto.setQuantity(SOCKS_DTO_QUANTITY_0);
        actual = out.socksDtoIsValid(socksDto);
        assertFalse(actual);
    }

    @Test
    void shouldReturnTrueInMethodSocksDtoIsValid() {
        SocksDto socksDto = new SocksDto();
        socksDto.setColor(SOCKS_DTO_COLOR_RED);
        socksDto.setCottonPart(SOCKS_DTO_COTTON_PART_10);
        socksDto.setQuantity(SOCKS_DTO_QUANTITY_10);

        boolean actual = out.socksDtoIsValid(socksDto);
        assertTrue(actual);
    }

    @Test
    void shouldReturnFalseInMethodSocksReleaseDataIsConsistentWithNull() {
        when(socksRepository.findSocksByColorIgnoreCaseAndCottonPart(SOCKS_DTO_COLOR_RED, SOCKS_DTO_COTTON_PART_10))
                .thenReturn(null);

        boolean actual = out.socksReleaseDataIsConsistent(SOCKS_DTO_RED_10CP_10QUA);
        assertFalse(actual);
    }

    @Test
    void shouldReturnFalseInMethodSocksReleaseDataIsConsistentWithExcessQuantity() {
        when(socksRepository.findSocksByColorIgnoreCaseAndCottonPart(SOCKS_DTO_COLOR_RED, SOCKS_DTO_COTTON_PART_20))
                .thenReturn(SOCKS_1_RED_20CP_20QUA);

        boolean actual = out.socksReleaseDataIsConsistent(SOCKS_DTO_RED_20CP_40QUA);
        assertFalse(actual);
    }

    @Test
    void shouldReturnTrueInMethodSocksReleaseDataIsConsistent() {
        when(socksRepository.findSocksByColorIgnoreCaseAndCottonPart(SOCKS_DTO_COLOR_RED, SOCKS_DTO_COTTON_PART_30))
                .thenReturn(SOCKS_1_RED_30CP_40QUA);

        boolean actual = out.socksReleaseDataIsConsistent(SOCKS_DTO_RED_30CP_20QUA);
        assertTrue(actual);
    }

    @Test
    void shouldReturnFalseInMethodGetTotalNumberIsValid() {
        String color;
        String operation;
        Integer cottonPart;
        boolean actual;

        operation = OPERATION_EQUAL;
        cottonPart = SOCKS_COTTON_PART_10;
        actual = out.getTotalNumberIsValid(null, operation, cottonPart);
        assertFalse(actual);

        color = SOCKS_COLOR_RED;
        cottonPart = SOCKS_COTTON_PART_10;
        actual = out.getTotalNumberIsValid(color, null, cottonPart);
        assertFalse(actual);

        color = SOCKS_COLOR_RED;
        operation = OPERATION_EQUAL;
        actual = out.getTotalNumberIsValid(color, operation, null);
        assertFalse(actual);

        color = SOCKS_COLOR_BLANK;
        operation = OPERATION_EQUAL;
        cottonPart = SOCKS_COTTON_PART_10;
        actual = out.getTotalNumberIsValid(color, operation, cottonPart);
        assertFalse(actual);

        color = SOCKS_COLOR_RED;
        operation = OPERATION_EQUAL;
        cottonPart = SOCKS_COTTON_PART_MINUS_10;
        actual = out.getTotalNumberIsValid(color, operation, cottonPart);
        assertFalse(actual);

        color = SOCKS_COLOR_RED;
        operation = OPERATION_EQUAL;
        cottonPart = SOCKS_COTTON_PART_110;
        actual = out.getTotalNumberIsValid(color, operation, cottonPart);
        assertFalse(actual);

        color = SOCKS_COLOR_RED;
        operation = OPERATION_NOT_VALID;
        cottonPart = SOCKS_COTTON_PART_10;
        actual = out.getTotalNumberIsValid(color, operation, cottonPart);
        assertFalse(actual);
    }

    @Test
    void shouldReturnTrueInMethodGetTotalNumberIsValid() {
        String color = SOCKS_COLOR_RED;
        Integer cottonPart = SOCKS_COTTON_PART_10;
        String operation;
        boolean actual;

        operation = OPERATION_EQUAL;
        actual = out.getTotalNumberIsValid(color, operation, cottonPart);
        assertTrue(actual);

        operation = OPERATION_MORE_THAN;
        actual = out.getTotalNumberIsValid(color, operation, cottonPart);
        assertTrue(actual);

        operation = OPERATION_LESS_THAN;
        actual = out.getTotalNumberIsValid(color, operation, cottonPart);
        assertTrue(actual);
    }
}