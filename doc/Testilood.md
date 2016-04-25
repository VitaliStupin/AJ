# Testilood

**DUMonitor**

Versioon 1.0, 24.04.2016

Tellija: Riigi Infosüsteemi Amet

Täitja: Degeetia OÜ, Mindstone OÜ

![EL struktuurifondid](EL_struktuuri-_ja_investeerimisfondid_horisontaalne.jpg)

## Dokumendi ajalugu

| Versioon | Kuupäev    | Autor      | Märkused
|----------|------------|------------|----------------------------------------------
| 1.0      | 24.04.2016 | Ivo Mehide | Esimene versioon

## Sissejuhatus

Käesolevas dokumendis kirjeldaatkse DUMonitor tarkvara integratsiooni- ja koormustestide testilood.

## Testilood

### Testilugude koond

#### Integratsioonitestid

Automaatsed testid:

* Filtri komponent: X-teelt saabunud päringu vastuvõtt ja vastuse tagastamine - korrektne päring
* Filtri komponent: X-teelt saabunud päringu vastuvõtt ja vastuse tagastamine - vigane päring
* Filtri komponent: päringu ja vastuse logimine
* Andmesalvestaja komponent: URL kaudu kasutusteabe logimise sõnumi vastuvõtmine ja salvestamine
* Andmesalvestaja komponent: REST liidese kaudu kasutusteabe logimise sõnumi vastuvõtmine ja salvestamine
* Andmesalvestaja komponent: REST liidese kaudu logist otsimine ja tulemuse tagastamine
* Andmesalvestaja komponent: X-tee liidese kaudu logist otsimine ja tulemuse tagastamine
* Andmesalvestaja komponent: X-tee liidese kaudu ajahorisondi päringu vastuvõtmine ja tulemuse tagastamine (hetkel realiseerimata - ootel RIA otsuse taga)

Käsitsi läbi viidavad testid:

* Andmesalvestaja komponent: veebiliidese kaudu logist otsimine ja tulemuse tagastamine
* Esitamise testrakenduse komponent: veebiliidese kaudu logist otsimine ja tulemuse tagastamine
* Eesti.ee komponent: MISP2 veebiliidese kaudu logist otsimine ja tulemuse tagastamine

#### Koormustestid
 
* Filtri komponendi koormustest
* Andmesalvestaja komponendi REST logimisliidese koormustest
* Andmesalvestaja komponendi REST päringuliidese koormustest
* Andmesalvestaja komponendi X-tee päringuliidese koormustest

### Testilugude kirjeldused

#### 1. Filtri komponent: X-teelt saabunud päringu vastuvõtt ja vastuse tagastamine - korrektne päring

* Testiloo nimetus:	Filtri komponent: X-teelt saabunud päringu vastuvõtt ja vastuse tagastamine - korrektne päring
* Testiloo indeks:	1
* Viide kasutusloole:	
* Lühikirjeldus: 	Tehakse päring eraldusfiltri komponendi andmekogu teenust vahendavale URLile ja veendutakse saadud vastuse korrektsuses.
* Omadused, mida testitakse:	Eraldusfiltri komponendi päringu vahendamine
* Omadused, mida ei testita:	Eraldusfiltri komponendi päringu logimine
* Lähtetingimused:	-
* Oletatavad vead:
  1. Päring edastatakse andmekogule vigaselt või ei edastata üldse
  2. Andmekogu poolt tagastatud päringu vastus tagastatakse vigaselt või ei tagastata üldse
* Testi jada kirjeldus:	
  1. Tehakse test andmekogu teenuse getPersonData päring filtri komponendi andmekogu teenust vahendavale URLile.
  2. Filter loeb päringu sisse ja teeb sama sisuga päringu testandmekogule.
  3. Testandmekogu kontrollib saabunud päringu sisu korrektsust (kõikide väljade vastavust algses päringus toodule) ning tagastab selle baasilt kas korrektse päringu vastuse või veavastuse.
  4. Filter loeb päringu vastuse sisse ja tagastab selle.
  6. Testiskript veendub et tegemist pole veavastusega ning et vastuses toodud andmeväljade sisu vastab test andmekogu poolt saadetule.
