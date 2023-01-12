package ru.ea_dm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ea_dm.models.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
