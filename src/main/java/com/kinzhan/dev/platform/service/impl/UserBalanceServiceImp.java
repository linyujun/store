package com.kinzhan.dev.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinzhan.dev.platform.beans.entity.UserBalanceEntity;
import com.kinzhan.dev.platform.beans.mapper.UserBalanceMapper;
import com.kinzhan.dev.platform.service.UserBalanceService;
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
