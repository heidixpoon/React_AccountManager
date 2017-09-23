package com.mattel.fuhu.nabi.queenhead.view.json;

import com.mattel.fuhu.nabi.queenhead.entity.Role;
import com.mattel.fuhu.nabi.queenhead.entity.Session;

/**
 * <p>
 * </p>
 * <p>
 * Inception date: 2017-09-19
 * </p>
 * <p>
 * Copyright &copy; 2017 Fuhu, Inc. All rights reserved.
 * </p>
 *
 * @author Jerry Chen (<a href="jerry.chen@fuhu.com">jerry.chen@fuhu.com</a>)
 */
public class LoginResponseBody {
    private String sessionId;
    private Role role;

    public LoginResponseBody(Session session){
        this.sessionId = session.getSessionId();
        this.role = session.getUser().getRole();
    }

    public void setSessionId(String sessionId){
        this.sessionId = sessionId;
    }

    public String getSessionId(){
        return sessionId;
    }

    public void setRole(Role role){
        this.role = role;
    }

    public Role getRole(){
        return role;
    }
}
