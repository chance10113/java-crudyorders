package lambda.school.javaordersproject.repositories;

import lambda.school.javaordersproject.models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
