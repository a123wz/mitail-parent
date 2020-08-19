package com.mitail.util;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import lombok.Data;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import com.google.common.collect.Lists;

public class ParamInvlidUtil {

    @Data
    public static class ParamErrorMessage{
        /**
         * 无效字段的名称
         */
        private String fieldName;

        /**
         * 错误信息
         */
        private String message;
    }

    public static List<ParamErrorMessage> convertErrorMessage(Set<ConstraintViolation<?>> cvset) {
        if (CollectionUtils.isEmpty(cvset)) {
            return null;
        }

        List<ParamErrorMessage> parameterInvalidItemList = Lists.newArrayList();
        for (ConstraintViolation<?> cv : cvset) {
            ParamErrorMessage parameterInvalidItem = new ParamErrorMessage();
            String propertyPath = cv.getPropertyPath().toString();
            if (propertyPath.indexOf(".") != -1) {
                String[] propertyPathArr = propertyPath.split("\\.");
                parameterInvalidItem.setFieldName(propertyPathArr[propertyPathArr.length - 1]);
            } else {
                parameterInvalidItem.setFieldName(propertyPath);
            }
            parameterInvalidItem.setMessage(cv.getMessage());
            parameterInvalidItemList.add(parameterInvalidItem);
        }

        return parameterInvalidItemList;
    }

//    public static List<ParamErrorMessage> convertCVSetToParameterInvalidItemList(Set<ConstraintViolation<?>> cvset) {
//        if (CollectionUtils.isEmpty(cvset)) {
//            return null;
//        }
//
//        List<ParamErrorMessage> parameterInvalidItemList = Lists.newArrayList();
//        for (ConstraintViolation<?> cv : cvset) {
//            ParamErrorMessage parameterInvalidItem = new ParamErrorMessage();
//            String propertyPath = cv.getPropertyPath().toString();
//            if (propertyPath.indexOf(".") != -1) {
//                String[] propertyPathArr = propertyPath.split("\\.");
//                parameterInvalidItem.setFieldName(propertyPathArr[propertyPathArr.length - 1]);
//            } else {
//                parameterInvalidItem.setFieldName(propertyPath);
//            }
//            parameterInvalidItem.setMessage(cv.getMessage());
//            parameterInvalidItemList.add(parameterInvalidItem);
//        }
//
//        return parameterInvalidItemList;
//    }

    public static List<ParamErrorMessage> convertBindingResultMessage(BindingResult bindingResult) {
        if (bindingResult == null) {
            return null;
        }

        List<ParamErrorMessage> parameterInvalidItemList = Lists.newArrayList();

        List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrorList) {
            ParamErrorMessage parameterInvalidItem = new ParamErrorMessage();
            parameterInvalidItem.setFieldName(fieldError.getField());
            parameterInvalidItem.setMessage(fieldError.getDefaultMessage());
            parameterInvalidItemList.add(parameterInvalidItem);
        }

        return parameterInvalidItemList;
    }

}
