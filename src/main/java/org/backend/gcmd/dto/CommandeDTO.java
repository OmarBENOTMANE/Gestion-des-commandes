package org.backend.gcmd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.backend.gcmd.enums.BpStatusEnum;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CommandeDTO {

    private Long id;

    private String numeroBulletinPrestation;

    private String codeCpa;

    private String codeOperation;

    private String codeClient;

    private String nomClient;

    private Double numeroEscale;

    private String codeOperation1;

    private String codeOperation2;

    private String codeOperation3;

    private String codeNaturePrestation;

    private String bulltinAnnule;

    private String codeCauseAnnulation;

    private String numeroFacture;

    private Double dateLimiteFacture;

    private BpStatusEnum status;

    private String numeroCmd;

    private Double shipLength;


    private Boolean isGenerated = false;

    private Boolean isValidateApproved = false;

    private Boolean isCancelApproved  = false;


}
