package wvsdev.costumerconnectapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import wvsdev.costumerconnectapi.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    Page<CustomerEntity> findByCpf(String cpf, PageRequest pageRequest);

    Page<CustomerEntity> findByEmail(String email, PageRequest pageRequest);

    Page<CustomerEntity> findByCpfAndEmail(String cpf, String email, PageRequest pageRequest);
}
