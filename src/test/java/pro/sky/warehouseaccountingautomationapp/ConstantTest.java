package pro.sky.warehouseaccountingautomationapp;

import pro.sky.warehouseaccountingautomationapp.dto.SocksDto;
import pro.sky.warehouseaccountingautomationapp.model.Socks;

import java.util.ArrayList;
import java.util.List;

public class ConstantTest {

    public static Long SOCKS_ID_1 = 1L;
    public static Long SOCKS_ID_2 = 2L;
    public static Long SOCKS_ID_3 = 3L;

    public static String SOCKS_COLOR_RED = "red";
    public static String SOCKS_COLOR_GREEN = "green";
    public static String SOCKS_COLOR_BLUE = "blue";

    public static Integer SOCKS_COTTON_PART_10 = 10;
    public static Integer SOCKS_COTTON_PART_20 = 20;
    public static Integer SOCKS_COTTON_PART_40 = 40;
    public static Integer SOCKS_COTTON_PART_45 = 45;
    public static Integer SOCKS_COTTON_PART_50 = 50;
    public static Integer SOCKS_COTTON_PART_90 = 90;

    public static Long SOCKS_QUANTITY_0 = 0L;
    public static Long SOCKS_QUANTITY_20 = 20L;
    public static Long SOCKS_QUANTITY_30 = 30L;
    public static Long SOCKS_QUANTITY_40 = 40L;
    public static Long SOCKS_QUANTITY_50 = 50L;
    public static Long SOCKS_QUANTITY_70 = 70L;
    public static Long SOCKS_QUANTITY_80 = 80L;

    public static String OPERATION_MORE_THAN = "moreThan";
    public static String OPERATION_LESS_THAN = "lessThan";
    public static String OPERATION_EQUAL = "equal";


    public static Socks SOCKS_1_RED_10CP_20QUA = new Socks(SOCKS_ID_1, SOCKS_COLOR_RED, SOCKS_COTTON_PART_10, SOCKS_QUANTITY_20);
    public static Socks SOCKS_1_RED_10CP_40QUA = new Socks(SOCKS_ID_1, SOCKS_COLOR_RED, SOCKS_COTTON_PART_10, SOCKS_QUANTITY_40);
    public static Socks SOCKS_1_RED_20CP_40QUA = new Socks(SOCKS_ID_1, SOCKS_COLOR_RED, SOCKS_COTTON_PART_20, SOCKS_QUANTITY_40);
    public static Socks SOCKS_1_RED_20CP_80QUA = new Socks(SOCKS_ID_1, SOCKS_COLOR_RED, SOCKS_COTTON_PART_20, SOCKS_QUANTITY_80);

    public static Socks SOCKS_2_GREEN_40CP_40QUA = new Socks(SOCKS_ID_2, SOCKS_COLOR_GREEN, SOCKS_COTTON_PART_40, SOCKS_QUANTITY_40);
    public static Socks SOCKS_2_GREEN_45CP_30QUA = new Socks(SOCKS_ID_2, SOCKS_COLOR_GREEN, SOCKS_COTTON_PART_45, SOCKS_QUANTITY_30);
    public static Socks SOCKS_2_GREEN_50CP_20QUA = new Socks(SOCKS_ID_2, SOCKS_COLOR_GREEN, SOCKS_COTTON_PART_50, SOCKS_QUANTITY_20);

    public static Socks SOCKS_3_BLUE_90CP_50QUA = new Socks(SOCKS_ID_3, SOCKS_COLOR_BLUE, SOCKS_COTTON_PART_90, SOCKS_QUANTITY_80);


    public static Socks SOCKS_WITHOUT_ID_RED_10_20 = new Socks();

    static {
        SOCKS_WITHOUT_ID_RED_10_20.setColor(SOCKS_COLOR_RED);
        SOCKS_WITHOUT_ID_RED_10_20.setCottonPart(SOCKS_COTTON_PART_10);
        SOCKS_WITHOUT_ID_RED_10_20.setQuantity(SOCKS_QUANTITY_20);
    }

    public static String SOCKS_DTO_COLOR_RED = "red";

    public static Integer SOCKS_DTO_COTTON_PART_10 = 10;
    public static Integer SOCKS_DTO_COTTON_PART_20 = 20;

    public static Long SOCKS_DTO_QUANTITY_20 = 20L;
    public static Long SOCKS_DTO_QUANTITY_40 = 40L;

    public static SocksDto SOCKS_DTO_RED_10CP_20QUA = new SocksDto(SOCKS_DTO_COLOR_RED, SOCKS_DTO_COTTON_PART_10, SOCKS_DTO_QUANTITY_20);
    public static SocksDto SOCKS_DTO_RED_20CP_40QUA = new SocksDto(SOCKS_DTO_COLOR_RED, SOCKS_DTO_COTTON_PART_20, SOCKS_DTO_QUANTITY_40);

    public static List<Socks> SOCKS_LIST_GREEN_MORE_THAN_40_CP = new ArrayList<>();

    static {
        SOCKS_LIST_GREEN_MORE_THAN_40_CP.add(SOCKS_2_GREEN_45CP_30QUA);
        SOCKS_LIST_GREEN_MORE_THAN_40_CP.add(SOCKS_2_GREEN_50CP_20QUA);
    }

    public static List<Socks> SOCKS_LIST_GREEN_LESS_THAN_50_CP = new ArrayList<>();

    static {
        SOCKS_LIST_GREEN_LESS_THAN_50_CP.add(SOCKS_2_GREEN_45CP_30QUA);
        SOCKS_LIST_GREEN_LESS_THAN_50_CP.add(SOCKS_2_GREEN_40CP_40QUA);
    }
}
