package pro.sky.warehouseaccountingautomationapp.mapper;

import org.mapstruct.Mapper;
import pro.sky.warehouseaccountingautomationapp.dto.SocksDto;
import pro.sky.warehouseaccountingautomationapp.model.Socks;

@Mapper(componentModel = "spring")
public interface SocksMapper {

    Socks socksDtoToSocks(SocksDto socksDto);
}
