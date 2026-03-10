# ta105 - 105组TA招聘系统

## Stack
- OpenJDK 25.0.2 (or any Java 17+)
- Tomcat 11
- Servlet API (Jakarta) + 原生 HTML/CSS/JS
- Maven WAR project

## Architecture
- 单 WAR 同源部署，前后端分离：
- 前端：纯静态 `index.html` + `static/js` + `static/css`（不使用 JSP）
- 后端：Servlet 仅提供 JSON API（`/api/v1/*`）
- 注解注册：`@WebServlet`、`@WebFilter`，不使用 `web.xml`

## Project Layout
```text
.
├── pom.xml
└── src/
    └── main/
        ├── java/ta105/
        │   ├── EncodingFilter.java
        │   ├── HelloApiServlet.java
        │   └── User.java
        ├── resources/
        └── webapp/
            ├── index.html
            └── static/
                ├── css/style.css
                └── js/app.js
```

## API Contract
### GET `/api/v1/hello?name=Nick`
```json
{
  "title": "105组TA招聘系统",
  "user": { "name": "Nick", "email": "user@example.com" }
}
```

### POST `/api/v1/hello`
Request:
```json
{ "name": "Nick" }
```

Response:
```json
{
  "title": "105组TA招聘系统",
  "user": { "name": "Nick", "email": "user@example.com" }
}
```

### Error Response
```json
{ "code": "INVALID_REQUEST", "message": "..." }
```

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

### 7) 验证
前端入口：
- `http://localhost:8080/ta105/`

API：
- `http://localhost:8080/ta105/api/v1/hello`
- `http://localhost:8080/ta105/api/v1/hello?name=Nick`

curl：
```bash
curl "http://localhost:8080/ta105/api/v1/hello"
curl "http://localhost:8080/ta105/api/v1/hello?name=Nick"
curl -X POST "http://localhost:8080/ta105/api/v1/hello" \
  -H "Content-Type: application/json" \
  -d '{"name":"Nick"}'
```

### 8) 停止 Tomcat
前台 `catalina run`：`Ctrl + C`

后台服务：
```bash
brew services stop tomcat
```

## Deploy To External Tomcat 11
```bash
mvn clean package
cp target/ta105.war "$(brew --prefix tomcat)/libexec/webapps/"
"$(brew --prefix tomcat)/bin/catalina" start
```
