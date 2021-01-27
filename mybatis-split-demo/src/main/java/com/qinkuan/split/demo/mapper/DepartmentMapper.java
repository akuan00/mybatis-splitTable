package com.qinkuan.split.demo.mapper;

import com.qinkuan.split.demo.model.Department;
import com.qinkuan.split.mapper.IMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentMapper extends IMapper<Department, Long> {



}
