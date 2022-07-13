package org.backend.gcmd.entity.as400;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Table(name = "DESCAL")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EscaleEntity {

    @Id
    @Column(name = "NUESC")
    private Double numeroEscale;

    @Column(name = "NMNAVE")
    private String nomNavire;

    @Column(name = "NULLYE")
    private String numeroLloyd;

    @Column(name = "TYNAVE")
    private String typeNavire;

    @Column(name = "ETA1")
    private Double dateArriveeEstimative;

}
