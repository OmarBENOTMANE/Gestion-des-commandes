package org.backend.gcmd.entity.local;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "GCMD_PeiceJointEntity")
@Entity
public class PieceJointEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column
    String fileName;

    @Column
    private String designation ;

    @Column
    String numCmd;



}
