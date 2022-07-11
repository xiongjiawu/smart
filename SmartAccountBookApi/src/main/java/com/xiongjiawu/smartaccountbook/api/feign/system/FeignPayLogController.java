package com.xiongjiawu.smartaccountbook.api.feign.system;

import com.xiongjiawu.smartaccountbook.api.feign.base.FeignBaseController;
import com.xiongjiawu.smartaccountbook.common.base.BaseResult;
import com.xiongjiawu.smartaccountbook.common.model.PayLog;
import com.xiongjiawu.smartaccountbook.common.model.User;
import com.xiongjiawu.smartaccountbook.common.service.PayLogService;
import com.xiongjiawu.smartaccountbook.common.vo.payLog.PayLogAddEditVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@Api(tags = "支付记录管理")
@RequestMapping("/payLog")
public class FeignPayLogController extends FeignBaseController {
    @Autowired
    private PayLogService payLogService;


    @PostMapping("/addEditPayLog")
    @ApiOperation("新增/编辑支付记录")
    @ApiImplicitParam(name = "type", value = "收支类型: 0-支付; 1-收入")
    public BaseResult<String> addEditUserInfo(@Valid PayLogAddEditVo vo, Integer type) {
        User user = getShiroUser().getUser();
        if (user == null){
            return new BaseResult<>(false,"未找到当前登录用户信息!");
        }
        PayLog payLog = new PayLog();
        BeanUtils.copyProperties(vo, payLog);
        if (type == 0){
            payLog.setPayandincomeamount(new BigDecimal("-"+payLog.getPayandincomeamount()));
        }
        payLog.setUserName(user.getUserName());
        if (payLog.getId() == null) { // 新增
            payLogService.insert(payLog);
        } else { // 编辑
            payLogService.updateByPrimaryKey(payLog);
        }
        return new BaseResult<>(true, "操作成功！");
    }


}
