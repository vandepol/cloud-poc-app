# cloud-poc

The IBM ICP CoC team modifications of the code origination here: [https://github.com/deepthisprint/cloud-poc](https://github.com/deepthisprint/cloud-poc).

This app extends the [Spring Boot Websocket Chat Demo](https://www.callicoder.com/spring-boot-websocket-chat-example/),    source: [https://github.com/callicoder/spring-boot-websocket-chat-demo](https://github.com/callicoder/spring-boot-websocket-chat-demo).

This application uses [Stomp](http://jmesnil.net/stomp-websocket/doc/) over websockets to add messages directly to ActiveMQ running on the server. A call is made to an external service (Mule) - which is [being stubbed](https://github.com/jconallen/mule-stub). Failed records get placed in a Mongo db.