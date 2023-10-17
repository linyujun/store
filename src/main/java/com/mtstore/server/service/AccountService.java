package com.mtstore.server.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtstore.server.beans.dto.account.AccountDto;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import com.mtstore.server.beans.entity.AccountEntity;

import java.util.List;

/**
 * <p>
 * 收款账户表 服务类
 * </p>
 *
 * @author songsir
 * @since 2022-04-05
 */
public interface AccountService extends IKService<AccountEntity, AccountDto> {

    default List<AccountEntity> getListByCurrentUser() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("create_user", LoggedUser.get().getUserId());

        return list(queryWrapper);
    }

    default AccountEntity findByCurrentUser(Integer accountId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("create_user", LoggedUser.get().getUserId());
        queryWrapper.eq("id", accountId);

        return getOne(queryWrapper);
    }
}
