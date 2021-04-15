package lambda.school.javaordersproject.services;

import lambda.school.javaordersproject.models.Customer;
import lambda.school.javaordersproject.views.AdvanceAmounts;
import lambda.school.javaordersproject.views.OrderCounts;

import java.util.List;

public interface CustomerServices {
    Customer save(Customer customer);

    List<Customer> findAllOrders();

    Customer findCustomerById(long custcode);

    List<Customer> findByNameLike(String subname);

    List<OrderCounts> getOrderCounts();

    List<AdvanceAmounts> getAdvanceAmounts();

    void delete(long id);

    Customer update(Customer customer, long custcode);
}
