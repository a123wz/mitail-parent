package com.mitail.base.handler;

import com.alibaba.fastjson.JSON;
import com.mitail.base.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 *  接口响应体处理器
 */
@ControllerAdvice(basePackages = "com.mitail")
@Slf4j
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {

//	/**
//	 * 字符串转换错误问题
//	 */
//	public static class StringOrMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
//
//		private static final StringHttpMessageConverter shmc = new StringHttpMessageConverter();
//
//		@Override
//		public boolean canWrite(Class<?> clazz, MediaType mediaType) {
//			boolean canWrite = super.canWrite(clazz, mediaType);
//			if (!canWrite) {
//				canWrite = clazz.isAssignableFrom(String.class);
//			}
//			return canWrite;
//		}
//
//		@Override
//		protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage)
//				throws IOException, HttpMessageNotWritableException {
//			if (object != null && object instanceof String) {
//				outputMessage.getHeaders().setContentType(MediaType.TEXT_PLAIN);;
//				shmc.write((String)object, MediaType.TEXT_PLAIN, outputMessage);
//				return;
//			}
//			outputMessage.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
//			super.writeInternal(object, object != null ? object.getClass() : null, outputMessage);
//		}
//	}



	@Override
	public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
//		return methodParameter.hasMethodAnnotation(ResponseBody.class);
//		return !methodParameter.getMethod().getReturnType().isAssignableFrom(Void.TYPE)
//				&& converterType.isAssignableFrom(StringOrMappingJackson2HttpMessageConverter.class);
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		if(body instanceof Result){
			return body;
		}
		if(body instanceof String){
			return JSON.toJSONString(Result.sussces(body));
		}
		return Result.sussces(body);
	}

}
