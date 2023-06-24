package pro.sky.warehouseaccountingautomationapp.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import pro.sky.warehouseaccountingautomationapp.mapper.SocksMapper;
import pro.sky.warehouseaccountingautomationapp.repository.SocksRepository;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static pro.sky.warehouseaccountingautomationapp.ConstantTest.*;

@SpringBootTest
class SocksServiceImplTest {

    @InjectMocks
    private SocksServiceImpl out;

    @Mock
    private SocksRepository socksRepository;

    @Mock
    private SocksMapper socksMapper;

    @Test
    void registerArrivalOfSocksTest() {
        when(socksRepository.findSocksByColorIgnoreCaseAndCottonPart(SOCKS_DTO_COLOR_RED, SOCKS_DTO_COTTON_PART_20))
                .thenReturn(SOCKS_1_RED_20CP_40QUA);
        when(socksRepository.save(SOCKS_1_RED_20CP_80QUA)).thenReturn(SOCKS_1_RED_20CP_80QUA);

        out.registerArrivalOfSocks(SOCKS_DTO_RED_20CP_40QUA);

        verify(socksRepository, times(1))
                .findSocksByColorIgnoreCaseAndCottonPart(SOCKS_DTO_COLOR_RED, SOCKS_DTO_COTTON_PART_20);
        verify(socksRepository, times(1)).save(SOCKS_1_RED_20CP_80QUA);
    }

    @Test
    void registerArrivalOfSocksTestWithNull() {
        when(socksRepository.findSocksByColorIgnoreCaseAndCottonPart(SOCKS_DTO_COLOR_RED, SOCKS_DTO_COTTON_PART_10))
                .thenReturn(null);
        when(socksMapper.socksDtoToSocks(SOCKS_DTO_RED_10CP_20QUA)).thenReturn(SOCKS_WITHOUT_ID_RED_10_20);
        when(socksRepository.save(SOCKS_WITHOUT_ID_RED_10_20)).thenReturn(SOCKS_1_RED_10CP_20QUA);

        out.registerArrivalOfSocks(SOCKS_DTO_RED_10CP_20QUA);

        verify(socksRepository, times(1))
                .findSocksByColorIgnoreCaseAndCottonPart(SOCKS_DTO_COLOR_RED, SOCKS_DTO_COTTON_PART_10);
        verify(socksMapper, only()).socksDtoToSocks(SOCKS_DTO_RED_10CP_20QUA);
        verify(socksRepository, times(1)).save(SOCKS_WITHOUT_ID_RED_10_20);
    }

    @Test
    void registerReleaseOfSocksTest() {
        when(socksRepository.findSocksByColorIgnoreCaseAndCottonPart(SOCKS_DTO_COLOR_RED, SOCKS_DTO_COTTON_PART_10))
                .thenReturn(SOCKS_1_RED_10CP_40QUA);
        when(socksRepository.save(SOCKS_1_RED_10CP_20QUA)).thenReturn(SOCKS_1_RED_10CP_20QUA);

        out.registerReleaseOfSocks(SOCKS_DTO_RED_10CP_20QUA);

        verify(socksRepository, times(1))
                .findSocksByColorIgnoreCaseAndCottonPart(SOCKS_DTO_COLOR_RED, SOCKS_DTO_COTTON_PART_10);
        verify(socksRepository, times(1)).save(SOCKS_1_RED_10CP_20QUA);
    }

    @Test
    void getTotalNumberOfSocksTestMoreThan() {
        when(socksRepository.findSocksByColorAndCottonPartGreaterThan(SOCKS_COLOR_GREEN, SOCKS_COTTON_PART_40))
                .thenReturn(SOCKS_LIST_GREEN_MORE_THAN_40_CP);

        Long actual = out.getTotalNumberOfSocks(SOCKS_COLOR_GREEN, OPERATION_MORE_THAN, SOCKS_COTTON_PART_40);

        assertEquals(SOCKS_QUANTITY_50, actual);
        verify(socksRepository, only()).findSocksByColorAndCottonPartGreaterThan(SOCKS_COLOR_GREEN, SOCKS_COTTON_PART_40);
    }

    @Test
    void getTotalNumberOfSocksTestMoreThanWithEmptyList() {
        when(socksRepository.findSocksByColorAndCottonPartGreaterThan(SOCKS_COLOR_GREEN, SOCKS_COTTON_PART_40))
                .thenReturn(new ArrayList<>());

        Long actual = out.getTotalNumberOfSocks(SOCKS_COLOR_GREEN, OPERATION_MORE_THAN, SOCKS_COTTON_PART_40);

        assertEquals(SOCKS_QUANTITY_0, actual);
        verify(socksRepository, only()).findSocksByColorAndCottonPartGreaterThan(SOCKS_COLOR_GREEN, SOCKS_COTTON_PART_40);
    }

    @Test
    void getTotalNumberOfSocksTestLessThan() {
        when(socksRepository.findSocksByColorAndCottonPartLessThan(SOCKS_COLOR_GREEN, SOCKS_COTTON_PART_50))
                .thenReturn(SOCKS_LIST_GREEN_LESS_THAN_50_CP);

        Long actual = out.getTotalNumberOfSocks(SOCKS_COLOR_GREEN, OPERATION_LESS_THAN, SOCKS_COTTON_PART_50);

        assertEquals(SOCKS_QUANTITY_70, actual);
        verify(socksRepository, only()).findSocksByColorAndCottonPartLessThan(SOCKS_COLOR_GREEN, SOCKS_COTTON_PART_50);
    }

    @Test
    void getTotalNumberOfSocksTestEqual() {
        when(socksRepository.findSocksByColorIgnoreCaseAndCottonPart(SOCKS_COLOR_BLUE, SOCKS_COTTON_PART_90))
                .thenReturn(SOCKS_3_BLUE_90CP_50QUA);

        Long actual = out.getTotalNumberOfSocks(SOCKS_COLOR_BLUE, OPERATION_EQUAL, SOCKS_COTTON_PART_90);

        assertEquals(SOCKS_QUANTITY_80, actual);
        verify(socksRepository, only()).findSocksByColorIgnoreCaseAndCottonPart(SOCKS_COLOR_BLUE, SOCKS_COTTON_PART_90);
    }

    @Test
    void getTotalNumberOfSocksTestEqualWithNull() {
        when(socksRepository.findSocksByColorIgnoreCaseAndCottonPart(SOCKS_COLOR_RED, SOCKS_COTTON_PART_90))
                .thenReturn(null);

        Long actual = out.getTotalNumberOfSocks(SOCKS_COLOR_RED, OPERATION_EQUAL, SOCKS_COTTON_PART_90);

        assertEquals(SOCKS_QUANTITY_0, actual);
        verify(socksRepository, only()).findSocksByColorIgnoreCaseAndCottonPart(SOCKS_COLOR_RED, SOCKS_COTTON_PART_90);
    }
}