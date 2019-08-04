package com.imocc.miaosha.Redis;

/**
 * @author Dyson
 * @date 2019/8/4 15:38
 */
public class UserKey extends BasePrefix{

    private UserKey( String prefix) {
        super(prefix);
    }
    public static UserKey getById=new UserKey("id");
    public static UserKey getByName=new UserKey("name");

}
