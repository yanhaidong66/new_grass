package top.haidong556.oauth.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.haidong556.oauth.entity.MyUser;

@Mapper
public interface UserMapper {
    public MyUser getUserByUserName(@Param("userName") String userName)throws Exception;
    public MyUser getUserByEmail(@Param("email") String email)throws Exception;
    public MyUser getUserById(@Param("id") long id)throws Exception;
    public int addUser(MyUser user)throws Exception;
    public int deleteUser(@Param("userName") String userName)throws Exception;
    public int updatePasswordByUserName(@Param("userName") String userName,@Param("password") String password)throws Exception;
}
