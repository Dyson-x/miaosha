package com.imocc.miaosha.exception;

import com.imocc.miaosha.result.CodeMsg;

/**
 * d定义一个全局异常
 * @author Dyson
 * @date 2019/8/11 16:35
 */
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CodeMsg getCm() {
        return cm;
    }

    private CodeMsg cm;
    public GlobalException(CodeMsg cm){
        super(cm.toString());
        this.cm=cm;
    }


}
