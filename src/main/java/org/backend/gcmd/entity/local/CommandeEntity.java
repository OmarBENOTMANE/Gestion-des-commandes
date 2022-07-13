package org.backend.gcmd.entity.local;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.backend.gcmd.enums.BpStatusEnum;
import org.hibernate.annotations.GenericGenerator;

import org.hibernate.annotations.Parameter;
@Getter
@Setter
@Builder
@Table(name = "GCMD_COMMANDE")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CommandeEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private volatile Long id;

    @Size(max = 14 , message = "Command  size must be less than 15")
    private String numeroCmd;

    @Column(name = "BUNUBP")
    private String numeroBulletinPrestation;

    @Column(name = "BUCPA")
    private String codeCpa;

    @Column(name = "BUCODE")
    private String codeOperation;

    @Column(name = "BUTYPC")
    private String codeClient;

    @PostLoad
    public void trim(){
        this.nomClient  = nomClient.trim();
    }
    @Column(name = "BUNOMC")
    private String nomClient;

    @Column(name = "BUNUES")
    private Double numeroEscale;


    @Column(name = "BUMOYE")
    private String codeOperation1;

    @Column(name = "BUPRVA")
    private String codeOperation2;


    @Column(name = "BUVALI")
    private String codeOperation3;


    @Column(name = "BUCDNA")
    private String codeNaturePrestation;

    @Column(name = "BUANUL")
    private String bulltinAnnule;


    @Column(name = "BUCSAN")
    private String codeCauseAnnulation;

    @Column(name = "BUNUFA")
    private String numeroFacture;

    @Column(name = "BUDTLI")
    private Double dateLimiteFacture;

    @Enumerated(EnumType.STRING)
    private BpStatusEnum status;

    @Column(name = "LONGEUR_NAVIRE")
    private Double shipLength;

    @Column
    private Boolean isValidateApproved;

    @Column
    private Boolean isCancelApproved;




    private Boolean isDeleted  = false;

    private Boolean isGenerated = false;



}
