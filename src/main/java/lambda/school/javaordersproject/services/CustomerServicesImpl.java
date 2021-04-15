package lambda.school.javaordersproject.services;

import lambda.school.javaordersproject.models.Customer;
import lambda.school.javaordersproject.models.Order;
import lambda.school.javaordersproject.models.Payment;
import lambda.school.javaordersproject.repositories.CustomerRepository;
import lambda.school.javaordersproject.repositories.PaymentRepository;
import lambda.school.javaordersproject.views.AdvanceAmounts;
import lambda.school.javaordersproject.views.OrderCounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerServices")
public class CustomerServicesImpl implements CustomerServices{

    @Autowired
    private CustomerRepository customerrepos;

    @Autowired
    private PaymentRepository paymentrepos;

    @Autowired
    private AgentServices agentServices;

    @Transactional
    @Override
    public Customer save(Customer customer)
    {
        Customer newCust = new Customer();
        if(customer.getCustcode() != 0)
        {
            findCustomerById(customer.getCustcode());

            newCust.setCustcode(customer.getCustcode());
        }

        newCust.setCustname(customer.getCustname());
        newCust.setCustcity(customer.getCustcity());
        newCust.setWorkingarea(customer.getWorkingarea());
        newCust.setCustcountry(customer.getCustcountry());
        newCust.setGrade(customer.getGrade());
        newCust.setOpeningamt(customer.getOpeningamt());
        newCust.setReceiveamt(customer.getReceiveamt());
        newCust.setPaymentamt(customer.getPaymentamt());
        newCust.setOutstandingamt(customer.getOutstandingamt());
        newCust.setPhone(customer.getPhone());
        newCust.setAgent(agentServices.findAgentById(customer.getAgent().getAgentcode()));

        newCust.getOrders().clear();
        for (Order o: customer.getOrders())
        {
            Order newOrder = new Order();
            newOrder.setOrderdescription(o.getOrderdescription());
            newOrder.setOrdamount(o.getOrdamount());
            newOrder.setAdvanceamount(o.getAdvanceamount());
            newOrder.setCustomer(newCust);
            newCust.getOrders().add(newOrder);
        }

        newCust.getPayments().clear();
        for (Payment p : customer.getPayments())
        {
            Payment newPayment = paymentrepos.findById(p.getPaymentid())
                    .orElseThrow(()-> new EntityNotFoundException("Payment " + p.getPaymentid() + " NotFound"));

            newCust.getPayments().add(newPayment);
        }

        return customerrepos.save(newCust);
    }

    @Override
    public List<Customer> findAllOrders() {
        List<Customer> list = new ArrayList<>();
        customerrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Customer findCustomerById(long id) {
        return customerrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found!"));
    }

    @Override
    public List<Customer> findByNameLike(String subname) {
        List<Customer> rtnList = customerrepos.findByCustnameContainingIgnoringCase(subname);
        return rtnList;
    }

    @Override
    public List<OrderCounts> getOrderCounts() {
        List<OrderCounts> rtnList = customerrepos.findOrderCounts();
        return rtnList;
    }

    @Override
    public List<AdvanceAmounts> getAdvanceAmounts() {
        List<AdvanceAmounts> rtnList = customerrepos.findAdvanceAmounts();
        return rtnList;
    }

    @Transactional
    @Override
    public void delete(long id) {
        if (customerrepos.findById(id).isPresent())
        {
            customerrepos.deleteById(id);
        }else
        {
            throw new EntityNotFoundException("Customer " + id + " Not Found");
        }
    }

    @Transactional
    @Override
    public Customer update(Customer customer, long id) {
        Customer currentCustomer = findCustomerById(id);

        if (customer.getCustcode() != 0)
        {
            findCustomerById(customer.getCustcode());
            currentCustomer.setCustcode(customer.getCustcode());
        }

        if (customer.getCustname()!= null)
        {
            currentCustomer.setCustname(customer.getCustname());
        }

        if (customer.getCustcity() != null)
        {
            currentCustomer.setCustcity(customer.getCustcity());
        }

        if (customer.getWorkingarea() != null)
        {
            currentCustomer.setWorkingarea(customer.getWorkingarea());
        }

        if (customer.getCustcountry() != null)
        {
            currentCustomer.setCustcountry(customer.getCustcountry());
        }

        if (customer.getGrade()!= null)
        {
            currentCustomer.setGrade(customer.getGrade());
        }

        if (customer.hasvalueforopeningamt)
        {
            currentCustomer.setOpeningamt(customer.getOpeningamt());
        }

        if (customer.hasvalueforreceiveamt)
        {
            currentCustomer.setReceiveamt(customer.getReceiveamt());
        }

        if (customer.hasvalueforpaymentamt)
        {
            currentCustomer.setPaymentamt(customer.getPaymentamt());
        }

        if (customer.hasvalueforoutstandingamt)
        {
            currentCustomer.setOutstandingamt(customer.getOutstandingamt());
        }

        currentCustomer.setCustname(customer.getCustname());
        currentCustomer.setCustcity(customer.getCustcity());
        currentCustomer.setWorkingarea(customer.getWorkingarea());
        currentCustomer.setCustcountry(customer.getCustcountry());
        currentCustomer.setGrade(customer.getGrade());
        currentCustomer.setOpeningamt(customer.getOpeningamt());
        currentCustomer.setReceiveamt(customer.getReceiveamt());
        currentCustomer.setPaymentamt(customer.getPaymentamt());
        currentCustomer.setOutstandingamt(customer.getOutstandingamt());
        currentCustomer.setPhone(customer.getPhone());

        if (customer.getOrders().size() > 0)
        {
            currentCustomer.getOrders().clear();
            for (Order o : customer.getOrders())
            {
                Order newOrder = new Order();
                newOrder.setOrderdescription(o.getOrderdescription());
                newOrder.setOrdamount(o.getOrdamount());
                newOrder.setAdvanceamount(o.getAdvanceamount());
            }
        }

        if (customer.getPayments().size() >0)
        {
            currentCustomer.getPayments().clear();
            for (Payment p : customer.getPayments())
            {
                Payment newPayment = paymentrepos.findById(p.getPaymentid())
                        .orElseThrow(()-> new EntityNotFoundException("Payment " + p.getPaymentid() + " NotFound"));

                currentCustomer.getPayments().add(newPayment);
            }
        }
        return customerrepos.save(currentCustomer);
    }
}
