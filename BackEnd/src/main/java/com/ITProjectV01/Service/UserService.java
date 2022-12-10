package com.ITProjectV01.Service;



import com.ITProjectV01.DataObject.UserData;
import java.util.List;


public interface UserService {

    // 按照ID查询
    public UserData findOne(Integer UserID);

    //查询所有
    public List<UserData> findAll();

    //更新传入数据信息进行更新
    public UserData save(UserData userData);

    //删除数据
    public void deleteByID(Integer userID);

}
