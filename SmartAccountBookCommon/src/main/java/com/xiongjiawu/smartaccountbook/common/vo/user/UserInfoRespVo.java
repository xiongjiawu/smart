package com.xiongjiawu.smartaccountbook.common.vo.user;


import com.xiongjiawu.smartaccountbook.common.annotation.TranslationDict;
import com.xiongjiawu.smartaccountbook.common.dto.user.UserLoginRespDto;
import com.xiongjiawu.smartaccountbook.common.enums.EnumTranStatus;
import com.xiongjiawu.smartaccountbook.common.model.User;
import com.xiongjiawu.smartaccountbook.common.vo.base.BaseRespVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "用户信息响应vo")
@Data
public class UserInfoRespVo extends BaseRespVo implements Serializable {

	private static final long serialVersionUID = 752692197865397397L;

	@ApiModelProperty(value = "用户id")
	private Long id;

	@ApiModelProperty(value = "用户名")
	private String userName;

	@ApiModelProperty(value = "真实姓名")
	private String realName;

	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	@ApiModelProperty(value = "手机号")
	private String phone;
	@ApiModelProperty(value = "备注")
	private String remark;
	@ApiModelProperty(value = "创建人")
	private String createUser;
	@ApiModelProperty(value = "更新人")
	private String updateUser;
	@ApiModelProperty(value = "修改时间")
	private Date updateTime;


	/**
	 *
	 * @return
	 */
	public static UserInfoRespVo parse(UserLoginRespDto respDto) {
		UserInfoRespVo respVo = new UserInfoRespVo();
		respVo.setId(respDto.getUser().getId());
		respVo.setUserName(respDto.getUser().getUserName());
		respVo.setRealName(respDto.getUser().getRealName());
		//respVo.setOrgCode(respDto.getOrganization().getOrgCode());
		//respVo.setOrgName(respDto.getOrganization().getOrgName());
		//if(respDto.getDepartment() != null) {
		//	respVo.setDeptCode(respDto.getDepartment().getDeptCode());
		//	respVo.setDeptName(respDto.getDepartment().getDeptName());
		//}
		respVo.setCreateTime(respDto.getUser().getCreateTime());
		return respVo;
	}

	public UserInfoRespVo() {
	}

	public UserInfoRespVo(User item) {
		BeanUtils.copyProperties(item,this);
	}

}
