package com.leomax.delayedMQ;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class DelayedSender {

	@Autowired
	private DelayedSink delayedSink;

	public void delayedMessage() {
		String context = "test delay message";
		System.out.println("Send time: " + LocalDateTime.now() + "  Send: " + context);
		// 延时时间20秒
		delayedSink.output().send(MessageBuilder.withPayload(context).setHeader("x-delay", 20000).build());
	}

}
