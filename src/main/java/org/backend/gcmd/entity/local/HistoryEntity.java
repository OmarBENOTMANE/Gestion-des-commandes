package org.backend.gcmd.entity.local;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class HistoryEntity {

    @Id
    private Long id ;

    @Column
    private String user;

    @Column
    private String action ;

}
