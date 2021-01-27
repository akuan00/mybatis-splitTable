package com.qinkuan.split.demo.service;

import com.qinkuan.split.demo.mapper.DepartmentMapper;
import com.qinkuan.split.demo.model.Department;
import com.qinkuan.split.mapper.IMapper;
import com.qinkuan.split.service.AbstractMapperService;
import com.qinkuan.split.type.SplitType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService extends AbstractMapperService<Department, Long> {

    @Autowired
    DepartmentMapper departmentMapper;


    @Override
    public String cacheNamespace() {
        return null;
    }

    @Override
    protected IMapper getMapper() {
        return departmentMapper;
    }

    @Override
    protected SplitType getSplitType() {
        return null;
    }


}
