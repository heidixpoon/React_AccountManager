package com.mattel.fuhu.nabi.queenhead.controller;

import com.mattel.fuhu.nabi.queenhead.common.exception.user.UserInfoLackingException;
import com.mattel.fuhu.nabi.queenhead.entity.Role;
import com.mattel.fuhu.nabi.queenhead.entity.User;
import com.mattel.fuhu.nabi.queenhead.service.UserService;
import com.mattel.fuhu.nabi.queenhead.view.json.CreateUserRequestBody;
import com.mattel.fuhu.nabi.queenhead.view.json.UpdateUserRequestBody;
import com.mattel.fuhu.nabi.queenhead.view.json.UserResponseBodyElement;
import com.mattel.fuhu.playform.common.exception.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<UserResponseBodyElement> getAccountList() throws SystemException {
//        List<User> userList = userService.getAll();
        //TODO Mock Data for frontend test
        List<User> userList = new ArrayList<>();
        for (int i = 1 ; i <= 20; i++) {
            User user = new User();
            user.setAccount("yingray+" + i + "@fuhu.com");
            user.setRole((i%3 == 0)? Role.ADMIN : Role.NORMAL);
            userList.add(user);
        }
        return convertUserListToUserResponseList(userList);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void createUserAccount(@Valid @RequestBody CreateUserRequestBody requestBody, Errors errors) throws SystemException {
        checkforErrors(errors);
        User user = convertCreateUserRequestBodyToUser(requestBody);
        userService.createUser(user);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public User getUserAccount(@PathVariable("userId") String userId) throws SystemException {
        return userService.getUser(userId);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public void updateUserAccount(@PathVariable("userId") String userId, @Valid @RequestBody UpdateUserRequestBody requestBody, Errors errors) throws SystemException {
        checkforErrors(errors);
        User user = convertUpdateUserRequestBodyToUser(requestBody);
        user.setAccount(userId);
        userService.updateUser(user);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public void deleteUserAccount(@PathVariable("userId") String userId) throws SystemException {
        userService.deleteUser(userId);
    }

    private void checkforErrors(Errors errors) {
        if (errors.hasErrors()) {
            String messages = errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(" , "));
            throw new UserInfoLackingException(messages);
        }
    }

    private List<UserResponseBodyElement> convertUserListToUserResponseList(List<User> userList) {
        List<UserResponseBodyElement> userResponseBody = new ArrayList<>();
        for (User user : userList) {
            UserResponseBodyElement response = new UserResponseBodyElement();
            response.setAccount(user.getAccount());
            response.setRole(user.getRole());
            userResponseBody.add(response);
        }
        return userResponseBody;
    }

    private User convertCreateUserRequestBodyToUser(CreateUserRequestBody requestBody) {
        User user = new User();
        user.setAccount(requestBody.getAccount());
        user.setPassword(requestBody.getPassword());
        user.setRole(requestBody.getRole());
        return user;
    }

    private User convertUpdateUserRequestBodyToUser(UpdateUserRequestBody requestBody) {
        User user = new User();
        if (requestBody.getPassword() != null) {
            user.setPassword(requestBody.getPassword());
        }
        if (requestBody.getRole() != null) {
            user.setRole(requestBody.getRole());
        }
        return user;
    }
}
