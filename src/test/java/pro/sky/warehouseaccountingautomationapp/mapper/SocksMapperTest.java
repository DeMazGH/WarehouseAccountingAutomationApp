package pro.sky.warehouseaccountingautomationapp.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import pro.sky.warehouseaccountingautomationapp.model.Socks;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pro.sky.warehouseaccountingautomationapp.ConstantTest.*;

@SpringBootTest
class SocksMapperTest {

    @SpyBean
    private SocksMapper socksMapper;

    @Test
    void socksDtoToSocksTest() {
        Socks actual = socksMapper.socksDtoToSocks(SOCKS_DTO_RED_10CP_20QUA);

        assertThat(actual).isNotNull();
        assertThat(actual.getColor()).isEqualTo(SOCKS_DTO_COLOR_RED);
        assertThat(actual.getCottonPart()).isEqualTo(SOCKS_DTO_COTTON_PART_10);
        assertThat(actual.getQuantity()).isEqualTo(SOCKS_DTO_QUANTITY_20);
    }
}