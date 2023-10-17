package com.mtstore.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtstore.server.beans.entity.UserBalanceEntity;
import com.mtstore.server.beans.mapper.UserBalanceMapper;
import com.mtstore.server.service.UserBalanceService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户账户余额表 服务实现类
 * </p>
 *
 * @author songsir
 * @since 2022-04-06
 */
@Service
public class UserBalanceServiceImp extends ServiceImpl<UserBalanceMapper, UserBalanceEntity> implements UserBalanceService {

}