* Käivitamise ja läbiviimise skript:	filterReqResp
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:	Testi käigus tekkinud vigade korral test katkestatakse ning testi tulemus loetakse negatiivseks.
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:
  Test loetakse positiivseks, kui:
  - Testandmekogu poolt vastuvõetud päring on SOAP sõnum, milles kõigi X-tee päisväljade väärtused langevad kokku päringus esitatutega.
  - Filtri poolt tagastatud vastus on SOAP sõnum, milles kõigi X-tee päisväljade väärtused langevad kokku testandmekogu poolt tagastatutega.
  - Filtri poolt tagastatud vastus on ""multipart/related"" ning sisaldab manust, mille ID langeb kokku testandmekogu poolt tagastatuga
  - Filtri poolt tagastatud vastuse sisus toodud andmeväljade väärtused langevad kokku testandmekogu poolt tagastatutele
  Kõigil muudel juhtudel loetakse test ebaõnnestunuks.
* Testi läbiviimise meetodi kirjeldus:	Täisautomaatne test, mis käivitatakse vastava skriptiga.


#### 2. Filtri komponent: X-teelt saabunud päringu vastuvõtt ja vastuse tagastamine - vigane päring

* Testiloo nimetus:	Filtri komponent: X-teelt saabunud päringu vastuvõtt ja vastuse tagastamine - vigane päring
* Testiloo indeks:	2
* Viide kasutusloole:	
* Lühikirjeldus: 	Tehakse vigaseid andmeis sisaldav päring eraldusfiltri komponendi andmekogu teenust vahendavale URLile ja veendutakse vastuseks saadud SOAP exceptioni korrektsuses. Eesmärgiks on veenduda, et andmekogu poolt tagastatav SOAP exception jõuab muutmatult läbi filtri päringu tegijale.
* Omadused, mida testitakse:	Eraldusfiltri komponendi päringu vahendamine
* Omadused, mida ei testita:	Eraldusfiltri komponendi päringu logimine
* Lähtetingimused:	-
* Oletatavad vead:	Viga sisaldav andmekogu päringuvastus tagastatakse filtri poolt muutunud kujul
* Testi jada kirjeldus:
  1. Tehakse test andmekogu teenuse getPersonData päring filtri komponendi andmekogu teenust vahendavale URLile. Päringus on esitatud vigased andmed.
  2. Filter loeb päringu sisse ja teeb sama sisuga päringu testandmekogule.
  3. Testandmekogu kontrollib saabunud päringu sisu korrektsust, tuvastab probleemi andmetes ning tagastab veavastuse (SOAP exception).
  4. Filter loeb päringu vastuse sisse ja tagastab selle.
  5. Testiskript veendub, et tegemist on SOAP exception vastusega ning veakood langeb kokku andmekogu poolt antuga.
* Käivitamise ja läbiviimise skript:	filterReqRespInvalid
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:	Testi käigus tekkinud vigade korral test katkestatakse ning testi tulemus loetakse negatiivseks.
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:	
  Test loetakse positiivseks, kui:
  - Filtri poolt tagastatud vastus on SOAP exception sõnum, mille ""faultcode"" element langeb kokku testandmekogu poolt tagastatutega.
  - Filtri poolt tagastatud vastus on ""text/xml"" ning HTTP vastuskood on 400
  Kõigil muudel juhtudel loetakse test ebaõnnestunuks.
* Testi läbiviimise meetodi kirjeldus:	Täisautomaatne test, mis käivitatakse vastava skriptiga.


#### 3. Filtri komponent: päringu ja vastuse logimine

* Testiloo nimetus:	Filtri komponent: päringu ja vastuse logimine
* Testiloo indeks:	3
* Viide kasutusloole:	
* Lühikirjeldus: 	Tehakse päring eraldusfiltri komponendi andmekogu teenust vahendavale URLile ja veendutakse päringu ja selle vastuse logimises andmesalvestaja komponenti.
* Omadused, mida testitakse:	Eraldusfiltri komponendi päringu logimine
* Omadused, mida ei testita:	Eraldusfiltri komponendi päringu vahendamine
* Lähtetingimused:	-
* Oletatavad vead:	
  1. Päringut ei logita andmesalvestaja komponenti või logitakse puudulikult.
  2. Päringu vastust ei logita andmesalvestaja komponenti või logitakse puudulikult.
