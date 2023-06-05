package ru.ea_dm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ea_dm.models.ServiceImpl;

@Repository
public interface ServiceImplRepository extends JpaRepository<ServiceImpl, Long> {

}
