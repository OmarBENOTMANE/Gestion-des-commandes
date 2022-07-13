package org.backend.gcmd.repository.local;

import org.backend.gcmd.entity.local.CommandeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommandeRepository extends JpaRepository<CommandeEntity, Long> , JpaSpecificationExecutor<CommandeEntity> {

	Page<CommandeEntity> findAllByIsDeletedFalseAndIsGeneratedFalse(Pageable page);

	Optional<CommandeEntity> findByNumeroCmd(String numBp);

	@Query(value = "SELECT c From  CommandeEntity c WHERE  c.numeroEscale =:numEscale and c.status <> org.backend.gcmd.enums.BpStatusEnum.CANCELED")
	Optional<CommandeEntity> findByNumeroEscale(Double numEscale);

	Page<CommandeEntity> findAllByIsGeneratedTrue(Pageable pag);

	@Query("from CommandeEntity c where c.isGenerated = true and " +
			"c.status <>  org.backend.gcmd.enums.BpStatusEnum.SENT AND" +
			" c.status <>  org.backend.gcmd.enums.BpStatusEnum.CANCELED AND" +
			" c.status <>  org.backend.gcmd.enums.BpStatusEnum.PRECANCELED  ")
	Page<CommandeEntity> findAllGeneratedAndNotSent(Pageable page);

	@Query("from CommandeEntity c where c.isGenerated = true  AND " +
			"c.status = org.backend.gcmd.enums.BpStatusEnum.PRECANCELED")
	Page<CommandeEntity> findAllPreCanceled(Pageable page);

	@Query("from CommandeEntity c where c.isGenerated = true  AND " +
			"c.status = org.backend.gcmd.enums.BpStatusEnum.PRECANCELED")
	Page<CommandeEntity> findAllPreValidated(Pageable page);

	@Query("From CommandeEntity c where c.status= org.backend.gcmd.enums.BpStatusEnum.SENT or" +
			" c.status = org.backend.gcmd.enums.BpStatusEnum.CANCELED OR" +
			" c.status = org.backend.gcmd.enums.BpStatusEnum.PRECANCELED ")
	Page<CommandeEntity> findAllBySent(Pageable page);


	@Query("From CommandeEntity c where c.isGenerated = false")
	Page<CommandeEntity> cmdToBill(Pageable page);

	@Modifying
	@Query("update CommandeEntity c set c.isGenerated = false  where c.numeroCmd = ?1")
	void setIsGeneratedFalse(String numeroCmd);



	@Modifying
	@Query("update CommandeEntity c set c.status = org.backend.gcmd.enums.BpStatusEnum.VALIDATED  where c.numeroBulletinPrestation = ?1")
	void setIsValidate(String numeroCmd );

	@Modifying
	@Query("update CommandeEntity c set c.status = org.backend.gcmd.enums.BpStatusEnum.SAVED  where c.numeroBulletinPrestation = ?1")
	void cancelValidation (String numeroCmd );



	@Modifying
	@Query("update CommandeEntity c set c.status = org.backend.gcmd.enums.BpStatusEnum.SENT   where c.numeroBulletinPrestation = ?1")
	void sent(String numBP );


	@Modifying
	@Query("update CommandeEntity c set c.status = org.backend.gcmd.enums.BpStatusEnum.SAVED  where c.numeroCmd = ?1")
	void setStatusToSaved(String numeroCmd);


	@Modifying
	@Query("update CommandeEntity c set c.status = org.backend.gcmd.enums.BpStatusEnum.INITIAL  where c.numeroCmd = ?1")
	void setStatusToInitial(String numeroCmd);


	@Modifying
	@Query("update CommandeEntity c set c.status = org.backend.gcmd.enums.BpStatusEnum.CANCELED  where c.numeroBulletinPrestation = ?1")
	void setStatusCancel(String numeroBp);

	Optional<CommandeEntity> findByNumeroBulletinPrestation( String numBp);


	@Modifying
	@Query("update CommandeEntity c set c.numeroBulletinPrestation =null   where c.numeroCmd = ?1")
	void setNumBpToNull(String numCmd);
}