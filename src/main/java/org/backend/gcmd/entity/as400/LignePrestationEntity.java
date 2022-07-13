package org.backend.gcmd.entity.as400;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Table(name = "DEXECPR")
@NoArgsConstructor
@AllArgsConstructor
@IdClass(LignePrestationPk.class)
@Entity(name = "PrestationEntityAS400")
public class LignePrestationEntity {

    @Column(name = "EXCPA")
    private String codeCpa;

	@Id
	@Column(name = "EXNUBP")
	private String  numeroDossier;

	@Column(name = "EXNUCD")
	private String numeroBonCommande;

	@Id
	@Column(name = "EXNUOR")
	private Double numeroOrdrePrestation;

	@Column(name = "EXCDPR")
	private Double codePrestation;

	//@Column(name = "EXLIBPR")
	//private String libellePrestation;

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

}
