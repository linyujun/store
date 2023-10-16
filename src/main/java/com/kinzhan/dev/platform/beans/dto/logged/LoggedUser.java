package com.kinzhan.dev.platform.beans.dto.logged;


import com.kinzhan.dev.platform.beans.dto.user.UserPayloadDto;

public class LoggedUser {
    private static final ThreadLocal<UserPayloadDto> userHolder =
            new ThreadLocal<>();

    public static void logIn(UserPayloadDto dto) {
        userHolder.set(dto);
    }

    public static void logOut() {
        userHolder.remove();
    }

    public static UserPayloadDto get() {
        return userHolder.get();
    }
}
