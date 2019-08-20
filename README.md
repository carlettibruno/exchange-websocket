# Exchange WebSocket

Project created to practice websocket concepts using _javax.websocket_.

## Examples

![Example 1](https://raw.githubusercontent.com/carlettibruno/exchange-websocket/master/examples/example_1.png)

![Example 2](https://raw.githubusercontent.com/carlettibruno/exchange-websocket/master/examples/example_2.png)

![Example 3](https://raw.githubusercontent.com/carlettibruno/exchange-websocket/master/examples/example_3.png)

## How to run?
Start your Tomcat 7+ and run:  
`mvn clean tomcat7:deploy`

Server and deploy authentication must be configured in _pom.xml_:
```
<configuration>
    <url>http://localhost:8080/manager/text</url>
    <server>TomcatHml</server>
</configuration>
```

And then open the link:  
http://localhost:8080/exchange/

## WebSocket JavaScript

Is supported by all majors browsers:
https://caniuse.com/#feat=websockets