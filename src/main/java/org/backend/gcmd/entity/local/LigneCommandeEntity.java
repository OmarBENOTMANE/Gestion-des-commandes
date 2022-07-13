package org.backend.gcmd.entity.local;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Table(name = "GCMD_LIGNE_COMMANDE")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LigneCommandeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "EXCPA")
    private String codeCpa;


    @Column(name = "EXNUBP")
    private String numeroDossier;


    @Column(name = "EXNUCD")
    private String numeroBonCommande;

    @Column(name = "EXNUOR")
    private Double numeroOrdrePrestation;

    @Column(name = "EXCDPR")
    private Double codePrestation;

    @Column(name = "EXLIBPR")
    private String libellePrestation;

    @Column(name = "EXDTDE")
    private Double dateDebut;


    @Column(name = "EXHEDE")
    private Double heureDebut;


    @Column(name = "EXDTFN")
    private Double dateFin;


    @Column(name = "EXHEFN")
    private Double heureFin;


    @Column(name = "EXTONN")
    private Double poids;


    @Column(name = "EXNBRS")
    private Double quantite1;


    @Column(name = "EXNBRT")
    private Double quantite2;

    @Column(name = "EXTYIE")
    private String importExport;

    @Column(name = "EXNUDE")
    private String numeroDeclaration;


    @Column
    private Boolean isGenerated;

    private Boolean isDeleted = false;
    private Boolean isSelected = false;

}

