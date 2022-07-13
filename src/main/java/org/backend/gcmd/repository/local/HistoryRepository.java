package org.backend.gcmd.repository.local;

import org.backend.gcmd.entity.local.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<HistoryEntity , Long> {

}
