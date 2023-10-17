package com.mtstore.server.config.handle;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

@Slf4j
public class MyTenantLineHandler implements TenantLineHandler {
    private static final String[] ROLES = {"SA", "ADMIN"};
    final String[] ignoreList = {"kz_sys_property", "kz_sys_menu", "kz_sys_quartz_job", "kz_sys_quartz_log","kz_order_queue",
            "kz_sys_config", "kz_sys_tenant", "kz_sys_dict", "kz_sys_role", "kz_sys_permission", "kz_user", "kz_agent_bill"};

    @Override
    public Expression getTenantId() {
        // 模拟ID
        String tenantId = "0";
        if (null != LoggedUser.get() && LoggedUser.get().getTenantId() > 0) {
            tenantId = LoggedUser.get().getTenantId().toString();
        }

        return new StringValue(tenantId);
    }

    /**
     * 获取租户表字段 默认为tenant_id
     *
     * @return
     */
    @Override
    public String getTenantIdColumn() {
        return "tenant_id";
    }

    /**
     * 表过滤，返回true，表示当前表不进行租户过滤
     *
     * @param tableName 表名
     * @return
     */
    @Override
    public boolean ignoreTable(String tableName) {
        // 排除user表
        if (null == LoggedUser.get()) {
            return true;
        }
        if (null != LoggedUser.get().getRole() && ArrayUtils.contains(ROLES, LoggedUser.get().getRole())) {
            return true;
        }

        return  Arrays.asList(ignoreList).contains(tableName);
    }
}
