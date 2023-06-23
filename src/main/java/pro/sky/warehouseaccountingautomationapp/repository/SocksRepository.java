package pro.sky.warehouseaccountingautomationapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.warehouseaccountingautomationapp.model.Socks;

@Repository
public interface SocksRepository extends JpaRepository<Socks, Long> {

    Socks findSocksByColorIgnoreCaseAndCottonPart(String color, int cottonPart);
}
