package org.backend.gcmd.entity.as400;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Table(name = "DMVNAV")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CodeMouvementEntityAS400")
public class CodeMouvementEntity {

    @Id
    @Column(name = "CODMVT")
    private volatile String  codeMouvementNavire;

    @Column(name = "LIBMVT")
    private String libelle;

}
