# Guestbook9001

## Installation

Die Datei `apache/httpd.conf` muss die Standardkonfigurationsdatei von Apache ersetzten. Die Konfigurationsdatei geht
davon aus, dass die Apache Installation diejenige von XAMPP ist.

## Dokumentation

### Session-Fixation

Um Session-Fixation-Angriffe zu verhindern, mussten wir in unserer JSP Applikation vor dem Login, sowie nach dem Logout,
die aktuelle Session des Benutzers invalidieren. Dies geschieht mit einem Aufruf der
`javax.servlet.http.HttpSession.invalidate()`-Methode. Nach diesem Aufruf, gibt der nächste aufruf auf
`javax.servlet.http.HttpServletRequest.getSession(true)` eine neue Session zurück und weist diese dem Benutzer zu.

### CSRF

Um CSRF-Angriffe zu verhindern, haben wir dem Dispatcher einen Filter angefügt, welcher alle POST-Requests überprüft.

Um einen CSRF-Angriff zu verhindern, müssen wir sichergehen, dass der Benutzer selbst, von dem von uns Erstellten
Formular aus, die POST-Request ausgelöst hat.
Dazu verwenden wir einen CSRF-Token.

Ein CSRF-Token ist eine eindeutige ID, welche beim Aufruf des Formulars per GET-Request diesem Formular zugewiesen wird.
Um einen CSRF-Angriff erfolgreich durchzuführen, müsste der Angreifer den aktuellen CSRF-Token des Benutzer kennen.

In unserem Fall ist der CSRF-Token eine UUID (universally unique identifier) nach RFC 4122 Standard. Das generieren
dieser UUID in Java ist sehr trivial.
Die Java Standard-Library bietet eine Klasse `java.util.UUID`, welche mithilfe eines kryptografisch sicheren
Zufallszahlengenerators eine UUID generiert.

Bei unserer Implementation wird beim ersten Aufruf einer Seite eines eingeloggten Benutzer eine UUID als CSRF-Token
generiert.
Dieser CSRF-Token wird dann in die Session gespeichert und als verstecktes Input-Feld ins Formular eingefügt.
Wenn eine POST-Request eines eingeloggten Benutzers erfolgt, wird überprüft, ob der CSRF-Token des Formulars mit dem
CSRF-Token aus der Session übereinstimmt, wenn dies nicht der Fall ist wird ein `HTTP 403`-Fehler zurückgegeben.
