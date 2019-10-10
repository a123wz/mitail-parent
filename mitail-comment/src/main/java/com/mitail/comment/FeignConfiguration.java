package com.mitail.comment;

import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@Slf4j
public class FeignConfiguration {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new UserErrorDecoder();
    }
    /**
     * 自定义错误
     */
    public class UserErrorDecoder implements ErrorDecoder {
        private Logger logger = LoggerFactory.getLogger(getClass());

        @Override
        public Exception decode(String methodKey, Response response) {
            Exception exception = null;
            try {
                String json = Util.toString(response.body().asReader());
                log.error("fegin异常消息:{}",json);
                exception = new RuntimeException(json);
//                Result result =  JsonMapper.nonEmptyMapper().fromJson(json, Result.class);
//                // 业务异常包装成 HystrixBadRequestException，不进入熔断逻辑
//                if (!result.isSuccess()) {
//                    exception = new HystrixBadRequestException(result.getMessage());
//                }
            } catch (IOException ex) {
                logger.error(ex.getMessage(), ex);
            }
            return exception;
        }
    }
}
