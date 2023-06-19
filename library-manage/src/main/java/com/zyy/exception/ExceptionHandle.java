package com.zyy.exception;


import cn.hutool.core.util.StrUtil;
import com.zyy.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = ServiceException.class)
    public R serviceExceptionError(ServiceException e) {
        log.error("业务异常", e);
        String code = e.getCode();
        if (StrUtil.isNotBlank(code)) {
            return R.fail(code, e.getMessage());
        }
        return R.fail(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public R exceptionError(Exception e) {
        log.error("系统错误", e);
        return R.fail("系统错误");
    }
}
