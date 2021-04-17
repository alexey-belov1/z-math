package ru.zmath.rest.repository;

import org.springframework.data.repository.CrudRepository;
import ru.zmath.rest.model.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Integer> {
}