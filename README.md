# user-api
REST-API zur Verwaltung von Nutzern (Demo)

## Start der Anwendung
Die Anwendung kann aus ihrem Wurzelverzeichnis folgendermaßen gebaut und anschließend mit dem Profil "dev" gestartet werden
(In der Windows-Kommandozeile "./mvnw" durch "mvnw.cmd" ersetzen):
1. ```./mvnw clean install```
2. ```./mvnw spring-boot:run -D"spring-boot.run.profiles=dev"```

Anschließend ist die Anwendung unter localhost:8000 erreichbar.

Alternativ kann die Anwendung auch mit dem Profil "prod" gestartet werden. Die Anwendung ist dann unter localhost:9000 erreichbar

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
- vorname: darf nicht leer sein
- name: darf nicht leer sein
- email: muss einem typischen E-Mail-Format entsprechen