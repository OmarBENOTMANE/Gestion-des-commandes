package org.backend.gcmd.entity.local;


import lombok.*;

import javax.persistence.*;

@Builder
@Table(name = "IndexGenerator")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class IndexGeneratorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "key")
    private String key ;

    @Column(name = "value")
    private int value;


}
