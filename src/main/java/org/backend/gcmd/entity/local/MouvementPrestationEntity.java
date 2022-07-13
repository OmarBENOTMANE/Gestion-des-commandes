package org.backend.gcmd.entity.local;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Data
@Table(name = "GCMD_MOUVEMENT_PRESTATION")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MouvementPrestationEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CODEPRESTATION")
    private String codePrestation;

    @Column(name = "CODEMOUVEMENT")
    private String codeMouvement;

    @Column(name = "LIBELLEPRESTATION")
    private String libellePrestation;

    @Column(name = "LIBELLEMOUVEMENT")
    private String libelleMouvement;
}
