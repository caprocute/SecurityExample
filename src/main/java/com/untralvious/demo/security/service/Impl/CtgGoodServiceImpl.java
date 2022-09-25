package com.untralvious.demo.security.service.Impl;

import com.untralvious.demo.security.domain.CtgGood;
import com.untralvious.demo.security.service.CtgGoodService;
import com.untralvious.demo.security.service.UserService;
import org.springframework.data.domain.Pageable;
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
    public List<CtgGood> getAll(String departId) {
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
        sqlQuery.setParameter("id", departId);
        return sqlQuery.getResultList();
    }

    @Override
    public Query createListQuery(String departId, Pageable pageable) {
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
        sqlQuery.setParameter("id", departId);
        int position = 0;
        if(pageable.getPageNumber() >= 2){
            position = (pageable.getPageNumber()-1)*pageable.getPageSize();
            sqlQuery.setFirstResult(position);
        } else {
            sqlQuery.setFirstResult(position);
        }
        sqlQuery.setMaxResults(pageable.getPageSize());
        return sqlQuery;
    }

    @Override
    public Query createCountQuery(String departId) {
        String query = new String ("select count(*) from ctg_good ctg\n" +
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
        sqlQuery.setParameter("id", departId);
        return sqlQuery;
    }


}
