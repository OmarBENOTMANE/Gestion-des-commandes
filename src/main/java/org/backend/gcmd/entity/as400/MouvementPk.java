package org.backend.gcmd.entity.as400;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MouvementPk implements  Serializable {

        @Column(name = "NUESCM" , insertable = false, unique = false, updatable = false, nullable = false)
        private  Double numeroEscale;

        @Column(name = "DTDEBM"  , insertable = false, unique = false, updatable = false, nullable = false)
        private  Double date;

        @Column(name = "HRDEBM" , insertable = false, unique = false, updatable = false, nullable = false)
        private  Double heure;


}
