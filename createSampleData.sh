#!/bin/bash

if [ $1 == local ]; then
    api_url=localhost:8080
elif [ $1 == docker ]; then
    api_url=192.168.99.101:8080
elif [ $1 == kubernetes ]; then
    api_url=192.168.99.102:30002
fi

curl $api_url/car/create -d marke=Mercedes -d model=A-Klasse -d leistung=120 -d gewicht=1400 -d farbe=grau -d klasse=Limousine
curl $api_url/car/create -d marke=BMW -d model=M4 -d leistung=520 -d gewicht=2000 -d farbe=gruen -d klasse=Coupe
curl $api_url/car/create -d marke=VW -d model=Golf -d leistung=75 -d gewicht=1150 -d farbe=schwarz -d klasse=Limousine
curl $api_url/car/create -d marke=Audi -d model=A4 -d leistung=150 -d gewicht=1500 -d farbe=rot -d klasse=Kombi
curl $api_url/car/create -d marke=Smart -d model=ForTwo -d leistung=60 -d gewicht=850 -d farbe=schwarz -d klasse=Kleinwagen
curl $api_url/car/create -d marke=Porsche -d model="911 Turbo" -d leistung=550 -d gewicht=1950 -d farbe=schwarz -d klasse=Sportwagen -d motor_art=Benzin
curl $api_url/car/create -d marke=Smart -d model=ForTwo -d leistung=65 -d gewicht=850 -d farbe=silber -d klasse=Kleinwagen -d motor_art=Diesel

#JSON FORMAT
curl $api_url/car/create -H "Content-Type: application/json" --data '{
    "marke": "Audi",
    "model": "RS4",
    "gewicht": 1400,
    "leistung": 450,
    "drehmoment": 600,
    "farbe": "grau",
    "tueren": 5,
    "klasse": "Limousine"
}'

curl $api_url/car/create -H "Content-Type: application/json" --data '{
    "marke": "VW",
    "model": "Passat",
    "gewicht": 1400,
    "leistung": 120,
    "drehmoment": 230,
    "farbe": "grau",
    "tueren": 0,
    "klasse": "Limousine",
    "motor_art": "diesel"
}'

