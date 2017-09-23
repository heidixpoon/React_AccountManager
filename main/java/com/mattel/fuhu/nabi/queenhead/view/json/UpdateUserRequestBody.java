package com.mattel.fuhu.nabi.queenhead.view.json;

import com.mattel.fuhu.nabi.queenhead.entity.Role;
import com.mattel.fuhu.playform.common.auth.entity.BaseRequestBody;

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
public class UpdateUserRequestBody extends BaseRequestBody {

    private String password;

    private Role role;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
