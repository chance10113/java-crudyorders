package lambda.school.javaordersproject.services;

import lambda.school.javaordersproject.models.Payment;
import lambda.school.javaordersproject.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "paymentServices") // value is name used in our program
public class PaymentServicesImpl implements PaymentServices
{
    @Autowired
    PaymentRepository paymentrepos;

    @Override
    public Payment save(Payment payment)
    {
        return paymentrepos.save(payment);
    }
}