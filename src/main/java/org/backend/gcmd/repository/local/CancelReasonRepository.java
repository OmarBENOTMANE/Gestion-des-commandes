package org.backend.gcmd.repository.local;

import org.backend.gcmd.entity.local.CancelReasonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CancelReasonRepository extends JpaRepository<CancelReasonEntity , Long> {
}
