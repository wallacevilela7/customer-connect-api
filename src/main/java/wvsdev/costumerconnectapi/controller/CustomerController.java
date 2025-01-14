package wvsdev.costumerconnectapi.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wvsdev.costumerconnectapi.controller.dto.ApiResponse;
import wvsdev.costumerconnectapi.controller.dto.CreateUserDto;
import wvsdev.costumerconnectapi.controller.dto.PaginationResponse;
import wvsdev.costumerconnectapi.controller.dto.UpdateCustomerDto;
import wvsdev.costumerconnectapi.entity.CustomerEntity;
import wvsdev.costumerconnectapi.service.CustomerService;

import java.net.URI;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> createCustomer(@RequestBody CreateUserDto data) {
        var customer = service.createCustomer(data);
        return ResponseEntity.created(URI.create("/customers/" + customer.getCustomerId())).build();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<CustomerEntity>> listAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                               @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                               @RequestParam(name = "orderBy", defaultValue = "desc") String orderBy,
                                                               @RequestParam(name = "email", required = false) String email,
                                                               @RequestParam(name = "cpf", required = false) String cpf){
            var pageResponse =  service.findAll(page,pageSize,orderBy,email,cpf);

            return ResponseEntity.ok(new ApiResponse<>(
                    pageResponse.getContent(),
                    new PaginationResponse(
                            pageResponse.getNumber(),
                            pageResponse.getSize(),
                            pageResponse.getTotalElements(),
                            pageResponse.getTotalPages()
                    )
            ));
    }

    @GetMapping(path = "/{customerId}")
    public ResponseEntity<CustomerEntity> findById(@PathVariable("customerId") Long customerId){
        var customer = service.findById(customerId);

        return customer.isPresent() ?
                ResponseEntity.ok(customer.get()) :
                ResponseEntity.notFound().build();
    }

    @PutMapping(path = "/{customerId}")
    public ResponseEntity<CustomerEntity> updateById(@PathVariable("customerId") Long customerId,
                                                     @RequestBody UpdateCustomerDto data){
        var customer = service.updateById(customerId, data);

        return customer.isPresent() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/{customerId}")
    public ResponseEntity<Void> deleteById(@PathVariable("customerId") Long customerId){
        boolean deleted = service.deleteById(customerId);

        return deleted ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }
}
