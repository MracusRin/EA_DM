package ru.ea_dm.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ea_dm.models.ServiceImpl;
import ru.ea_dm.repositories.ServiceImplRepository;


import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ServiceImplService {
    private final ServiceImplRepository serviceImplRepository;

    public List<ServiceImpl> findAll() {
        return serviceImplRepository.findAll();
    }

    public ServiceImpl findById(Long id) {
        Optional<ServiceImpl> service = serviceImplRepository.findById(id);
        return service.orElseThrow(() -> new RuntimeException("Service not found"));
    }

    @Transactional
    public void save(ServiceImpl service) {
        log.info("Service {} SAVE", service.getTitle());
        serviceImplRepository.save(service);
    }

    @Transactional
    public void delete(Long id) {
        serviceImplRepository.deleteById(id);
    }

    // TODO: После обновления сервисов теряется дота создания, добавить id в сигнатуру
    @Transactional
    public void update(ServiceImpl updatedService, Long id) {
        ServiceImpl service = serviceImplRepository.findById(id).get();
        updatedService.setCreatedAt(service.getCreatedAt());
        log.info("Service {} UPDATED", updatedService.getTitle());
        serviceImplRepository.save(updatedService);
    }
}
