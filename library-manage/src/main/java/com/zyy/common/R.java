package com.zyy.common;

import lombok.Data;

@Data
public class R {
        private static final String SUCCESS_CODE = "200";
        private static final String ERROR_CODE = "-1";

        private String code;
        private Object data;
        private String msg;

        public static R OK() {
            R r = new R();
            r.setCode(SUCCESS_CODE);
            return r;
        }

        public static R OK(Object data) {
            R r = new R();
            r.setCode(SUCCESS_CODE);
            r.setData(data);
            return r;
        }

        public static R fail(String msg) {
            R r = new R();
            r.setCode(ERROR_CODE);
            r.setMsg(msg);
            return r;
        }

        public static R fail(String code, String msg) {
            R r = new R();
            r.setCode(code);
            r.setMsg(msg);
            return r;
        }
}
