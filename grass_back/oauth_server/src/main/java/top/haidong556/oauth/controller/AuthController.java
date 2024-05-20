package top.haidong556.oauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.haidong556.chat_server.common.exception.BaseException;
import top.haidong556.chat_server.common.exception.CustomExceptionType;
import top.haidong556.chat_server.common.exception.oauth.OauthJwtException;
import top.haidong556.chat_server.common.exception.oauth.OauthUserException;
import top.haidong556.chat_server.common.response.BaseResponse;
import top.haidong556.chat_server.common.response.ExceptionResponse;
import top.haidong556.chat_server.common.response.SuccessResponse;
import top.haidong556.oauth.dto.JwtRequest;
import top.haidong556.oauth.entity.Jwt;
import top.haidong556.oauth.entity.MyUser;
import top.haidong556.oauth.security.key.JwtFactory;
import top.haidong556.oauth.service.UserService;


@RestController
@RequestMapping("/jwt")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/verify")
    public BaseResponse verifyToken(@RequestBody Jwt jwt) throws OauthUserException {

             if(JwtFactory.verify(jwt.toString())==true){
                 return new SuccessResponse("true");
             }
            return new ExceptionResponse(new BaseException(new CustomExceptionType(923,"token verify fail")));
    }

    @PostMapping("/createJwt")
    public BaseResponse<String> createJwtByUserName(@RequestBody JwtRequest request) throws OauthUserException{
        String requestUserName = request.getUserName();
        String requestUserEmail = request.getEmail();

        MyUser user=null;

        if (requestUserName == null && requestUserEmail == null)
            throw new OauthJwtException(OauthJwtException.CreateJwtExceptionType.REQUEST_USERNAME_EMAIL_BOTH_NULL);
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("userName and email both null");

        if (requestUserName != null) {
             user = userService.getUserByUserName(request.getUserName());
        }
        else {
            user = userService.getUserByEmail(requestUserEmail);
        }
        if (user == null)
            throw new OauthJwtException(OauthJwtException.CreateJwtExceptionType.USER_NOT_FOUND);
        if (user.getPassword().equals(request.getPassWord()) == false)
            throw new OauthJwtException(OauthJwtException.CreateJwtExceptionType.USER_PASSWORD_WRONG);

        Jwt jwt = JwtFactory.createJwt(user, request.getRequester(), request.getAuthorities());
        return new SuccessResponse("\"jwt\":\""+jwt.toString()+"\"");
    }



}
