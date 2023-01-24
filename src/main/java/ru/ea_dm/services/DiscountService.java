package ru.ea_dm.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ea_dm.models.Discount;
import ru.ea_dm.repositories.DiscountRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DiscountService {
    private final DiscountRepository discountRepository;

    public List<Discount> findAll() {
        return discountRepository.findAll();
    }

    @Transactional
    public void save(Discount discount) {
        discountRepository.save(discount);
    }

    @Transactional
    public void delete(Long id) {
        discountRepository.deleteById(id);
    }

    public Optional<Discount> findById(Long id) {
        return discountRepository.findById(id);
    }

    @Transactional
    public void update(Discount discount) {
        discountRepository.save(discount);
    }
}
