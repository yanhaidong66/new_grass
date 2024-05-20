package top.haidong556.chat.gateway.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.haidong556.chat.gateway.entity.UserServerMapping;
@Mapper
public interface MappingMapper {
    public UserServerMapping getMappingByUserName(@Param("userName") String userName);
    public UserServerMapping getMappingByUserId(@Param("userId") long userId);
    public int addUserServerMapping(UserServerMapping userServerMapping);
    public int countUsersUsingChatServer(@Param("serverId") int serverId);
}
