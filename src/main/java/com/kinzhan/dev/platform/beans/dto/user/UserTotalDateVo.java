package com.kinzhan.dev.platform.beans.dto.user;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserTotalDateVo {
    private LocalDate date;
    private Long total;
}
