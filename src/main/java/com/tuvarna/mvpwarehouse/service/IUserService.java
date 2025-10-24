package com.tuvarna.mvpwarehouse.service;

import com.tuvarna.mvpwarehouse.dto.RegisterRequest;
import com.tuvarna.mvpwarehouse.model.User;

public interface IUserService {
    User register(RegisterRequest request);
    User getByUsername(String username);
    boolean exists(String username);
}