* Testi jada kirjeldus:	
  1. Tehakse test andmekogu teenuse getPersonData päring filtri komponendi andmekogu teenust vahendavale URLile. Päringule genereeritakse juhuslik X-tee päringu ID.
  2. Filter loeb päringu sisse ja teeb sama sisuga päringu testandmekogule. Parallelselt käivitab päringu logimise andmesalvestaja komponenti.
  3. Testandmekogu kontrollib saabunud päringu sisu korrektsust (kõikide väljade vastavust algses päringus toodule) ning tagastab selle baasilt kas korrektse päringu vastuse või veavastuse.
  4. Filter loeb päringu vastuse sisse ja tagastab selle. Paralleelselt käivitab päringu vastuse logimise andmesalvestaja komponenti.
  6. Tehakse JDBC päring andmesalvestaja andmebaasi ning loetakse sisse kõik kirjed, mille ""xroadrequestid"" atribuudi väärtus langeb kokku sammus 1 tekitatule.
  7. Veendutakse, et tagastati päringu ning päringu vastuse logikirjed ning et nende logikirjete väljade väärtused langevad kokku päringu ja päringu vastuse omadega.
* Käivitamise ja läbiviimise skript:	filterLogging
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:	Testi käigus tekkinud vigade korral test katkestatakse ning testi tulemus loetakse negatiivseks.
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:	
  Test loetakse positiivseks, kui:
  - Andmebaasist tagastati täpselt kaks kirjet
  - Ühes kirjes vastab ""actioncode"" atribuudi väärtus päringu poolt logitule ning teises kirjes vastab selle atribuudi väärtus päringu vastuse poolt logitule
  - Ülejäänud kirjete atribuudid langevad kokku päringu ja päringu vastuse poolt logitutele (need on mõlemal juhul samad)
* Testi läbiviimise meetodi kirjeldus:	Täisautomaatne test, mis käivitatakse vastava skriptiga.


#### 4. Andmesalvestaja komponent: URL kaudu kasutusteabe logimise sõnumi vastuvõtmine ja salvestamine

* Testiloo nimetus:	Andmesalvestaja komponent: URL kaudu kasutusteabe logimise sõnumi vastuvõtmine ja salvestamine
* Testiloo indeks:	4
* Viide kasutusloole:	
* Lühikirjeldus: 	Tehakse HTTP GET päring andmesalvestaja komponendi REST logimisliidesele, kus logitav info on URLi parameetrites, ning veendutakse päringu logimises.
* Omadused, mida testitakse:	Andmesalvestaja komponendi REST logimisliidese GET-päringutega logimine
* Omadused, mida ei testita:	Andmesalvestaja komponendi REST logimisliidese POST-päringutega logimine
* Lähtetingimused:	-
* Oletatavad vead:	Esitatud infot ei logita või logitakse puudulikult.
* Testi jada kirjeldus:	
  1. Tehakse HTTP GET päring andmesalvestaja logimise REST liidesele. Päringule genereeritakse juhuslik ""xroadrequestid"" parameetri väärtus.
  2. Andmesalvestaja logib päringu andmebaasi
  3. Tehakse JDBC päring andmesalvestaja andmebaasi ning loetakse sisse kõik kirjed, mille ""xroadrequestid"" atribuudi väärtus langeb kokku sammus 1 tekitatule.
  4. Veendutakse, et tagastati täpselt üks kirje ning et kirje atribuutide väärtused langevad kokku logimise päringus antutele.
* Käivitamise ja läbiviimise skript:	storageUrlStore
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:	Testi käigus tekkinud vigade korral test katkestatakse ning testi tulemus loetakse negatiivseks.
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:	
  Test loetakse positiivseks, kui:
  - Andmebaasist tagastati täpselt üks logikirje
  - Selle kirje atribuutide väärtused langevad kokku logipäringus antutele
* Testi läbiviimise meetodi kirjeldus:	Täisautomaatne test, mis käivitatakse vastava skriptiga.


#### 5. Andmesalvestaja komponent: REST liidese kaudu kasutusteabe logimise sõnumi vastuvõtmine ja salvestamine

