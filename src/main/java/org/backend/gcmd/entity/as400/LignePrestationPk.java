package org.backend.gcmd.entity.as400;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LignePrestationPk implements  Serializable {

        @Column(name = "EXNUBP" , insertable = false, unique = false, updatable = false, nullable = false)
        private  String  numeroDossier;

        @Column(name = "EXNUOR"  , insertable = false, unique = false, updatable = false, nullable = false)
        private  Double numeroOrdrePrestation;


}
