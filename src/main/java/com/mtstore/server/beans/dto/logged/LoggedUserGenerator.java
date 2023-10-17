package com.mtstore.server.beans.dto.logged;

import org.hibernate.Session;
import org.hibernate.tuple.ValueGenerator;

/**
 * @author songsir
 */

public class LoggedUserGenerator implements ValueGenerator<Integer> {

    @Override
    public Integer generateValue(
            Session session, Object owner) {
        return LoggedUser.get().getUserId();
    }
}
