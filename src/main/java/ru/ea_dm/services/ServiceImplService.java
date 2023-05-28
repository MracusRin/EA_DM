package ru.ea_dm.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ea_dm.models.ServiceIml;
import ru.ea_dm.repositories.ServiceImplRepository;


import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ServiceImplService {
    private final ServiceImplRepository serviceImplRepository;

    public List<ServiceIml> findAll() {
        return serviceImplRepository.findAll();
    }

    public ServiceIml findById(Long id) {
        Optional<ServiceIml> service = serviceImplRepository.findById(id);
        return service.orElseThrow(() -> new RuntimeException("Service not found"));
    }

    @Transactional
    public void save(ServiceIml service) {
        log.info("Service {} SAVE", service.getTitle());
        serviceImplRepository.save(service);
    }

    @Transactional
    public void delete(Long id) {
        serviceImplRepository.deleteById(id);
    }

    @Transactional
    public void update(ServiceIml updatedService) {
        log.info("Service {} UPDATED", updatedService.getTitle());
        serviceImplRepository.save(updatedService);
    }
}
