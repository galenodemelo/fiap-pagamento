package com.fiap.restaurant.types.mapper;

import com.fiap.restaurant.entity.Payment;
import com.fiap.restaurant.external.db.payment.PaymentJpa;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMapper {

    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    Payment toPayment(PaymentJpa paymentJpa);
}
