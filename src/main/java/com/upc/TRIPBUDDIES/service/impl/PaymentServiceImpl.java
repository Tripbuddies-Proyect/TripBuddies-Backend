package com.upc.TRIPBUDDIES.service.impl;

import com.upc.TRIPBUDDIES.entities.Payment;
import com.upc.TRIPBUDDIES.repository.IPaymentRepository;
import com.upc.TRIPBUDDIES.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements IPaymentService {
    @Autowired
    private final IPaymentRepository paymentRepository;

    public PaymentServiceImpl(IPaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    @Override
    public Payment save(Payment payment) throws Exception {
        return paymentRepository.save(payment);
    }

    @Override
    public void delete(Long id) throws Exception {
        paymentRepository.deleteById(id);

    }

    @Override
    public List<Payment> getAll() throws Exception {
        return paymentRepository.findAll();
    }

    @Override
    public Optional<Payment> getById(Long id) throws Exception {
        return paymentRepository.findById(id);
    }
}
