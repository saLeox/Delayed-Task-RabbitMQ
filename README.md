


# Distributed-Delayed-Queue

## Objective

Archieve the distributed and robust delayed task management function.

## Alternative Solution

| Solution | Pros |Cons |
|:--:|:--|:--|
| Delay Queue in JDK |No third party dependency.|Nondistributed; Single Point of Failure; Store in memory. |
| Quartz |Easy to implement.|High frequency to scan table, cause high load to DB and application server. |
| Redis sorted set |Distributed; Support by Redisson in Java.|Not suitable to large scale dataset because of re-partition when exceed specific qty.|
| Redis expiration callback |Distributed.|No ack; Lagging in reaction when overloading|
| RabbitMQ + TTL (Time to Live) |Distributed.|Fixed time for the msg under the whole topic.|
| RabbitMQ + delayed-message-exchange plugin |Distributed; Flexible time to each msg.|Not suitable for over storing.|
| RocketMQ |Distributed; Support delayed msg naturally.|Fixed short time gap.|
| TimingWheel in Netty or Kafka |Distributed; Real time processing with high throughput.|Abstract, difficult to understand the concept of ticksPerWheel.|

Finally, after trade-off, we choose the ***RabbitMQ + delayed-message-exchange plugin*** to implement it.

## Implementation

 1. Install a [docker image](https://hub.docker.com/r/heidiks/rabbitmq-delayed-message-exchange/) with delayed-message-exchange plugin inside.
 2. Import the ***spring-cloud-stream-binder-rabbit*** dependent in project.
 3. Define the properties in [.yml file](https://github.com/saLeox/delayed-queue-rabbit/blob/main/src/main/resources/application.yml), and ignore the ***delayed-exchange*** if needed.
 4. Write the DelayedSink, DelayedSender, and DelayedReceiver separately. In our case, the delayed time is 20 second.

## Outcome

Ping this API: http://localhost:8888/scs/delayedSender

Then will logging info will be shown in console.

<div align=center><img src="https://raw.githubusercontent.com/saLeox/photoHub/main/20210505191111.png" width="65%"/></div>

## Best Practice

Rather than putting all tasks into the delayed queue, especially that will wait for over few weeks, we have better use [scheduling tool](https://github.com/saLeox/Distributed-Scheduled-Job) to fetch the upcoming task then handle it to mq, so that can help relieve the storing load of mq.

<div align=center><img src="https://raw.githubusercontent.com/saLeox/photoHub/main/20210525115334.png" width="70%"/></div>

## Appendix

 1. https://juejin.cn/post/6844904163168485383#heading-11
 2.  https://github.com/viturefree/rabbitmq-delay
