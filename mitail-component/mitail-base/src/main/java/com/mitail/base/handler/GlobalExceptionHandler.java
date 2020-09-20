package com.mitail.base.handler;

import com.mitail.base.enums.ResultCode;
import com.mitail.base.model.Result;
import com.mitail.base.util.ParamInvlidUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;


/**
 * @desc 统一异常处理器
 *
 * @author zhumaer
 * @since 8/31/2017 3:00 PM
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /** 处理400类异常 */
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ConstraintViolationException.class)
    public Result handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
        List<ParamInvlidUtil.ParamErrorMessage> parameterInvalidItemList = ParamInvlidUtil.convertErrorMessage(e.getConstraintViolations());
        return Result.error(ResultCode.PARAM_ERROR, parameterInvalidItemList);
    }

//	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result handleConstraintViolationException(HttpMessageNotReadableException e, HttpServletRequest request) {
        return Result.error(ResultCode.PARAM_ERROR, e.getMessage());
    }

//	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BindException.class)
    public Result handleBindException(BindException e, HttpServletRequest request) {
        List<ParamInvlidUtil.ParamErrorMessage> parameterInvalidItemList = ParamInvlidUtil.convertBindingResultMessage(e.getBindingResult());
        return Result.error(ResultCode.PARAM_ERROR, parameterInvalidItemList);
    }

//	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        List<ParamInvlidUtil.ParamErrorMessage> parameterInvalidItemList = ParamInvlidUtil.convertBindingResultMessage(e.getBindingResult());
        return Result.error(ResultCode.PARAM_ERROR, parameterInvalidItemList);
    }

//    /** 处理自定义异常 */
//    @ExceptionHandler(BusinessException.class)
//    public ResponseEntity<Result> handleBusinessException(BusinessException e, HttpServletRequest request) {
//        return super.handleBusinessException(e, request);
//    }

    /** 处理运行时异常 */
//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(RuntimeException.class)
    public Result handleRuntimeException(RuntimeException e, HttpServletRequest request) {
//        TODO 可通过邮件、微信公众号等方式发送信息至开发人员、记录存档等操作
        log.error("运行时发生异常:{}",e);
        return Result.error(ResultCode.SYSTEM_ERROR,e.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Throwable.class)
    public Result handleException(Throwable e, HttpServletRequest request) {
        log.error("运行时发生异常:{}",e);
        return Result.error(ResultCode.SYSTEM_ERROR,null);
    }

}