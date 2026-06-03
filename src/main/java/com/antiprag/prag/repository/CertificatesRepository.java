package com.antiprag.prag.repository;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.antiprag.prag.domain.Certificates;
import com.antiprag.prag.domain.Customer;

@Repository
public interface CertificatesRepository extends JpaRepository<Certificates, Integer>{
    Set<Certificates> findByCustomers(Customer customer);
}
