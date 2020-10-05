# CarService CRUD REST API
## Beschreibung
Mit dieser API kann man sehr einfach Datensätze für Autos erstellen, ändern, listen und löschen.
## Setup
- Download the git repo `` git clone https://github.com/LukasLJL/CarService.git`` 
- Importiere das Maven Projekt in deine IDE am besten Intellij
- In der IDE das Projekt starten
- Die API sollte auf ``http://localhost:8080/car/`` laufen
- *Um das Testen einfacher zu machen, liegt im Projekt Verzeichnis eine ``createSampleData.cmd`` or ``createSampleData.sh``
mit diesem Script kannst du einfach Test-Daten erstellen.*
## How to use
Ein Car kann folgenden Eigenschaften haben:
- marke
- modell
- gewicht
- leistung
- drehmoment
- farbe
- tueren
- klasse
- motor_art
---
### POST
Bei POST kann man alle Eigenschaften vergeben wenn man möchte.\
Folgende Angaben sind Pflicht:\
marke, modell, leistung, gewicht, farbe\
POST -> ``localhost:8080/car/create``\
Beispiel commands:
- ``curl localhost:8080/car/create -d marke=Mercedes -d modell=A-Klasse -d leistung=120 -d gewicht=1400 -d farbe=grau -d klasse=Limousine``
- ``curl "localhost:8080/car/create?marke=Mercedes&modell=A-Klasse&leistung=120&gewicht=1400&farbe=grau&klasse=Limousine"``

---
### GET
GET gibt in dieser API alles als JSON zurück.\
Man kann sich alle Fahrzeuge ausgeben lassen oder auch einzelene.\
Um alle anzuzeigen:\
GET -> ``localhost:8080/car/list``\
Um sich einzelene anzuzeigen:\
GET -> ``localhost:8080/car/list/id``\
Beispiel commads:
- ``curl localhost:8080/car/list | json_pp``
- ``curl localhost:8080/car/list/0 | json_pp``

---
### PUT
Mit PUT kann man alle Eigenschaften eines CARs ändern wenn man möchte.\
Wenn man gewisse Eigenschaften nicht neu stetzt, werden diese auch nicht bearbeitet und\
die alten Werte bleiben erhalten.\
**ES MUSS IMMER DIE ID MIT ÜBRGEBEN WERDEN**\
PUT -> ``localhost:8080/car/edit``\
Beispiel commands:
- ``curl -X PUT "localhost:8080/car/edit?id=0&drehmoment=800"``
- ``curl -X PUT localhost:8080/car/edit -d id=0 -d drehmoment=700``

---
### DELETE
Mit DELETE kann man CAR Objekte löschen\
DELETE -> ``localhost:8080/car/delete``\
Beispeiel commands:
- ``curl -X DELETE "localhost:8080/car/delete?id=1"``
- ``curl -X DELETE localhost:8080/car/delete -d id=1``
