package ru.ea_dm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ea_dm.models.ServiceIml;

@Repository
public interface ServiceImplRepository extends JpaRepository<ServiceIml, Long> {

}
