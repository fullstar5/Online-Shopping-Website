package com.ITProjectV01.DAO;


import com.ITProjectV01.DataObject.UserData;
import org.springframework.data.jpa.repository.JpaRepository;



// 创建Dao持久层
public interface UserDataDao extends JpaRepository<UserData,Integer>{

    // more.......?

}
