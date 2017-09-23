package com.mattel.fuhu.nabi.queenhead.view.json;

import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * </p>
 * <p>
 * Inception date: 2017-09-15
 * </p>
 * <p>
 * Copyright &copy; 2017 Fuhu, Inc. All rights reserved.
 * </p>
 *
 * @author Jerry Chen (<a href="jerry.chen@fuhu.com">jerry.chen@fuhu.com</a>)
 */
public class LoginRequestBody {

    @ApiModelProperty(value = "User's account name", required = true)
    private String account;

    @ApiModelProperty(value = "User's account password", required = true)
    private String password;

    public void setAccount(String account){
        this.account = account;
    }

    public String getAccount(){
        return account;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }
}
