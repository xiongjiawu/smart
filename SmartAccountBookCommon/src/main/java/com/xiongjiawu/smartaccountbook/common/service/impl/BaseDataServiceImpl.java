package com.xiongjiawu.smartaccountbook.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiongjiawu.smartaccountbook.common.dao.BaseDataMapper;
import com.xiongjiawu.smartaccountbook.common.model.BaseData;
import com.xiongjiawu.smartaccountbook.common.service.BaseDataService;

/**
 * 基础数据表 服务接口
 * s_basedata
 */
@Service
public class BaseDataServiceImpl extends com.xiongjiawu.smartaccountbook.common.base.BaseServiceImpl<BaseData> implements BaseDataService {
    @Autowired
    private BaseDataMapper baseDataMapper;
    
    public BaseDataServiceImpl() {
    
    }
    
    @Override
    public com.xiongjiawu.smartaccountbook.common.base.BaseMapper<BaseData> getMapper() {
        return this.baseDataMapper;
    }
}
