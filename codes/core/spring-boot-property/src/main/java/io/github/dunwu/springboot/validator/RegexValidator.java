package io.github.dunwu.springboot.validator;

import io.github.dunwu.springboot.annotation.RegexValid;
import io.github.dunwu.tool.util.RegexUtil;
import io.github.dunwu.tool.util.StringUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 正则校验器，配合 {@link RegexValid} 使用
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2020-03-17
 */
public class RegexValidator implements ConstraintValidator<RegexValid, String> {

    private String regexp;

    @Override
    public void initialize(RegexValid constraintAnnotation) {
        regexp = constraintAnnotation.regexp();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        if (StringUtil.isBlank(s)) {
            return false;
        }
        if (StringUtil.isBlank(regexp)) {
            throw new IllegalArgumentException("regexp in @RegexValid must not be blank");
        }

        return RegexUtil.matches(s, regexp);
    }

}
