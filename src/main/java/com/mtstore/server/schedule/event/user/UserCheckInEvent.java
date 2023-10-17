package com.mtstore.server.schedule.event.user;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author hupeng
 * 微信模板事件
 */
@Getter
public class UserCheckInEvent extends ApplicationEvent {

	private Integer uid;
	/**
	 * 重写构造函数
	 * @param source 发生事件的对象
	 * @param uid 自定义
	 */
	public UserCheckInEvent(Object source, Integer uid) {
		super(source);
		this.uid = uid;
	}
}
