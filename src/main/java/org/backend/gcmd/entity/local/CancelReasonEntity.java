package org.backend.gcmd.entity.local;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "motif_annulation")
@Data
@Entity
public class CancelReasonEntity {

    @Id
    @Column
    String key ;

    @Column
    String value;

}
