package com.mtstore.server.beans.dto.user;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserTotalDateVo {
    private LocalDate date;
    private Long total;
}
