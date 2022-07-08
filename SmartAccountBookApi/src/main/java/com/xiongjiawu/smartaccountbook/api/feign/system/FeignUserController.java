package com.xiongjiawu.smartaccountbook.api.feign.system;


import cn.hutool.crypto.SecureUtil;
import com.xiongjiawu.smartaccountbook.api.feign.base.FeignBaseController;
import com.xiongjiawu.smartaccountbook.common.base.BaseResult;
import com.xiongjiawu.smartaccountbook.common.dto.user.UserLoginReqDto;
import com.xiongjiawu.smartaccountbook.common.dto.user.UserLoginRespDto;
import com.xiongjiawu.smartaccountbook.common.dto.user.UserLogoutReqDto;
import com.xiongjiawu.smartaccountbook.common.dto.user.UserLogoutRespDto;
import com.xiongjiawu.smartaccountbook.common.enums.EnumClientType;
import com.xiongjiawu.smartaccountbook.common.enums.EnumStatus;
import com.xiongjiawu.smartaccountbook.common.enums.EnumUserType;
import com.xiongjiawu.smartaccountbook.common.facade.UserFacade;
import com.xiongjiawu.smartaccountbook.common.model.User;
import com.xiongjiawu.smartaccountbook.common.model.UserAuth;
import com.xiongjiawu.smartaccountbook.common.service.UserService;
import com.xiongjiawu.smartaccountbook.common.vo.base.BaseQueryPageVo;
import com.xiongjiawu.smartaccountbook.common.vo.base.BaseResultVo;
import com.xiongjiawu.smartaccountbook.common.vo.user.*;
import com.zhangzlyuyx.easy.core.Result;
import com.zhangzlyuyx.easy.mybatis.Condition;
import com.zhangzlyuyx.easy.mybatis.PageResult;
import com.zhangzlyuyx.easy.shiro.authc.AccessToken;
import com.zhangzlyuyx.easy.shiro.util.ShiroUtils;
import com.zhangzlyuyx.easy.spring.util.SpringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jsoup.internal.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@RestController
@Api(tags = "用户管理")
@RequestMapping("/user")
public class FeignUserController extends FeignBaseController {
    @Autowired
    private UserFacade userFacade;
    @Autowired
    private UserService userService;

    /**
     * 用户登录
     *
     * @param request
     * @param response
     * @param userLoginReqVo
     * @return
     */
    @PostMapping(value = "/login")
    @ApiOperation(value = "用户登录")
    public BaseResultVo<UserLoginRespVo> login(ServletRequest request, ServletResponse response,
                                               UserLoginReqVo userLoginReqVo) {
        //注销现有登录状态
        ShiroUtils.logout();
        //用户名
        if (StringUtils.isEmpty(userLoginReqVo.getUserName())) {
            return new BaseResultVo<>(false, "请输入用户名!");
        }
        //密码
        if (StringUtils.isEmpty(userLoginReqVo.getPassword())) {
            return new BaseResultVo<>(false, "请输入密码!");
        }
        //客户端类型
        EnumClientType enumClientType = EnumClientType.parseByKey(userLoginReqVo.getClientType());
        if (enumClientType == null) {
            return new BaseResultVo<>(false, "请选择有效的客户端类型!");
        }

        //保存登录
        UserLoginReqDto reqBo = new UserLoginReqDto();
        reqBo.setUserName(userLoginReqVo.getUserName());
        reqBo.setPassword(userLoginReqVo.getPassword());
        reqBo.setClientType(enumClientType);
        reqBo.setClientIp(SpringUtils.getClientIP((HttpServletRequest) request));
        reqBo.setUserAgent(SpringUtils.getUserAgent((HttpServletRequest) request));
        Result<UserLoginRespDto> retLogin = this.userFacade.saveUserLogin(reqBo);
        if (!retLogin.isSuccess()) {
            return new BaseResultVo<>(false, retLogin.getMsg());
        }
        //返回结果
        UserLoginRespVo vo = UserLoginRespVo.parse(retLogin.getData());
        return new BaseResultVo<>(true, "登录成功", vo);
    }

