package lambda.school.javaordersproject.repositories;

import lambda.school.javaordersproject.models.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
