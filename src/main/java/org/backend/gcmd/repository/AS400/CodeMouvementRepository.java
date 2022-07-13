package org.backend.gcmd.repository.AS400;

import org.backend.gcmd.entity.as400.CodeMouvementEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeMouvementRepository extends JpaRepository<CodeMouvementEntity , Long> {

    CodeMouvementEntity findByCodeMouvementNavire(String codeMvt);

    @Query(value = "SELECT  c FROM CodeMouvementEntityAS400 c  where c.codeMouvementNavire in('1' , '2' , '3' , '5')")
    Page<CodeMouvementEntity> findMovementNavire(Pageable page);
}
