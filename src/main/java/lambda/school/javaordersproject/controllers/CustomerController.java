package lambda.school.javaordersproject.controllers;

import lambda.school.javaordersproject.models.Customer;
import lambda.school.javaordersproject.services.CustomerServices;
import lambda.school.javaordersproject.views.AdvanceAmounts;
import lambda.school.javaordersproject.views.OrderCounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerServices customerServices;

    //    http://localhost:2019/customers/orders
    @GetMapping(value = "/orders", produces = "application/json")
    public ResponseEntity<?> findAllOrders(){
        List<Customer> rtnList = customerServices.findAllOrders();
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

    //    http://localhost:2019/customers/customer/3
    @GetMapping(value = "/customer/{custcode}", produces = "application/json")
    public ResponseEntity<?> findCustomerById(@PathVariable long custcode){
        Customer rtnCust = customerServices.findCustomerById(custcode);
        return new ResponseEntity<>(rtnCust, HttpStatus.OK);
    }

    //    http://localhost:2019/customers/namelike/mes
    @GetMapping(value = "/namelike/{subname}", produces = "application/json")
    public ResponseEntity<?> findCustomersLikeName(@PathVariable String subname){
        List<Customer> rtnList = customerServices.findByNameLike(subname);
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

    //  http://localhost:2019/customers/orders/count
    @GetMapping(value = "/orders/count", produces = "application/json")
    public ResponseEntity<?> getOrderCounts()
    {
        List<OrderCounts> rtnList = customerServices.getOrderCounts();
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

    //  http://localhost:2019/customers/orders/advanceamount
    @GetMapping(value = "orders/advanceamount", produces = "application/json")
    public ResponseEntity<?> getAdvanceAmount()
    {
        List<AdvanceAmounts> rtnList = customerServices.getAdvanceAmounts();
        return new ResponseEntity<>(rtnList,HttpStatus.OK);
    }

    //    POST http://localhost:2019/customers/customer
    @PostMapping(value = "/customer", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addNewCustomer(@Valid
                                            @RequestBody
                                                    Customer newCustomer)
    {
        newCustomer.setCustcode(0);
        newCustomer = customerServices.save(newCustomer);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{custcode}")
                .buildAndExpand(newCustomer.getCustcode())
                .toUri();
        responseHeaders.setLocation(newCustomerURI);

        return new ResponseEntity<>(newCustomer,responseHeaders, HttpStatus.CREATED);
    }
    //    PUT http://localhost:2019/customers/customer/19
    @PutMapping(value = "/customer/{custcode}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateCompleteCustomer(@Valid @RequestBody Customer updateCustomer,
                                                    @PathVariable long custcode)
    {
        updateCustomer.setCustcode(custcode);
        updateCustomer = customerServices.save(updateCustomer);
        return new ResponseEntity<>(updateCustomer, HttpStatus.OK);
    }

    //    PATCH http://localhost:2019/customers/customer/19
    @PatchMapping(value = "customer/{custcode}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer updateCustomer,
                                            @PathVariable long custcode)
    {
        updateCustomer = customerServices.update(updateCustomer, custcode);
        return new ResponseEntity<>(updateCustomer, HttpStatus.OK);
    }

    //    DELETE http://localhost:2019/customers/customer/54
    @DeleteMapping(value = "customer/{custcode}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable long custcode)
    {
        customerServices.delete(custcode);
        return new  ResponseEntity<>(HttpStatus.OK);
    }

}