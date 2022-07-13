package org.backend.gcmd.repository.AS400;

import org.backend.gcmd.entity.as400.NavireEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NavireRepository extends JpaRepository<NavireEntity, Long> {

      NavireEntity findByNumeroLloyd(String  numeroLloyd);

}