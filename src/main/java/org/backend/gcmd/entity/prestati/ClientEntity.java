package org.backend.gcmd.entity.prestati;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Table(name = "CLIENT")
@NoArgsConstructor
@AllArgsConstructor
@Entity()
public class ClientEntity {

    @PostLoad
    protected void repair(){
        if(codeClient!=null)codeClient=codeClient.trim();
    }
    @Id
    @Column(name = "CLCODC")
    private String codeClient;


    @Column(name = "CLNOM")
    private String nomClient;



}
