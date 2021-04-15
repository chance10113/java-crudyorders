package lambda.school.javaordersproject.services;

import lambda.school.javaordersproject.models.Order;

public interface OrderServices {
    Order save(Order order);

    Order findOrderById(long ordnum);

    Order update(Order order, long ordnum);

    void delete(long ordnum);
}
