TRUNCATE TABLE  public.gcmd_mouvement_prestation;
TRUNCATE TABLE  public.motif_annulation;
TRUNCATE TABLE  public.indexgenerator;



INSERT INTO public.gcmd_mouvement_prestation(
    libellemouvement, codemouvement , codeprestation, libelleprestation)
VALUES ( 'ACCOSTAGE',1, 20101, 'OPERATION LAMANAGE'),
       ( 'APPAREILLAGE DU QUAI ', 5, 20101, 'OPERATION LAMANAGE'),
       ( 'DEHALAGE', 2, 20102, 'DEHALAGE'),
       ( 'CHANGEMENT DE POST ', 3, 20102, 'DEHALAGE');


INSERT INTO public.indexgenerator(
    id, key, value)
VALUES (1, 'cmd', 1),
       (2, 'bp', 1);

INSERT INTO public.motif_annulation(
    key, value)
VALUES ('R1', 'Raison 1'),
       ('R2', 'Raison 2'),
       ('R3', 'Raison 3');




TRUNCATE TABLE  CMDBP.gcmd_mouvement_prestation;
TRUNCATE TABLE  CMDBP.motif_annulation;
TRUNCATE TABLE  CMDBP.indexgenerator;
TRUNCATE TABLE  CMDBP.gcmd_commande;
TRUNCATE TABLE  CMDBP.gcmd_ligne_commande;
