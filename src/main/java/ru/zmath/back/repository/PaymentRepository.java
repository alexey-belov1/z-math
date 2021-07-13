package ru.zmath.back.repository;

import org.springframework.data.repository.CrudRepository;
import ru.zmath.back.model.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Integer> {
}
