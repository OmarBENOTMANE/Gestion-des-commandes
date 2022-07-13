TRUNCATE TABLE  public.descal;
TRUNCATE TABLE  public.destcg;
TRUNCATE TABLE  public.destdm;
TRUNCATE TABLE  public.dmvnav;



INSERT INTO public.descal(
    eta1, nmnave, nuesc, nullye, tynave)
VALUES (12025456, 'navire6', 12000, '120', '20'),
       (12025457, 'navire4', 12001, '121', '20'),
       (12025458, 'navire5', 12002, '122', '20'),
       (12025459, 'navire7', 12003, '123', '20'),
       (12025460, 'navire2', 12004, '124', '20'),
       (12025461, 'navire3', 12005, '125', '20'),
       (12025462, 'navire1', 12006, '126', '20');

INSERT INTO public.destcg(
     codcg, nuescg)
VALUES ( '25', 12000),
       ( '26', 12001),
       ( '27', 12002),
       ( '28', 12003),
       ('29', 12004),
       ( '30', 12005),
       ( '31', 12006);


INSERT INTO public.destdm(
    dtdebm, hrdebm, codmvm, cpstm, metr1m, nuescm)
VALUES (20220515, 1403, 5, 'CP01', 200, 12000),
       (20220515, 1403, 5, 'CP01', 200, 12001),
       (20220515, 1403, 5, 'CP01', 200, 12002),
       (20220515, 1403, 5, 'CP01', 200, 12003),
       (20220515, 1403, 5, 'CP01', 200, 12004),
       (20220515, 1403, 5, 'CP01', 200, 12005),
       (20220515, 1403, 5, 'CP01', 200, 12006),
       (20220515, 1403, 5, 'CP01', 200, 12007),
       (20220515, 1403, 5, 'CP01', 200, 12008);

INSERT INTO public.dmvnav(
    codmvt, libmvt)
VALUES (1, 'ACCOSTAGE'),
 (2, 'DEHALAGE'),
 (3, 'CHANGEMENT DE POSTE'),
 (5, 'APPAREILLAGE DU QUAI');

INSERT INTO public.DCONSI(
    CDCSG, NOMCON)
VALUES ('25', 'casa'),
       ('26', 'rabat'),
       ('27', 'beni-mellal'),
       ('28', 'marakesh'),
       ('29', 'agadir'),
       ('0'', ''laayoun''),
       (''331', 'dakhla'),
       ('32', 'tanger');




select * from BDDSIPOR.DBULPRD
where BUNUBP = 'DTV 2022070021';







