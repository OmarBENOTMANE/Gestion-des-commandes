package org.backend.gcmd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LigneCommandeDTO {

    private Long id;

    private String codeCpa;

    private String numeroDossier;

    private String numeroBonCommande;

    private Double numeroOrdrePrestation;

    private Double codePrestation;

    private String libellePrestation;

    private Double dateDebut;

    private Double heureDebut;

    private Double dateFin;

    private Double heureFin;

    private Double poids;

    private Double quantite1;

    private Double quantite2;

    private String importExport;

    private String numeroDeclaration;

    private String numeroCmd;
    private Boolean isGenerated;


    private Boolean isDeleted = false;
    private Boolean isSelected = false;


}
