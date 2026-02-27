package ee.argo.veebipood.repository;

import ee.argo.veebipood.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders,Long> {
}
