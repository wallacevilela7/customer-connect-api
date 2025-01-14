package wvsdev.costumerconnectapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import wvsdev.costumerconnectapi.controller.CustomerController;
import wvsdev.costumerconnectapi.controller.dto.CreateUserDto;
import wvsdev.costumerconnectapi.entity.CustomerEntity;
import wvsdev.costumerconnectapi.repository.CustomerRepository;

import static org.springframework.util.StringUtils.hasText;

@Service
public class CustomerService {
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public CustomerEntity createCustomer(CreateUserDto data) {
        var entity = new CustomerEntity();
        entity.setFullName(data.fullName());
        entity.setEmail(data.email());
        entity.setCpf(data.cpf());
        entity.setPhoneNumber(data.phoneNumber());

        return repository.save(entity);
    }

    public Page<CustomerEntity> findAll(Integer page, Integer pageSize, String orderBy, String email, String cpf) {
        var pageRequest = getPageRequest(page, pageSize, orderBy);

        return findWithFilter(email, cpf, pageRequest);
    }

    private Page<CustomerEntity> findWithFilter(String email, String cpf, PageRequest pageRequest) {
        if(hasText(cpf)){
            return repository.findByCpf(cpf, pageRequest);
        }

        if (hasText(email)){
            return repository.findByEmail(email, pageRequest);
        }

        if(hasText(cpf) && hasText(email)){
            return repository.findByCpfAndEmail(cpf, email, pageRequest);
        }

        return repository.findAll(pageRequest);
    }

    private PageRequest getPageRequest(Integer page, Integer pageSize, String orderBy) {
        var direction = Sort.Direction.DESC;
        if (orderBy.equalsIgnoreCase("asc")) {
            direction = Sort.Direction.ASC;
        }

        return PageRequest.of(page, pageSize, direction, "createdAt");
    }
}
