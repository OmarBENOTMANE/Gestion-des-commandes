package org.backend.gcmd.entity.as400;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Getter
@Setter
@Table(name = "DESTCG")
@NoArgsConstructor
@AllArgsConstructor
@IdClass(EscaleClientEntity.class)
@Entity(name = "EscaleClientEntity")
public class EscaleClientEntity implements Serializable {

    @Id
    @Column(name = "NUESCG")
    private Double numEscal;

    @PostLoad
    protected void repair(){
        if(codeClient!=null)codeClient=codeClient.trim();
    }
    @Id
    @Column(name = "CODCG")
    private String codeClient;


}
