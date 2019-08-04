package com.imocc.miaosha.Redis;

/**
 * @author Dyson
 * @date 2019/8/4 15:34
 */
public abstract class BasePrefix implements KeyPrefix {

    private int expireSeconds;
    private String prefix;

    //默认使用0代表过期日期
    public BasePrefix(String prefix){
        this(0,prefix);
    }
    public BasePrefix(int expireSeconds,String prefix){
        this.expireSeconds=expireSeconds;
        this.prefix=prefix;
    }
    @Override
    public int expireSeconds() { //默认0代表永不过期
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        //获取类名
        String sclassName=getClass().getSimpleName();
        return sclassName+":"+prefix;
    }
}
