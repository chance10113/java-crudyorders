package lambda.school.javaordersproject.services;

import lambda.school.javaordersproject.models.Payment;
import lambda.school.javaordersproject.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service(value = "paymentServices")
public interface PaymentServices {

    @Autowired
    private PaymentRepository paymentrepos;

    @Transactional
    @Override
    public Payment save(Payment payment)
    {
        return paymentrepos.save(payment);
    }
}
