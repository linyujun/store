package com.mtstore.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtstore.server.beans.entity.AccountEntity;
import com.mtstore.server.beans.mapper.AccountMapper;
import com.mtstore.server.service.AccountService;
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