* Testiloo nimetus:	Andmesalvestaja komponent: REST liidese kaudu kasutusteabe logimise sõnumi vastuvõtmine ja salvestamine
* Testiloo indeks:	5
* Viide kasutusloole:	
* Lühikirjeldus: 	Tehakse HTTP POST päring andmesalvestaja komponendi REST logimisliidesele, kus logitav info on päringu kehas JSON struktuuris, ning veendutakse päringu logimises.
* Omadused, mida testitakse:	Andmesalvestaja komponendi REST logimisliidese POST-päringutega logimine
* Omadused, mida ei testita:	Andmesalvestaja komponendi REST logimisliidese GET-päringutega logimine
* Lähtetingimused:	-
* Oletatavad vead:	Esitatud infot ei logita või logitakse puudulikult.
* Testi jada kirjeldus:	
  1. Tehakse HTTP POST päring andmesalvestaja logimise REST liidesele, mille kehas on JSON struktuur logiatribuutidega. Päringule genereeritakse juhuslik ""xroadrequestid"" atribuudi väärtus.
  2. Andmesalvestaja logib päringu andmebaasi
  3. Tehakse JDBC päring andmesalvestaja andmebaasi ning loetakse sisse kõik kirjed, mille ""xroadrequestid"" atribuudi väärtus langeb kokku sammus 1 tekitatule.
  4. Veendutakse, et tagastati täpselt üks kirje ning et kirje atribuutide väärtused langevad kokku logimise päringus antutele.
* Käivitamise ja läbiviimise skript:	storageRestStore
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:	Testi käigus tekkinud vigade korral test katkestatakse ning testi tulemus loetakse negatiivseks.
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:	
  Test loetakse positiivseks, kui:
  - Andmebaasist tagastati täpselt üks logikirje
  - Selle kirje atribuutide väärtused langevad kokku logipäringus antutele
* Testi läbiviimise meetodi kirjeldus:	Täisautomaatne test, mis käivitatakse vastava skriptiga.


#### 6. Andmesalvestaja komponent: REST liidese kaudu logist otsimine ja tulemuse tagastamine

* Testiloo nimetus:	Andmesalvestaja komponent: REST liidese kaudu logist otsimine ja tulemuse tagastamine
* Testiloo indeks:	6
* Viide kasutusloole:	
* Lühikirjeldus: 	Andemsalvestaja komponendi REST logimisliidese abil salvestatakse logikirje ning seejärel otsitakse seda andmesalvestaja REST otsinguliidese kaudu. Veendutakse kirje leidumises.
* Omadused, mida testitakse:	Andmesalvestaja komponendi REST otsinguliidese toimimine
* Omadused, mida ei testita:	Andmesalvestaja komponendi REST logimisliidese toimimine
* Lähtetingimused:	-
* Oletatavad vead:	Otsing andmeatribuutide järgi ei toimi korrektselt - kas kirjeid ei leita või leitakse liiga palju.
* Testi jada kirjeldus:	
  1. Tehakse HTTP POST päring andmesalvestaja REST logimisliidesele, mille kehas on JSON struktuur logiatribuutidega. Päringule määratakse kindel ""xroadrequestid"" atribuudi väärtus.
  2. Andmesalvestaja logib päringu andmebaasi
  3. Andmesalvestaja REST otsinguliidese kaudu tehakse päring logitud kirje otsimiseks, kus parameetritena antakse kõik logitud kirje atribuutide väärtused, ajalise piiranguna kuupäevavahemik jooksvast kuupäevast homse kuupäevani, ning vastuste kirjete arvu piiranguna 1.
  4. Veendutakse, et tagastati täpselt üks kirje ning et kirje atribuutide väärtused langevad kokku logimise päringus antutele.
* Käivitamise ja läbiviimise skript:	storageRestQuery
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:	Testi käigus tekkinud vigade korral test katkestatakse ning testi tulemus loetakse negatiivseks.
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:	
  Test loetakse positiivseks, kui:
  - Andmebaasist tagastati täpselt üks logikirje
  - Selle kirje atribuutide väärtused langevad kokku logipäringus antutele
* Testi läbiviimise meetodi kirjeldus:	Täisautomaatne test, mis käivitatakse vastava skriptiga.


#### 7. Andmesalvestaja komponent: X-tee liidese kaudu logist otsimine ja tulemuse tagastamine

