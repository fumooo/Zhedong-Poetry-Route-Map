package com.fxl.zhedong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxl.zhedong.entity.Admin;
import com.fxl.zhedong.entity.City;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminMapper extends BaseMapper<Admin> {
    List<Admin> queryAdmin();
    IPage<Admin> queryAdminPage(Page<Admin> page);
    int addAdmin(Admin admin);//添加管理员信息
    int updateAdmin(Admin admin);//修改管理员信息
    int delectAdmin(@Param("id") Integer id);//删除管理员信息
    int delectAdmins(@Param("eids") List<Integer> eids);//批量删除管理员信息

    Admin getAdminByAccountAndPassword(@Param("account") String account, @Param("password") String password);//根据前台传过来的登陆信息进行查询

    int updateAdminByNewPassword(@Param("account") String account, @Param("password") String password);

}
