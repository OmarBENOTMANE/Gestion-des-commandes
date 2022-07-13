package org.backend.gcmd.entity.as400;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Table(name = "DNAVIR")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "NavireEntityAS400")
public class NavireEntity {

    @Id
    @Column(name = "NULLYD")
    private String numeroLloyd;

    @Column(name = "NMNAV")
    private String nomNavire;

    @Column(name = "LNGNAV")
    private Double longeurNavire;

    @Column(name = "TREMX")
    private String tirantEau;

    @Column(name = "TYPEN")
    private Double typeNavire;

}
