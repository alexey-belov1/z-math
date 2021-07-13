package ru.zmath.back.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zmath.back.model.Payment;
import ru.zmath.back.repository.PaymentRepository;
import ru.zmath.back.service.dto.PaymentDTO;
import ru.zmath.back.service.mapper.PaymentMapper;

import java.util.GregorianCalendar;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public PaymentService(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    @Transactional
    public PaymentDTO save(PaymentDTO paymentDTO) {
        Payment payment = this.paymentMapper.toEntity(paymentDTO);
        payment.setCreated(GregorianCalendar.getInstance());
        return this.paymentMapper.toDto(this.paymentRepository.save(payment));
    }
}