* Testiloo nimetus:	Andmesalvestaja komponent: X-tee liidese kaudu logist otsimine ja tulemuse tagastamine
* Testiloo indeks:	7
* Viide kasutusloole:	
* Lühikirjeldus: 	Andemsalvestaja komponendi REST logimisliidese abil salvestatakse logikirje ning seejärel otsitakse seda andmesalvestaja X-tee otsinguliidese kaudu. Veendutakse kirje leidumises.
* Omadused, mida testitakse:	Andmesalvestaja komponendi X-tee otsinguliidese toimimine
* Omadused, mida ei testita:	Andmesalvestaja komponendi REST logimise liidese toimimine
* Lähtetingimused:	-
* Oletatavad vead:	Otsing andmeatribuutide järgi ei toimi korrektselt - kas kirjeid ei leita või leitakse liiga palju.
* Testi jada kirjeldus:	
  1. Tehakse HTTP POST päring andmesalvestaja REST logimisliidesele, mille kehas on JSON struktuur logiatribuutidega. Päringule määratakse kindel ""xroadrequestid"" atribuudi väärtus.
  2. Andmesalvestaja logib päringu andmebaasi
  3. Andmesalvestaja X-tee otsinguliidese kaudu tehakse päring logitud kirje otsimiseks, kus päringusõnumi atribuutidena antakse kõik logitud kirje atribuutide väärtused, ajalise piiranguna kuupäevavahemik jooksvast kuupäevast homse kuupäevani, ning vastuste kirjete arvu piiranguna 1.
  4. Veendutakse, et tagastati täpselt üks kirje ning et kirje atribuutide väärtused langevad kokku logimise päringus antutele.
* Käivitamise ja läbiviimise skript:	storageXroadQuery
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:	Testi käigus tekkinud vigade korral test katkestatakse ning testi tulemus loetakse negatiivseks.
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:	
  Test loetakse positiivseks, kui:
  - Andmebaasist tagastati täpselt üks logikirje
  - Selle kirje atribuutide väärtused langevad kokku logipäringus antutele
* Testi läbiviimise meetodi kirjeldus:	Täisautomaatne test, mis käivitatakse vastava skriptiga.


#### 8. Andmesalvestaja komponent: veebiliidese kaudu logist otsimine ja tulemuse tagastamine

* Testiloo nimetus:	Andmesalvestaja komponent: veebiliidese kaudu logist otsimine ja tulemuse tagastamine
* Testiloo indeks:	8
* Viide kasutusloole:	
* Lühikirjeldus: 	Otsitakse andmesalvestaja veebiliidese kaudu logikirjet, mis lisati testi nr 7 täitmise käigus. Veendutakse kirje leidumises.
* Omadused, mida testitakse:	Andmesalvestaja komponendi sisekasutuse veebiliidese toimimine
* Omadused, mida ei testita:	Andmesalvestaja komponendi REST otsinguliidese toimimine
* Lähtetingimused:	Vahetult enne testi peab olema sooritatud test nr 7
* Oletatavad vead:	Otsing andmeatribuutide järgi ei toimi korrektselt - kirjeid ei leita.
* Testi jada kirjeldus:	
  1. Andmesalvestaja veebiliidese kaudu teostatakse otsing, näidates ära järgmised andmed (need langevad kokku testis nr 7 lisatud kirje andmetega):
    - Isikukood: 47101010033
    - Tegevuse avalik nimi: Get Person Data
    - Saaja avalik nimi: Test receiver
    - Saatja avalik nimi: Test AK
    - X-tee päringu id: 4894e35d-bf0f-44a6-867a-123456643291
    - Tegevuse tehniline kood: REQ:getPersonData.v1
    - Saaja tehniline kood: 10000001
    - Saatja tehniline kood: 20000001
    - Töötleja isikukood: EE12345678901
    - X-tee teenuse nimetus: getPersonData
    - Salvestamisaeg suurem kui: testi sooritamise kuupäev
    - Salvestamisaeg väiksem kui: testi sooritamise kuupäev
    - Piirangud: A
  2. Veendutakse, et tagastati vähemalt üks kirje ning et kuvatud kirje atribuutide väärtused langevad kokku otsinguvormile sisestatutega
