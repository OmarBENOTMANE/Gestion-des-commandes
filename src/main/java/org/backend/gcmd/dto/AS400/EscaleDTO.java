package org.backend.gcmd.dto.AS400;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EscaleDTO {


    private Double numeroEscale;

    private String nomNavire;

    private String numeroLloyd;

    private String typeNavire;

    private Double dateArriv√©eEstimative;

    private Boolean isDeleted = false;
}
