package com.xiongjiawu.smartaccountbook.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiongjiawu.smartaccountbook.common.dao.PayLogMapper;
import com.xiongjiawu.smartaccountbook.common.model.PayLog;
import com.xiongjiawu.smartaccountbook.common.service.PayLogService;

/**
 * 收支记录 服务接口
 * s_paylog
 */
@Service
public class PayLogServiceImpl extends com.xiongjiawu.smartaccountbook.common.base.BaseServiceImpl<PayLog> implements PayLogService {
    @Autowired
    private PayLogMapper payLogMapper;
    
    public PayLogServiceImpl() {
    
    }
    
    @Override
    public com.xiongjiawu.smartaccountbook.common.base.BaseMapper<PayLog> getMapper() {
        return this.payLogMapper;
    }
}
