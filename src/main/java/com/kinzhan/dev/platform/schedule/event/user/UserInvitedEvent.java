package com.kinzhan.dev.platform.schedule.event.user;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author hupeng
 * 微信模板事件
 */
@Getter
public class UserInvitedEvent extends ApplicationEvent {

	private Integer inviteId;
	/**
	 * 重写构造函数
	 * @param source 发生事件的对象
	 * @param inviteId 自定义
	 */
	public UserInvitedEvent(Object source, Integer inviteId) {
		super(source);
		this.inviteId = inviteId;
	}
}
