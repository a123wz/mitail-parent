package com.mitail.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import com.mitail.constant.HeadConstant;
import com.mitail.util.RequestContextUtil;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

@Slf4j
@Aspect
@Component
public class RestControllerAspect {

	@Bean
	public RequestInterceptor headerInterceptor() {
		return template -> {
			String flow_no = RequestContextUtil.getRequest().getHeader(HeadConstant.FLOW_NO);
			if(!StringUtils.isEmpty(flow_no)) {
				template.header(HeadConstant.FLOW_NO, flow_no);
			}
		};
	}


	/**
	 * 环绕通知
	 *
	 * @param joinPoint
	 *            连接点
	 * @return 切入点返回值
	 * @throws Throwable
	 *             异常信息
	 */
	@Around("@within(org.springframework.web.bind.annotation.RestController) || @annotation(org.springframework.web.bind.annotation.RestController)")
	public Object apiLog(ProceedingJoinPoint joinPoint) throws Throwable {
		String flow_no = RequestContextUtil.getRequest().getHeader(HeadConstant.FLOW_NO);
		if(!StringUtils.isEmpty(flow_no)){
			Thread.currentThread().setName(flow_no);
		}
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		String methodName = this.getMethodName(joinPoint);
		String params = this.getParamsJson(joinPoint);

		log.info("Started method [{}] params [{}] IP [{}] headerInfo [{}] ",methodName, params);
		long start = System.currentTimeMillis();
		Object result = joinPoint.proceed();
		log.info("Ended method [{}] params[{}] response is [{}] cost [{}] millis ", methodName, params,
				this.deleteSensitiveContent(result), System.currentTimeMillis() - start);
		return result;
	}

	private String getMethodName(ProceedingJoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().toShortString();
		String shortMethodNameSuffix = "(..)";
		if (methodName.endsWith(shortMethodNameSuffix)) {
			methodName = methodName.substring(0, methodName.length() - shortMethodNameSuffix.length());
		}
		return methodName;
	}

	private String getParamsJson(ProceedingJoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		if (args == null || args.length == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (Object arg : args) {
			// 移除敏感内容
			String paramStr;
			if (arg instanceof HttpServletResponse) {
				paramStr = HttpServletResponse.class.getSimpleName();
			} else if (arg instanceof HttpServletRequest) {
				paramStr = HttpServletRequest.class.getSimpleName();
			} else if (arg instanceof MultipartFile) {
				long size = ((MultipartFile) arg).getSize();
				paramStr = MultipartFile.class.getSimpleName() + " size:" + size;
			} else {
				paramStr = this.deleteSensitiveContent(arg);
			}
			sb.append(paramStr).append(",");
		}
		return sb.deleteCharAt(sb.length() - 1).toString();
	}


	/**
	 * 删除参数中的敏感内容
	 *
	 * @param obj
	 *            参数对象
	 * @return 去除敏感内容后的参数对象
	 */
	private String deleteSensitiveContent(Object obj) {
		JSONObject jsonObject = new JSONObject();
		if (obj == null || obj instanceof Exception) {
			return jsonObject.toJSONString();
		}

		try {
			if (obj.getClass().isArray()) {
				return JSON.toJSONString(obj,SerializerFeature.WriteDateUseDateFormat);
			} else {
				String param = JSON.toJSONString(obj,SerializerFeature.WriteDateUseDateFormat);
				jsonObject = JSONObject.parseObject(param);
				List<String> sensitiveFieldList = this.getSensitiveFieldList();
				for (String sensitiveField : sensitiveFieldList) {
					if (jsonObject.containsKey(sensitiveField)) {
						jsonObject.put(sensitiveField, "******");
					}
				}
			}
		} catch (Exception e) {
			return String.valueOf(obj);
		}
		return jsonObject.toJSONString();
	}

	/**
	 * 敏感字段列表
	 */
	private List<String> getSensitiveFieldList() {
		List<String> sensitiveFieldList = Lists.newArrayList();
		sensitiveFieldList.add("pwd");
		sensitiveFieldList.add("password");
		return sensitiveFieldList;
	}
}
