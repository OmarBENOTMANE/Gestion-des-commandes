
TRUNCATE TABLE  public.prestati;
TRUNCATE TABLE  public.client;

INSERT INTO public.client(
     clcodc, clnom)
VALUES ('25', 'comanav'),
       ('26', 'utiopia'),
       ('27', 'afriquesud'),
       ('28', 'algerie'),
       ('29', 'tunisia'),
       ('30', 'cameroun'),
       ('31', 'amerique'),
       ('32', 'australie');

INSERT INTO public.prestati(
    prcoac, prcode, prcotv, prdesi)
VALUES (1, 20101, 45, 'OPERATION LAMANAGE'),
       (1, 20102, 67, 'DEHALAGE');

