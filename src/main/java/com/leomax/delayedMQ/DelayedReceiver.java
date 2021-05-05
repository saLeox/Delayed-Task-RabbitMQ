package com.leomax.delayedMQ;

import java.time.LocalDateTime;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@EnableBinding(DelayedSink.class)
@Component
public class DelayedReceiver {

	@StreamListener(DelayedSink.INPUT)
	public void receive(String message) {
		System.out.println("Received time: " + LocalDateTime.now() + "  Received: " + message);
	}

}