    /**
     * 刷新登录
     *
     * @return
     */
    @PostMapping(value = "/refreshLogin")
    @ApiOperation(value = "刷新登录")
    public BaseResultVo<UserLoginRespVo> refreshLogin(ServletRequest request, ServletResponse response) {
        AccessToken shiroToken = ShiroUtils.getAccessToken();
        if (shiroToken == null) {
            return new BaseResultVo<>(false, "获取用户登录信息失败!");
        }
        //注销现有登录状态
        ShiroUtils.logout();
        //获取认证信息
        Result<UserAuth> retUserAuth = this.userFacade.getUserAuth(shiroToken.getAccessToken());
        if (!retUserAuth.isSuccess()) {
            return new BaseResultVo<>(false, retUserAuth.getMsg());
        }
        //保存登录
        UserLoginReqDto loginReqDto = new UserLoginReqDto();
        loginReqDto.setUserName(retUserAuth.getData().getUsername());
        loginReqDto.setPassword(null);
        loginReqDto.setClientType(EnumClientType.parseByKey(retUserAuth.getData().getClientType()));
        loginReqDto.setClientIp(SpringUtils.getClientIP((HttpServletRequest) request));
        loginReqDto.setUserAgent(SpringUtils.getUserAgent((HttpServletRequest) request));
        Result<UserLoginRespDto> retLogin = this.userFacade.saveUserLogin(loginReqDto);
        if (!retLogin.isSuccess()) {
            return new BaseResultVo<>(false, retLogin.getMsg());
        }
        //返回结果
        UserLoginRespVo vo = UserLoginRespVo.parse(retLogin.getData());
        return new BaseResultVo<UserLoginRespVo>(true, "刷新登录成功", vo);
    }

    /**
     * 用户注销
     *
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "/userLogout")
    @ApiOperation(value = "用户注销")
    public BaseResultVo<UserLogoutRespVo> userLogout(ServletRequest request, ServletResponse response) {
        AccessToken shiroToken = ShiroUtils.getAccessToken();
        //注销现有登录状态
        ShiroUtils.logout();
        if (shiroToken == null) {
            return new BaseResultVo<>(false, "获取用户登录信息失败!");
        }
        //清除认证信息
        UserLogoutReqDto logoutReqDto = new UserLogoutReqDto();
        logoutReqDto.setAccessToken(shiroToken.getAccessToken());
        Result<UserLogoutRespDto> retLogout = this.userFacade.saveUserLogout(logoutReqDto);
        if (!retLogout.isSuccess()) {
            return new BaseResultVo<>(false, retLogout.getMsg());
        }
        return new BaseResultVo<UserLogoutRespVo>(true, "注销成功");
    }


    /**
     * 用户信息
     *
     * @return
     */
    @PostMapping(value = "/userInfo")
    @ApiOperation(value = "用户信息")
    public BaseResult<UserInfoRespVo> userInfo() {
        User user = getShiroUser().getUser();
        if (user == null) {
            return new BaseResult<>(false, "获取用户登录信息失败!", null);
        }
        //查询用户
        UserInfoRespVo userInfoVo = new UserInfoRespVo(user);
        return new BaseResult<>(true, "请求成功", userInfoVo);
    }

