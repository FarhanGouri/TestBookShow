package org.example.Service;

import org.example.model.User.User;
import org.example.request.UserRequestDto;
import org.example.response.RestResponse;

public interface UserService {

    public RestResponse register(UserRequestDto userRequestDto);

    public RestResponse login(UserRequestDto userRequestDto);

    public RestResponse getUserDeatils(long userId);
}