* Käivitamise ja läbiviimise skript:	-
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:	Testi käigus tekkinud vigade korral test katkestatakse ning testi tulemus loetakse negatiivseks.
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:	
  Test loetakse positiivseks, kui:
  - Otsing tagastas vähemalt ühe kirje (aktsepteeriv on ka mitme kirje tagastamine, see situatsioon tekib juhul, kui testi nr 7 on käivitatud mitu korda)
  - Selle kirje atribuutide väärtused langevad kokku otsingus sisestatutega"
* Testi läbiviimise meetodi kirjeldus:	Käsitsi läbiviidav test.


#### 9. Esitamise testrakenduse komponent: veebiliidese kaudu logist otsimine ja tulemuse tagastamine

* Testiloo nimetus:	Esitamise testrakenduse komponent: veebiliidese kaudu logist otsimine ja tulemuse tagastamine
* Testiloo indeks:	9
* Viide kasutusloole:	
* Lühikirjeldus: 	Otsitakse esitamise testrakenduse kaudu logikirjet, mis lisati testi nr 7 täitmise käigus. Veendutakse kirje leidumises.
* Omadused, mida testitakse:	Esitamise testrakenduse veebiliidese toimimine
* Omadused, mida ei testita:	
* Lähtetingimused:	Vahetult enne testi peab olema sooritatud test nr 7
* Oletatavad vead:	Otsing andmeatribuutide järgi ei toimi korrektselt - kirjeid ei leita.
* Testi jada kirjeldus:	
  1. Esitamise testrakenduse veebiliidese kaudu teostatakse otsing, näidates ära järgmised andmed (need langevad kokku testis nr 7 lisatud kirje andmetega):
    - Isikukood: 47101010033
    - Tegevuse avalik nimi: Get Person Data
    - Saaja avalik nimi: Test receiver
    - Saatja avalik nimi: Test AK
    - X-tee päringu id: 4894e35d-bf0f-44a6-867a-123456643291
    - Tegevuse tehniline kood: REQ:getPersonData.v1
    - Saaja tehniline kood: 10000001
    - Saatja tehniline kood: 20000001
    - Töötleja isikukood: EE12345678901
    - X-tee teenuse nimetus: getPersonData
    - Salvestamisaeg suurem kui: testi sooritamise kuupäev
    - Salvestamisaeg väiksem kui: testi sooritamise kuupäev    
    - Piirangud: A
  2. Veendutakse, et tagastati vähemalt üks kirje ning et kuvatud kirjete atribuutide väärtused langevad kokku otsinguvormile sisestatutega
* Käivitamise ja läbiviimise skript:	-
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:	Testi käigus tekkinud vigade korral test katkestatakse ning testi tulemus loetakse negatiivseks.
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:	
  Test loetakse positiivseks, kui:
  - Otsing tagastas vähemalt ühe kirje (aktsepteeriv on ka mitme kirje tagastamine, see situatsioon tekib juhul, kui testi nr 7 on käivitatud mitu korda)
  - Selle kirje atribuutide väärtused langevad kokku otsingus sisestatutega
* Testi läbiviimise meetodi kirjeldus:	Käsitsi läbiviidav test.


#### 10. Eesti.ee komponent: MISP2 veebiliidese kaudu logist otsimine ja tulemuse tagastamine

* Testiloo nimetus:	Eesti.ee komponent: MISP2 veebiliidese kaudu logist otsimine ja tulemuse tagastamine
* Testiloo indeks:	10
* Viide kasutusloole:	
* Lühikirjeldus: 	Otsitakse esitamise testrakenduse kaudu logikirjet, mis lisati testi nr 7 täitmise käigus. Veendutakse kirje leidumises.
* Omadused, mida testitakse:	Eesti.ee xforms rakenduse toimimine
* Omadused, mida ei testita:	
* Lähtetingimused:	Vahetult enne testi peab olema sooritatud test nr 7
* Oletatavad vead:	Otsing andmeatribuutide järgi ei toimi korrektselt - kirjeid ei leita.
* Testi jada kirjeldus:	
  1. Sisenetakse MISP2 keskkonda MARI-LIIS MÄNNIK test ID-kaardiga.
  2. Käivitatakse teenus findUsage.
  3. Veendutakse, et tagastati vähemalt üks kirje ning kirjel on esitatud järgmine info:
    - Tegevus: Get Person Data
    - Saaja: Test receiver
    - X-tee päringu id: 4894e35d-bf0f-44a6-867a-123456643291
    - Kuupäev: testi sooritamise kuupäev
