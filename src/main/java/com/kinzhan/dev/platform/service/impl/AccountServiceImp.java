package com.kinzhan.dev.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinzhan.dev.platform.beans.entity.AccountEntity;
import com.kinzhan.dev.platform.beans.mapper.AccountMapper;
import com.kinzhan.dev.platform.service.AccountService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 收款账户表 服务实现类
 * </p>
 *
 * @author songsir
 * @since 2022-04-05
 */
@Service
public class AccountServiceImp extends ServiceImpl<AccountMapper, AccountEntity> implements AccountService {

}
