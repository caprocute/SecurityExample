package com.untralvious.demo.security.service.Impl;

import com.untralvious.demo.security.domain.CtgGood;
import com.untralvious.demo.security.domain.SysUser;
import com.untralvious.demo.security.service.CtgGoodService;
import com.untralvious.demo.security.service.UserService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class CtgGoodServiceImpl implements CtgGoodService {

    @PersistenceContext
    private EntityManager entityManager;

    private final UserService userService;

    public CtgGoodServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<CtgGood> getAll() {
        String query = new String ("select * from ctg_good ctg\n" +
            "where ctg.depart_id in (\n" +
            "\tWITH RECURSIVE\n" +
            "\tcte ( id, parent_id )\n" +
            "\tAS\n" +
            "\t( SELECT id, parent_id  \n" +
            "\t          FROM sys_depart WHERE id = :id" +
            "\t  UNION ALL\n" +
            "\t  SELECT sd.id, sd.parent_id \n" +
            "\t  FROM cte JOIN sys_depart sd ON cte.id = sd.parent_id  \n" +
            "\t)\n" +
            "\tselect id from cte\n" +
            ")"
        );
        Query sqlQuery =  entityManager.createNativeQuery(query);
        SysUser sysUser = userService.getUserWithLogin();
        sqlQuery.setParameter("id", sysUser.getDepartIds());
        return sqlQuery.getResultList();
    }
}
