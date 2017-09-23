package com.mattel.fuhu.nabi.queenhead.view.json;

import com.mattel.fuhu.nabi.queenhead.entity.Role;
import com.mattel.fuhu.playform.common.auth.entity.BaseRequestBody;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

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
public class CreateUserRequestBody extends BaseRequestBody {

    @NotBlank(message = "account is required.")
    private String account;

    @NotBlank(message = "password is required.")
    private String password;

    @NotNull(message = "role must not be null.")
    private Role role;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

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