* Käivitamise ja läbiviimise skript:	-
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:	Testi käigus tekkinud vigade korral test katkestatakse ning testi tulemus loetakse negatiivseks.
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:	
  Test loetakse positiivseks, kui:
  - Otsing tagastas vähemalt ühe kirje (aktsepteeriv on ka mitme kirje tagastamine, see situatsioon tekib juhul, kui testi nr 7 on käivitatud mitu korda)
  - Selle kirje atribuutide väärtused langevad kokku otsingus sisestatutega
* Testi läbiviimise meetodi kirjeldus:	Käsitsi läbiviidav test.


#### 11. Filtri komponendi koormustest

* Testiloo nimetus:	Filtri komponendi koormustest
* Testiloo indeks:	11
* Viide kasutusloole:	
* Lühikirjeldus: 	Tehakse paralleelsed päringud test andmekogu teenusele ja peale seda eraldusfiltri komponendi andmekogu teenust vahendavale URLile. Saadud tulemuste baasilt leitakse keskmised päringuajad otse test andmekogu teenuse vastu ning filtri vastu. Päringuaegade vahe annab filtri poolt kulutatava täiendava keskmise aja.
* Omadused, mida testitakse:	Eraldusfiltri komponendi jõudlus
* Omadused, mida ei testita:	Eraldusfiltri logimise funktsionaalsuse jõudlus
* Lähtetingimused:	-
* Oletatavad vead:	Filtri poolt kulutatav täiendav keskmine aeg on liiga suur (suurem kui nõutav 1ms).
* Testi jada kirjeldus:	
  1. Käivitatakse 50 paralleelset lõime päringute tegemiseks. Lõimed töötavad üksteisest sõltumatult.
  2. Iga lõim teeb päringu test andmekogu teenusele ja loeb vastuse. Registreeritakse aeg, mis kulub päringu tegemisest vastuse sisselugemiseni.
  3. Iga lõim teeb päringu eraldusfiltri komponendi andmekogu teenust vahendavale URLile ja loeb vastuse. Registreeritakse aeg, mis kulub päringu tegemisest vastuse sisselugemiseni.
  4. Iga lõim täidab samme 2 ja 3 järjest 10 korda.
  5. Kui kõik lõimed on oma töö lõpetanud, arvutatakse mõlema päringu keskmised täitmise ajad.
  6. Mõlema päringu keskmise täitmisaja vahe annab filtri poolt kulutatava täiendava keskmise aja.
* Käivitamise ja läbiviimise skript:	filterLoad
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:	Testi käigus tekkinud vigade korral test katkestatakse ning testi tulemus loetakse negatiivseks.
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:	Test loetakse positiivseks, kui filtri poolt kulutatav täiendav keskmine aeg on väiksem või võrdne 1ms. Vastasel korral loetakse test ebaõnnestunuks.
* Testi läbiviimise meetodi kirjeldus:	Täisautomaatne test, mis käivitatakse vastava skriptiga.


#### 12. Andmesalvestaja komponendi REST logimisliidese koormustest

* Testiloo nimetus:	Andmesalvestaja komponendi REST logimisliidese koormustest
* Testiloo indeks:	12
* Viide kasutusloole:	
* Lühikirjeldus: 	Tehakse paralleelsed päringud andmesalvestaja komponendi REST logimisliidesele ja fikseeritakse nende keskmine täitmise aeg.
* Omadused, mida testitakse:	Andmesalvestaja komponendi REST logimisliidese jõudlus
* Omadused, mida ei testita:	Andmesalvestaja komponendi REST logimisliidese GET päringute jõudlus
* Lähtetingimused:	-
* Oletatavad vead:	Päringute täitmise jõudlus on liiga väike - keskmine päringu täitmise aeg on suurem kui 10ms.
* Testi jada kirjeldus:	
  1. Käivitatakse 50 paralleelset lõime päringute tegemiseks. Lõimed töötavad üksteisest sõltumatult.
  2. Iga lõim teeb päringu andmesalvestaja komponendi REST logimisliidesele. Registreeritakse aeg, mis kulub päringu tegemisest vastuse sisselugemiseni.
  3. Iga lõim täidab eelmist sammu järjest 10 korda.
  4. Kui kõik lõimed on oma töö lõpetanud, arvutatakse päringute keskmine täitmise aeg.
