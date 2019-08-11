package com.imocc.miaosha.validator;

import  javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.imocc.miaosha.util.VaildatorUtil;
import org.apache.commons.lang3.StringUtils;


/**
 * 校验器
 * @author Dyson
 * @date 2019/8/11 12:03
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile,String> {

    private boolean required = false;
    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(required){
            return VaildatorUtil.isMobile(value);
        }else {
            if(StringUtils.isEmpty(value)){
                return true;
            }else {
                return VaildatorUtil.isMobile(value);
            }
        }
    }
}
