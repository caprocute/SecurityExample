package com.untralvious.demo.security.service;

import com.untralvious.demo.security.domain.CtgGood;
import org.springframework.data.domain.Pageable;

import javax.persistence.Query;
import java.util.List;

public interface CtgGoodService {

    public List<CtgGood> getAll(String departId);

    Query createListQuery(String departId, Pageable pageable);

    Query createCountQuery(String departId);
}
