package ru.ea_dm.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ea_dm.models.Discount;
import ru.ea_dm.repositories.DiscountRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class DiscountService {
    private final DiscountRepository discountRepository;

    public List<Discount> findAll() {
        List<Discount> discounts = discountRepository.findAll();
        discounts.forEach(discount -> discount.setDaysLeft(daysCount(discount)));
        return discounts;
    }
    public List<Discount> findAllActive() {
        List<Discount> discounts = discountRepository.findAll();
        // TODO: Скидки: убрать торой проход по списку
        discounts.forEach(discount -> discount.setDaysLeft(daysCount(discount)));
        discounts = discounts.stream().filter(discount -> discount.getDaysLeft() > 0).collect(Collectors.toList());
        return discounts;
    }

    @Transactional
    public void save(Discount discount) {
        if (discount.getStartDate() == null) {
            discount.setStartDate(LocalDate.now());
        }
        if (discount.getEndDate() == null) {
            discount.setEndDate(LocalDate.now().plusYears(1));
        }
        discountRepository.save(discount);
    }

    @Transactional
    public void delete(Long id) {
        discountRepository.deleteById(id);
    }

    public Optional<Discount> findById(Long id) {
        Optional<Discount> discount = discountRepository.findById(id);
        discount.ifPresent(disc -> disc.setDaysLeft(daysCount(disc)));
        return discount;
    }

    @Transactional
    public void update(Discount discount) {
        discountRepository.save(discount);
    }

    private long daysCount(Discount discount) {
        long days = ChronoUnit.DAYS.between(LocalDate.now(), discount.getEndDate());
        if (days < 0 | LocalDate.now().isBefore(discount.getStartDate())) {
            return 0;
        } else {
            return days;
        }
    }
}