    @PostMapping(value = "/queryUserInfoList")
    @ApiOperation(value = "查询用户信息列表(分页)")
    @ApiImplicitParams({@ApiImplicitParam(name = "userName", value = "用户名(模糊查询)"),
            @ApiImplicitParam(name = "roleCode", value = "角色代码"),
            @ApiImplicitParam(name = "orgCode", value = "单位/机构代码"),
            @ApiImplicitParam(name = "deptCode", value = "部门代码"),
            @ApiImplicitParam(name = "realName", value = "用户姓名(模糊查询)"),
            @ApiImplicitParam(name = "inChargeDeptCode", value = "分管部门代码")})
    public BaseResult<PageResult<UserInfoVo>> queryUserInfoList(String userName, String roleCode, String orgCode, String deptCode, String realName,
                                                                String inChargeDeptCode, BaseQueryPageVo baseQueryPageVo) {
        // 封装查询条件
        List<Condition> conditions = new ArrayList<>();
        if (StringUtils.isNotBlank(userName)) {
            conditions.add(new Condition(User.column_userName, "like", "%" + userName + "%"));
        }
        if (StringUtils.isNotBlank(realName)) {
            conditions.add(new Condition(User.column_realName, "like", "%" + realName + "%"));
        }
        conditions.add(new Condition(User.column_isValid, EnumStatus.valid.getKey()));
        // 执行查询
        PageResult<User> pageResult = (PageResult<User>) userService.selectByPage(baseQueryPageVo.load(conditions));
        List<User> userList = pageResult.getRows();
        // 封装结果
        List<UserInfoVo> voList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(userList)) {
            for (User user : userList) {
                voList.add(new UserInfoVo(user));
            }
        }
        PageResult<UserInfoVo> result = new PageResult<>(voList);
        result.setPageNo(pageResult.getPageNo());
        result.setPages(pageResult.getPages());
        result.setPageSize(pageResult.getPageSize());
        result.setTotal(pageResult.getTotal());
        return new BaseResult<>(true, null, result);
    }

    @PostMapping(value = "/queryAllUserInfoList")
    @ApiOperation(value = "查询用户信息列表(不分页)")
    @ApiImplicitParams({@ApiImplicitParam(name = "userName", value = "用户名(模糊查询)"),
            @ApiImplicitParam(name = "roleCode", value = "角色代码"),
            @ApiImplicitParam(name = "orgCode", value = "单位/机构代码"),
            @ApiImplicitParam(name = "deptCode", value = "部门代码"),
            @ApiImplicitParam(name = "realName", value = "用户姓名(模糊查询)")})
    public BaseResult<List<UserInfoVo>> queryAllUserInfoList(String userName, String roleCode, String orgCode, String deptCode, String realName) {
        // 封装查询条件
        List<Condition> conditions = new ArrayList<>();
        if (StringUtils.isNotBlank(userName)) {
            conditions.add(new Condition(User.column_userName, "like", "%" + userName + "%"));
        }
        if (StringUtils.isNotBlank(realName)) {
            conditions.add(new Condition(User.column_realName, "like", "%" + realName + "%"));
        }
        conditions.add(new Condition(User.column_isValid, EnumStatus.valid.getKey()));
        // 执行查询
        List<User> userList = userService.selectByCondition(conditions);
        // 封装结果
        List<UserInfoVo> voList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(userList)) {
            userList.forEach(a -> {
                UserInfoVo vo = new UserInfoVo(a);
                voList.add(vo);
            });
        }
        return new BaseResult<>(true, null, voList);
    }

    @PostMapping("/addEditUser")
    @ApiOperation("新增/编辑用户")
    public BaseResult<String> addEditUserInfo(@Valid UserAddEditVo vo) {
        User user = new User();
        BeanUtils.copyProperties(vo, user);
        if (user.getId() == null) { // 新增
            if (org.apache.commons.lang3.StringUtils.isBlank(user.getPassword())) {
                return new BaseResult<>(false, "密码不能为空!");
            }
            User userByUserName = userService.getUserByUserName(user.getUserName());
            if (userByUserName != null) {
                return new BaseResult<>(false, "用户名已存在!");
            }
            user.setPassword(SecureUtil.md5(vo.getPassword()));
            userService.insert(user);
        } else { // 编辑
            User userById = userService.selectByPrimaryKey(user.getId());
            if (StringUtils.isNotBlank(user.getPassword())) {
                user.setPassword(null);
            }
            if (!userById.getUserName().equals(user.getUserName())) {//若修改用户名,则判断是否存在相同用户
                User userByUserName = userService.getUserByUserName(user.getUserName());
                if (userByUserName != null) {
                    return new BaseResult<>(false, "用户名已存在!");
                }
            }
            userService.updateByPrimaryKey(user);
        }
        return new BaseResult<>(true, "操作成功！");
    }


    @PostMapping("/resetPassword")
    @ApiOperation("修改用户密码")
    public BaseResult<String> resetPassword(Long id, String newPassword) {
        if (id == null) {
            return new BaseResult<>(false, "用户ID不能为空！");
        }
        if (StringUtil.isBlank(newPassword)) {
            return new BaseResult<>(false, "输入新密码");
        }
        User user = userService.selectByPrimaryKey(id);
        if (user == null) {
            return new BaseResult<>(false, "未找到用户信息！");
        }
        user.setPassword(SecureUtil.md5(newPassword));
        int i = userService.updateByPrimaryKey(user);
        return i > 0 ? new BaseResult<>(true, "修改成功") : new BaseResult<>(false, "修改失败");
    }

    @PostMapping("/updatePassword")
    @ApiOperation("修改密码")
    public BaseResult<String> updatePassword(String oldPassword, String newPassword) {
        User user = getShiroUser().getUser();
        if (user == null) {
            return new BaseResult<>(false, "未找到当前用户信息！");
        }
        if (StringUtil.isBlank(oldPassword)) {
            return new BaseResult<>(false, "输入现在账号密码");
        }
        if (StringUtil.isBlank(newPassword)) {
            return new BaseResult<>(false, "输入新密码");
        }

        user = userService.selectByPrimaryKey(user.getId());
        if (user == null) {
            return new BaseResult<>(false, "未找到当前用户信息！");
        }

        String oldMD5 = SecureUtil.md5(oldPassword);
        if (!oldMD5.equals(user.getPassword())) {
            return new BaseResult<>(false, "当前密码输入错误!");
        }
        user.setPassword(SecureUtil.md5(newPassword));
        int i = userService.updateByPrimaryKey(user);
        return i > 0 ? new BaseResult<>(true, "修改成功") : new BaseResult<>(false, "修改失败");
    }

    @PostMapping("/disableUser")
    @ApiOperation("禁用用户")
    public BaseResult<String> disableUser(Long id) {
        if (id == null) {
            return new BaseResult<>(false, "用户ID不能为空！");
        }
        User user = userService.selectByPrimaryKey(id);
        if (user == null) {
            return new BaseResult<>(false, "未找到用户信息！");
        }
        user.setIsValid(EnumStatus.invalid.getKey());
        int i = userService.updateByPrimaryKey(user);
        return i > 0 ? new BaseResult<>(true, "操作成功") : new BaseResult<>(false, "操作失败");
    }


}
