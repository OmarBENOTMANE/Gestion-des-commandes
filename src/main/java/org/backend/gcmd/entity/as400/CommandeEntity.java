package org.backend.gcmd.entity.as400;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@Table(name = "DCOMPRD")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CommandeEntityAS400")
public class CommandeEntity {

    @Column(name = "CPAPR")
    private String codeCpa;

    @Id
    @Column(name = "NUMBPR")
    private String numeroDossier;

    @Column(name = "NUMBCD")
    private String numeroBonCommande;

    @CreatedDate
    @Column(name = "DATECD")
    private Double  dateCreationCommande;

    @Column(name = "DATEEX")
    private Double dateExecutionCommande;


}
