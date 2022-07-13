package org.backend.gcmd.entity.as400;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Table(name = "DESTDM")
@NoArgsConstructor
@AllArgsConstructor
@IdClass(MouvementPk.class)
@Entity
public class MouvementEntity {

    @Id
    @Column(name = "NUESCM")
    private Double numeroEscale;

    @Column(name = "CODMVM")
    private String codeMouvementNavire;

    @Id
    @Column(name = "DTDEBM")
    private Double date;

    @Id
    @Column(name = "HRDEBM")
    private Double heure;

    @Column(name = "CPSTM")
    private String codePoste;

    @Column(name = "METR1M")
    private Double metriquePoste;

}
