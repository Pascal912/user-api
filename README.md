# user-api
REST-API zur Verwaltung von Nutzern (Demo)

## Start der Anwendung

### ohne Docker

Die Anwendung kann aus dem Wurzelverzeichnis folgendermaßen gebaut und anschließend mit dem Profil "dev" gestartet werden
(In der Windows-Kommandozeile "./mvnw" durch "mvnw.cmd" ersetzen):
1. ```./mvnw clean package```
2. ```./mvnw spring-boot:run -D"spring-boot.run.profiles=dev"```

Anschließend ist die Anwendung unter localhost:8000 erreichbar.

Alternativ kann die Anwendung auch mit dem Profil "prod" gestartet werden. Die Anwendung ist dann unter localhost:9000 erreichbar

### mit Docker

Zuerst muss analog zum Start ohne Docker aus dem Wurzelverzeichnis heraus eine ausführbare Jar-Datei gebaut werden:

```./mvnw clean package```

Aus dem Wurzelverzeichnis muss folgender Befehl zum Erstellen eines Images ausgeführt werden:

```docker build -t user-api .```

Um den Container mit dem Profil "dev" zu starten, muss der folgende Befehl ausgeführt werden:

```docker run -e "SPRING_PROFILES_ACTIVE=dev" -p8000:8000 user-api```

Alternativ kann auch das Profil "prod" aktiviert werden und entsprechend Port 9000 exposed werden. Hierzu muss "dev" durch "prod" und jeweils 8000 durch 9000 ersetzt werden.


## Verfügbare Endpunkte
- GET /api/user
  - Liste aller Benutzer abrufen
  - per optionalem Request-Parameter kann nach einem bestimmten Vornamen gefiltert werden (Case-insentitive)
  - Beispiel: api/user?vorname=pascal
- POST /api/user
  - neuen Benutzer hinzufügen
  - per JSON-Objekt im Request-Body
  - Beispiel: {"name":"Jahn", "vorname":"Pascal", "email": "pascaljahn.be@gmail.com"}
- GET /api/user/{id}
  - einen bestimmten Benutzer abrufen
  - {id} im Pfad durch eine konkrete ID ersetzen
  - Beispiel: /api/user/1
- PUT /api/user/{id}
  - einen Benutzer aktualisieren
  - per JSON-Objekt im Request-Body
  - Beispiel: {"name":"Jahn", "vorname":"Pascal", "email": "pascaljahn.be@gmail.com"}
- DELETE /api/user/{id}
  - einen Benutzer löschen
  - {id} im Pfad durch eine konkrete ID ersetzen
  - Beispiel: /api/user/1
- Beispiel für einen zurückgegebenen Benutzer im Response-Body:
  - {"id":1, "name":"Jahn", "vorname":"Pascal", "email": "pascaljahn.be@gmail.com"}


## Validierung
Beim Übermitteln von Benutzerdaten im Request-Body gelten für die einzelnen Felder folgende Regeln:
- vorname: darf nicht leer sein (auch nicht nur aus Leerzeichen bestehen)
- name: darf nicht leer sein (auch nicht nur aus Leerzeichen bestehen)
- email: muss einem typischen E-Mail-Format entsprechen