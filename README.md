Bauunternehmen:

Um das entwickelte Projekt ausführen zu können sind 2 Schritte notwendig. 

1. Datenbankerstellung: zur Datenbankerstellung liegt unter src/main/resources ein docker-file ,
mit welchem man die benötigte Datenbank einfach erstellen kann. Dazu muss zunächst docker & docker-compose 
installiert sein. Als Nächstes muss man sich mit der Konsole zum resource-Verzeichnis navigieren. 
Dort kann man mittels des Kommandos "docker-compose up" die Datenbank erstellen. Falls man nicht diesen 
Weg wählen möchte und sich die Angaben unterscheiden muss man in folgenden Dateien die URL, User und Password anpassen:
flyway.properties & persistence.xml 

2. Man muss die Datenbankmigration mittels flyway anstoßen. Dafür mit der Konsole im Directory "bauunternehmen" folgenden
Befehl eingeben: "mvn clean compile flyway:clean flyway:migrate -Dflyway.configFiles=flyway.properties".
