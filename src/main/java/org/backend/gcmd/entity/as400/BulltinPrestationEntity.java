package org.backend.gcmd.entity.as400;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@Table(name = "DBULPRD")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BulltinPrestationEntity {

    @Id
    @Column(name = "BUNUBP")
    private String numeroBulletinPrestation;

    @Column(name = "BUCPA")
    private String codeCpa;

    @Column(name = "BUCODE")
    private String codeClient;

    @Column(name = "BUTYPC")
    private String codeOperation;

    @Column(name = "BUNOMC")
    private String nomClient;

    @Column(name = "BUNUES")
    private Double numeroEscale;

    //valeur C Dans La table
    @Column(name = "BUMOYE")
    private String codeOperation1;

    @Column(name = "BUPRVA")
    private String codeOperation2;

    @Column(name = "BUVALI")
    private String codeOperation3;

    @Column(name = "BUCDNA")
    private String codeNaturePrestation;

    //only one charcter
    @Column(name = "BUANUL")
    private String bulltinAnnule;

    //not null only 2 character
    @Column(name = "BUCSAN")
    private String codeCauseAnnulation;

    @Column(name = "BUNUFA")
    private String numeroFacture;

    @Column(name = "BUDTLI")
    private Double dateLimiteFacture;

}
