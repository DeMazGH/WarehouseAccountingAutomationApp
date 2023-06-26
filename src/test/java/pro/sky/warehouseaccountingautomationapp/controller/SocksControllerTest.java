package pro.sky.warehouseaccountingautomationapp.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.warehouseaccountingautomationapp.service.SocksDataValidator;
import pro.sky.warehouseaccountingautomationapp.service.SocksService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pro.sky.warehouseaccountingautomationapp.ConstantTest.*;

@WebMvcTest(controllers = SocksController.class)
class SocksControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SocksService socksService;

    @MockBean
    private SocksDataValidator socksDataValidator;

    @Test
    void registerArrivalOfSocksTest() throws Exception {
        String color = SOCKS_DTO_COLOR_BLANK;
        Integer cottonPart = SOCKS_DTO_COTTON_PART_10;
        Long quantity = SOCKS_DTO_QUANTITY_20;

        JSONObject socksDtoObject = createSocksDtoJson(color, cottonPart, quantity);

        when(socksDataValidator.socksDtoIsValid(SOCKS_DTO_NOT_VALID)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/socks/income")
                        .content(socksDtoObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        color = SOCKS_DTO_COLOR_RED;

        socksDtoObject = createSocksDtoJson(color, cottonPart, quantity);

        when(socksDataValidator.socksDtoIsValid(SOCKS_DTO_RED_10CP_20QUA)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/socks/income")
                        .content(socksDtoObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void registerReleaseOfSocksTest() throws Exception {
        String color = SOCKS_DTO_COLOR_BLANK;
        Integer cottonPart = SOCKS_DTO_COTTON_PART_10;
        Long quantity = SOCKS_DTO_QUANTITY_20;

        JSONObject socksDtoObject = createSocksDtoJson(color, cottonPart, quantity);

        when(socksDataValidator.socksDtoIsValid(SOCKS_DTO_NOT_VALID)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/socks/outcome")
                        .content(socksDtoObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        color = SOCKS_DTO_COLOR_RED;

        socksDtoObject = createSocksDtoJson(color, cottonPart, quantity);

        when(socksDataValidator.socksDtoIsValid(SOCKS_DTO_RED_10CP_20QUA)).thenReturn(true);
        when(socksDataValidator.socksReleaseDataIsConsistent(SOCKS_DTO_RED_10CP_20QUA)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/socks/outcome")
                        .content(socksDtoObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        cottonPart = SOCKS_DTO_COTTON_PART_20;

        socksDtoObject = createSocksDtoJson(color, cottonPart, quantity);

        when(socksDataValidator.socksDtoIsValid(SOCKS_DTO_RED_20CP_20QUA)).thenReturn(true);
        when(socksDataValidator.socksReleaseDataIsConsistent(SOCKS_DTO_RED_20CP_20QUA)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/socks/outcome")
                        .content(socksDtoObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getTotalNumberOfSocksTest() throws Exception {
        String color = SOCKS_DTO_COLOR_BLANK;
        String operation = OPERATION_NOT_VALID;
        Integer cottonPart = SOCKS_DTO_COTTON_PART_10;

        when(socksDataValidator.getTotalNumberIsValid(color, operation, cottonPart)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/socks")
                        .queryParam("color", color)
                        .queryParam("operation", operation)
                        .queryParam("cottonPart", cottonPart.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        color = SOCKS_DTO_COLOR_RED;
        operation = OPERATION_EQUAL;

        when(socksDataValidator.getTotalNumberIsValid(color, operation, cottonPart)).thenReturn(true);
        when(socksService.getTotalNumberOfSocks(color, operation, cottonPart)).thenReturn(SOCKS_QUANTITY_20);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/socks")
                        .queryParam("color", color)
                        .queryParam("operation", operation)
                        .queryParam("cottonPart", cottonPart.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(SOCKS_QUANTITY_20));
    }

    private JSONObject createSocksDtoJson(String color, Integer cottonPart, Long quantity) throws Exception {
        JSONObject socksDtoObject = new JSONObject();
        socksDtoObject.put("color", color);
        socksDtoObject.put("cottonPart", cottonPart);
        socksDtoObject.put("quantity", quantity);
        return socksDtoObject;
    }
}