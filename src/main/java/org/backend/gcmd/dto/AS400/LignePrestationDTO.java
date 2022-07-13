package org.backend.gcmd.dto.AS400;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LignePrestationDTO {


    private Double codeCpa;

    private Double numeroDossier;

    private Double numeroBonCommande;

    private Double numeroOrdrePrestation;

    private Double codePrestation;

    private Double dateDebut;

    private Double heureDebut;

    private Double dateFin;

    private Double heureFin;

    private Double poids;

    private Double quantite1;

    private Double quantite2;

    private String importExport;

    private String numeroDeclaration;

    private Boolean isDeleted = false;

}
