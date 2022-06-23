CREATE TABLE CALISAN (
  ad       varchar(20) not null, 
  soyad    varchar(20) not null,
  ssn      char(9),
  dogumT   date not null,
  cinsiyet varchar(10) not null,
  baslamaT date not null,
  calismaD varchar(10) not null,
  salary   int,
  primary key (ssn)
);

CREATE TABLE BASVURAN (
  ad           varchar(25) not null,
  soyad        varchar(20) not null,
  TC           char(11),
  dogumT       date not null,
  cinsiyet     varchar(10) not null,
  kanG         varchar(10) not null,
  tarih        date not null,
  covid        varchar(10) not null,
  odul         varchar(20),
  primary key (TC)
);


CREATE TABLE ISLEM (
  islemID      smallint,
  islemTipi    varchar(10) not null,
  islemTC      char(11),
  kanGr        varchar(10) not null,
  kanAldigiTC  char(11),
  issn         char(9),
  tarih        date not null,
  primary key (islemID),
  foreign key (islemTC) references BASVURAN(TC) ON DELETE NO ACTION ON UPDATE NO ACTION,
  foreign key (kanAldigiTC) references BASVURAN(TC) ON DELETE NO ACTION ON UPDATE NO ACTION,
  foreign key (issn) references CALISAN(ssn) ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE PLAZMA (
  pTC          char(11),
  tedaviTipi   varchar(30),
  iyilesenTC   char(11),
  covidBas     date not null,
  covidBit     date not null,
  primary key (pTC,tedaviTipi,iyilesenTC),
  foreign key (pTC) references BASVURAN(TC) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CHECK ((covidBas)>('2020-01-01'))
);

CREATE SEQUENCE seq
start with 1
increment by 1
;

create or replace function yaskontrol()
returns trigger 
as 
$$
begin
IF extract(YEAR FROM age(current_date,NEW.dogumt))<18 THEN
RAISE NOTICE'18 yasindan kucuk bireyler kan bagisinda bulunamaz';
RETURN NULL;
ELSIF extract(YEAR FROM age(current_date,NEW.dogumt))>65 THEN
RAISE NOTICE '65 yasindan buyuk bireyler kan bagisinda bulunamaz';
RETURN NULL;
ELSE
RETURN NEW;
END IF;
END
$$
language plpgsql;

create trigger yastrig
before insert on basvuran 
for each row
execute procedure yaskontrol();


create type yeni AS (ad VARCHAR(25),soyad varchar(20),tc char(11),sayac integer);

create or replace function odulkontrol()
returns trigger AS $$
DECLARE	
bilgi yeni;
BEGIN

select ad,soyad,tc, count(islemtipi) into bilgi
from basvuran b, islem i
where b.tc=i.islemtc and islemtipi='Kan Verme' and islemtc=new.islemtc group by tc;

IF bilgi.sayac=10 THEN
update basvuran set odul='Bronz Madalya' where tc=new.islemtc;
RAISE NOTICE ' Ad : %  Soyad : %  TC : %  vatandaşımız 10 kez bağış yaparak BRONZ MADALYA kazanmıştır.',bilgi.ad,bilgi.soyad,bilgi.tc;
return new;
ELSIF bilgi.sayac=25 THEN
update basvuran set odul='Gümüş Madalya' where tc=new.islemtc;
RAISE NOTICE ' Ad : %  Soyad : %  TC : %  vatandaşımız 25 kez bağış yaparak GÜMÜŞ MADALYA kazanmıştır.',bilgi.ad,bilgi.soyad,bilgi.tc;
return new;
ELSIF bilgi.sayac=35 THEN
update basvuran set odul='Altın Madalya' where tc=new.islemtc;
RAISE NOTICE ' Ad : %  Soyad : %  TC : %  vatandaşımız 35 kez bağış yaparak ALTIN MADALYA kazanmıştır.',bilgi.ad,bilgi.soyad,bilgi.tc;
return new;
ELSIF bilgi.sayac=45 THEN
update basvuran set odul='Plaket' where tc=new.islemtc;
RAISE NOTICE ' Ad : %  Soyad : %  TC : %  vatandaşımız 45 kez bağış yaparak PLAKET kazanmıştır.',bilgi.ad,bilgi.soyad,bilgi.tc;
return new;
else 
return new;
END IF;
END
$$
language plpgsql;

create trigger odultrig
after insert on islem 
for each row
execute procedure odulkontrol();


create function unitebul(kangrubu varchar(10) )
returns integer as $$
declare 
toplam integer;
islem_cur CURSOR FOR SELECT * from islem where kangr=kangrubu;
BEGIN
toplam:=0;
for satir in islem_cur loop
if satir.islemtipi='Kan Verme' then
toplam:=toplam+1;
elsif satir.islemtipi='Kan Alma' then
toplam := toplam-1;
end if;
end loop;
return toplam;
end;
$$
language plpgsql;

create or replace function kankontrol()
returns trigger as $$
DECLARE
unite integer;
begin
select unitebul(new.kangr) into unite;
IF new.islemtipi = 'Kan Alma' THEN
	IF unite>0 THEN
	return new;
	ELSE
	RAISE NOTICE ' % :  Kan grubu stokta yok..',new.kangr;
	return NULL;
	END IF;
ELSE
return new;
END IF;
END
$$
language plpgsql;

create trigger kantrig
before insert on islem
for each row
execute procedure kankontrol();
 
 CREATE VIEW COVIDLILER AS   --Çağırma: SELECT * FROM COVIDLILER
   SELECT TC, ad, soyad
   FROM BASVURAN
   WHERE covid='Geçirdi' AND
         dogumT > (CURRENT_DATE - INTERVAL '65' YEAR );
	