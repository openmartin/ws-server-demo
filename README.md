# ws-server-demo

本地服务地址(具体端口跟tomcat配置有关)

http://localhost:8280/services/HelloWorld?wsdl


## spring + cfx 配置

### maven

添加依赖包

```xml
<dependency>
  <groupId>org.apache.cxf</groupId>
  <artifactId>cxf-rt-frontend-jaxws</artifactId>
  <version>3.2.3</version>
</dependency>
<dependency>
  <groupId>org.apache.cxf</groupId>
  <artifactId>cxf-rt-transports-http</artifactId>
  <version>3.2.3</version>
</dependency>
```


### web.xml

在web.xml 增加 spring 的配置和 CXFServlet 的配置

```xml
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:applicationContext.xml</param-value>
    </context-param>
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
    <servlet>
        <servlet-name>CXFServlet</servlet-name>
        <servlet-class>org.apache.cxf.transport.servlet.CXFServlet</servlet-class>  
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>CXFServlet</servlet-name>  
        <url-pattern>/services/*</url-pattern> 
    </servlet-mapping>
```

### 编写服务

```java
package com.example.demo.ws.service;

import javax.jws.WebService;
import java.util.Date;


@WebService
public interface HelloWorld {

    String sayHi(String text);

    Date currentDate();
}
```

```java
package com.example.demo.ws.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jws.WebService;
import java.util.Date;

@WebService(endpointInterface = "com.example.demo.ws.service.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public String sayHi(String text) {
        logger.info("sayHi called");
        return "Hello " + text;
    }

    @Override
    public Date currentDate() {
        logger.info("currentDate called");
        return new Date();
    }
}
```
### applicationContext.xml

在 applicationContext.xml 增加 Service 的配置，记得要 import cxf 的配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:jaxws="http://cxf.apache.org/jaxws"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://cxf.apache.org/jaxws http://cxf.apache.org/schemas/jaxws.xsd">

    <import resource="classpath:META-INF/cxf/cxf.xml"/>
    <import resource="classpath:META-INF/cxf/cxf-servlet.xml"/>
    <jaxws:endpoint id="helloWorld" implementor="com.example.demo.ws.service.HelloWorldImpl" address="/HelloWorld"/>

</beans>
```

### WSDL

http://localhost:8280/services/HelloWorld?wsdl

就能看到 XML 接口说明