package com.leomax.delayedMQ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MsgController {

	@Autowired
	private DelayedSender delayedSender;

	/**
	 * 测试发送延时消息
	 *
	 * @return
	 */
	@GetMapping("/scs/delayedSender")
	public String delayedSender() {
		delayedSender.delayedMessage();
		return "delay msg ok";
	}

}