* Käivitamise ja läbiviimise skript:	storageRestStoreLoad
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:	Testi käigus tekkinud vigade korral test katkestatakse ning testi tulemus loetakse negatiivseks.
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:	Test loetakse positiivseks, kui päringute täitmise keskmine aeg on väiksem või võrdne 10ms. Vastasel korral loetakse test ebaõnnestunuks.
* Testi läbiviimise meetodi kirjeldus:	Täisautomaatne test, mis käivitatakse vastava skriptiga.


#### 13. Andmesalvestaja komponendi REST päringuliidese koormustest

* Testiloo nimetus:	Andmesalvestaja komponendi REST päringuliidese koormustest
* Testiloo indeks:	13
* Viide kasutusloole:	
* Lühikirjeldus: 	Tehakse paralleelsed päringud andmesalvestaja komponendi REST päringuliidesele ja fikseeritakse nende keskmine täitmise aeg.
* Omadused, mida testitakse:	Andmesalvestaja komponendi REST päringuliidese jõudlus
* Omadused, mida ei testita:	-
* Lähtetingimused:	-
* Oletatavad vead:	Päringute täitmise jõudlus on liiga väike - keskmine päringu täitmise aeg on suurem kui 10ms.
* Testi jada kirjeldus:	
  1. Käivitatakse 50 paralleelset lõime päringute tegemiseks. Lõimed töötavad üksteisest sõltumatult.
  2. Iga lõim teeb päringu andmesalvestaja komponendi REST päringuliidesele. Registreeritakse aeg, mis kulub päringu tegemisest vastuse sisselugemiseni.
  3. Iga lõim täidab eelmist sammu järjest 10 korda.
  4. Kui kõik lõimed on oma töö lõpetanud, arvutatakse päringute keskmine täitmise aeg.
* Käivitamise ja läbiviimise skript:	storageRestQueryLoad
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:	Testi käigus tekkinud vigade korral test katkestatakse ning testi tulemus loetakse negatiivseks.
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:	Test loetakse positiivseks, kui päringute täitmise keskmine aeg on väiksem või võrdne 10ms. Vastasel korral loetakse test ebaõnnestunuks.
* Testi läbiviimise meetodi kirjeldus:	Täisautomaatne test, mis käivitatakse vastava skriptiga.


#### 14. Andmesalvestaja komponendi X-tee päringuliidese koormustest

* Testiloo nimetus:	Andmesalvestaja komponendi X-tee päringuliidese koormustest
* Testiloo indeks:	14
* Viide kasutusloole:	
* Lühikirjeldus: 	Tehakse paralleelsed päringud andmesalvestaja komponendi X-tee päringuliidesele ja fikseeritakse nende keskmine täitmise aeg.
* Omadused, mida testitakse:	Andmesalvestaja komponendi X-tee päringuliidese jõudlus
* Omadused, mida ei testita:	-
* Lähtetingimused:	-
* Oletatavad vead:	Päringute täitmise jõudlus on liiga väike - keskmine päringu täitmise aeg on suurem kui 10ms.
* Testi jada kirjeldus:	
  1. Käivitatakse 50 paralleelset lõime päringute tegemiseks. Lõimed töötavad üksteisest sõltumatult.
  2. Iga lõim teeb päringu andmesalvestaja komponendi X-tee päringuliidesele. Registreeritakse aeg, mis kulub päringu tegemisest vastuse sisselugemiseni.
  3. Iga lõim täidab eelmist sammu järjest 10 korda.
  4. Kui kõik lõimed on oma töö lõpetanud, arvutatakse päringute keskmine täitmise aeg.
* Käivitamise ja läbiviimise skript:	storageXroadQueryLoad
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:	Testi käigus tekkinud vigade korral test katkestatakse ning testi tulemus loetakse negatiivseks.
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:	Test loetakse positiivseks, kui päringute täitmise keskmine aeg on väiksem või võrdne 10ms. Vastasel korral loetakse test ebaõnnestunuks.
* Testi läbiviimise meetodi kirjeldus:	Täisautomaatne test, mis käivitatakse vastava skriptiga.
