package ru.zmath.rest.service.mapper;

import org.mapstruct.Mapper;
import ru.zmath.rest.model.Payment;
import ru.zmath.rest.service.dto.PaymentDTO;

@Mapper(componentModel = "spring", uses = {MethodMapper.class})
public interface PaymentMapper extends EntityMapper<PaymentDTO, Payment> {

    default Payment fromId(Integer id) {
        if (id == null) {
            return null;
        }
        Payment payment = new Payment();
        payment.setId(id);
        return payment;
    }
}
