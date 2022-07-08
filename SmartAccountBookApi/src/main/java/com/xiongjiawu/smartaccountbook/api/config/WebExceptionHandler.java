package com.xiongjiawu.smartaccountbook.api.config;


import com.xiongjiawu.smartaccountbook.api.feign.system.FeignUserController;
import com.xiongjiawu.smartaccountbook.common.base.BaseResult;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * validate校验 异常统一捕捉处理
 */
@ControllerAdvice
public class WebExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(FeignUserController.class);

    /**
     * 处理请求参数格式错误 @RequestBody上validate失败后抛出的异常是MethodArgumentNotValidException异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public BaseResult<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String message =
                e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(";"));
        return new BaseResult<>(false, message);
    }

    /**
     * 处理Get请求中 使用@Valid 验证路径中请求实体校验失败后抛出的异常
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public BaseResult<String> BindExceptionHandler(BindException e) {
        String message =
                e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(";"));
        return new BaseResult<>(false, message);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public BaseResult<String> RuntimeExceptionHandler(RuntimeException e) {
        log.error("fail:", e);
        String message = e.getMessage();
        return new BaseResult<>(false, StringUtils.isBlank(message) ? e.toString() : message);
    }

    /**
     * 处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是ConstraintViolationException
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public BaseResult<String> ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"));
        return new BaseResult<>(false, message);
    }

}
