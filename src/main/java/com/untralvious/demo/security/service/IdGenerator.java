package com.untralvious.demo.security.service;

import java.io.Serializable;
import java.util.UUID;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class IdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        System.out.println(uuid + "-----" + o.toString());
        return uuid;
    }
}
