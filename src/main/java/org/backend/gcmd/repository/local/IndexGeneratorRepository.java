package org.backend.gcmd.repository.local;

import org.backend.gcmd.entity.local.IndexGeneratorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IndexGeneratorRepository extends JpaRepository<IndexGeneratorEntity, Long> {

    IndexGeneratorEntity findByKey(String Key);

    @Query(value = "UPDATE IndexGeneratorEntity e set e.value = 1 where e.key='cmd'")
    void resetIndexes();
}
