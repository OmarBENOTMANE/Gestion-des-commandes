package org.backend.gcmd.repository.local;

import org.backend.gcmd.entity.local.PieceJointEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PieceJointRepository extends JpaRepository<PieceJointEntity, Long> {

    Optional<PieceJointEntity> findByNumCmd(String numCmd);


    Long removeByNumCmd(String numCmd);
}
