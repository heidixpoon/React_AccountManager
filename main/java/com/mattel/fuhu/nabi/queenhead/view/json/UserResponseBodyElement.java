package com.mattel.fuhu.nabi.queenhead.view.json;

import com.mattel.fuhu.nabi.queenhead.entity.Role;

/**
 * <p>
 * </p>
 * <p>
 *     Inception date: 2017-09-15
 * </p>
 * <p>
 *     Copyright &copy; 2017 Fuhu, Inc. All rights reserved.
 * </p>
 *
 * @author Hudson Wang (<a href="hudson.wang@fuhu.com">hudson.wang@fuhu.com</a>)
 *
 */
public class UserResponseBodyElement {

    private String account;
    private Role role;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
