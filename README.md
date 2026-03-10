# ta105 - 105组TA招聘系统

## Stack
- OpenJDK 25.0.2 (or any Java 17+)
- Tomcat 11
- Servlet/JSP (Jakarta namespace)
- Maven WAR project

## Project Layout
```text
.
├── pom.xml
└── src/
    └── main/
        ├── java/
        │   └── com/example/
        │       ├── filter/EncodingFilter.java
        │       ├── model/User.java
        │       └── servlet/HelloServlet.java
        ├── resources/
        └── webapp/
            ├── index.jsp
            └── WEB-INF/
                └── views/
                    ├── error.jsp
                    └── hello.jsp
```

## Notes
- Annotation-only registration (`@WebServlet`, `@WebFilter`).
- No `web.xml`.
- Default entry redirects to `/hello`.

## Build
Maven is required (`mvn`).
```bash
mvn clean package
```

WAR output:
```text
target/ta105.war
```

## Deploy To External Tomcat 11
```bash
cp target/ta105.war "$CATALINA_BASE/webapps/"
"$CATALINA_BASE/bin/startup.sh"
```

If you use `CATALINA_HOME` instead:
```bash
cp target/ta105.war "$CATALINA_HOME/webapps/"
"$CATALINA_HOME/bin/startup.sh"
```

## Verify
- `http://localhost:8080/ta105/`
- `http://localhost:8080/ta105/hello`
- `http://localhost:8080/ta105/hello?name=Nick`

## Stop Tomcat
```bash
"$CATALINA_BASE/bin/shutdown.sh"
```
or
```bash
"$CATALINA_HOME/bin/shutdown.sh"
```
