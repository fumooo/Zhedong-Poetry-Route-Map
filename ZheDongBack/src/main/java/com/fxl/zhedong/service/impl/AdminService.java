package com.fxl.zhedong.service.impl;

import com.fxl.zhedong.entity.Admin;
import com.fxl.zhedong.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;

    public int save(Admin admin){
        if(admin.getId() == null){
            return adminMapper.addAdmin(admin);
        }
        else {
            return adminMapper.updateAdmin(admin);
        }
    }

//    public boolean login(UserDTO userDTO) {
//        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("account",userDTO.getAccount());
//        queryWrapper.eq("password",userDTO.getPassword());
//        Admin one = getOne(queryWrapper);
//        return one != null;
//    }

//    @Override
//    public boolean login(UserDTO userDTO) {
//    }
}
