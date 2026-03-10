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

## macOS 从零安装并运行（Homebrew）
### 1) 安装依赖
```bash
brew update
brew install openjdk maven tomcat
```

### 2) 配置 Java（zsh）
```bash
sudo ln -sfn /opt/homebrew/opt/openjdk/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk.jdk
echo 'export PATH="/opt/homebrew/opt/openjdk/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc
```

### 3) 验证安装
```bash
java -version
mvn -version
"$(brew --prefix tomcat)/bin/version.sh"
```

### 4) 构建项目
```bash
cd /Users/n1ck/fucksoftware/TA_recruitment_system
mvn clean package
```


### 5) 部署到 Tomcat 11
```bash
cp target/ta105.war "$(brew --prefix tomcat)/libexec/webapps/"
```

### 6) 启动 Tomcat
前台运行（便于看日志）：
```bash
"$(brew --prefix tomcat)/bin/catalina" run
```

或后台服务：
```bash
brew services start tomcat
```

### 7) 访问验证
- `http://localhost:8080/ta105/`
- `http://localhost:8080/ta105/hello`
- `http://localhost:8080/ta105/hello?name=Nick`

### 8) 停止 Tomcat
如果是前台 `catalina run`：`Ctrl + C`

如果是后台服务：
```bash
brew services stop tomcat
```

## Build
```bash
mvn clean package
```

WAR output:
```text
target/ta105.war
```

## Deploy To External Tomcat 11
```bash
cp target/ta105.war "$(brew --prefix tomcat)/libexec/webapps/"
"$(brew --prefix tomcat)/bin/catalina" start
```

## Verify
- `http://localhost:8080/ta105/`
- `http://localhost:8080/ta105/hello`
- `http://localhost:8080/ta105/hello?name=Nick`

## Stop Tomcat
```bash
"$(brew --prefix tomcat)/bin/catalina" stop
```
