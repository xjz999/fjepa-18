package org.fjepa.app.repository;

import org.fjepa.app.domain.Memuser;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Memuser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MemuserRepository extends JpaRepository<Memuser, Long> {

}
