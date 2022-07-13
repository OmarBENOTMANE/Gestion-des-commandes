package org.backend.gcmd.entity.prestati;

import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Table(name = "PRESTATI")
@NoArgsConstructor
@AllArgsConstructor
@Entity()
public class LibellePrestationEntity {

    @Id
    @Column(name = "PRCODE")
    private Double codePrestation;

    @Column(name = "PRDESI")
    private String designationPrestation;

    @Column(name = "PRCOTV")
    private String codeTVA;

    @Column(name = "PRCOAC")
    private String codeActivite;


}
