package sbnz.visitserbia.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import sbnz.visitserbia.model.Attraction;

public interface AttractionRepository extends JpaRepository<Attraction, Long> {

    @EntityGraph(attributePaths={"image"})
    Attraction findWithPropertyPictureAttachedById(Long id);
}
