package org.backend.gcmd.entity.as400;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Table(name = "DCONSI")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BddsiporClientEntity {

    @PostLoad
    protected void repair(){
        if(codeConsignataire!=null)codeConsignataire=codeConsignataire.trim();
    }
    @Id
    @Column(name = "CDCSG")
    private String codeConsignataire;

    @Column(name = "NOMCON")
    private String nomClient;



}
