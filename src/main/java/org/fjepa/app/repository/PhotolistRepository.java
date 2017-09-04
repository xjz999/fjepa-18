package org.fjepa.app.repository;

import org.fjepa.app.domain.Photolist;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Photolist entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PhotolistRepository extends JpaRepository<Photolist, Long> {

}
