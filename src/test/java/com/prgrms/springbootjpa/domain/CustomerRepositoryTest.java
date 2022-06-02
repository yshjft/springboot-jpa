package com.prgrms.springbootjpa.domain;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;

    Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer("jerry", "hong");
        customerRepository.save(customer);
    }

    @Test
    @DisplayName("고객 create Read 테스트")
    void createReadTest() {
        Customer entity = customerRepository.findById(customer.getId()).get();
        assertThat(entity, samePropertyValuesAs(entity));
    }

    @Test
    @DisplayName("고객 update 테스트")
    void updateTest() {
        customer.changeFirstName("yuseok");

        Customer updatedCustomer = customerRepository.findById(customer.getId()).get();
        assertThat(updatedCustomer.getFirstName(), is("yuseok"));
    }

    @Test
    @DisplayName("고객 delete 테스트")
    void deleteTest() {
        customerRepository.delete(customer);
        Optional<Customer> nonExistentCustomer = customerRepository.findById(customer.getId());
        assertThat(nonExistentCustomer, is(Optional.empty()));
    }
}