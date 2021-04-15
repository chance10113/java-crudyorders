package lambda.school.javaordersproject.repositories;

import lambda.school.javaordersproject.models.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findByCustname(String custname);

    List<Customer> findByCustnameContainingIgnoringCase(String likename);

   List<OrderCounts> findOrderCounts();

    List<AdvanceAmounts> findAdvanceAmounts();
}