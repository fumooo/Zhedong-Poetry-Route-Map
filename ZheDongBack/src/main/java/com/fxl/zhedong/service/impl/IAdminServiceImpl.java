package com.fxl.zhedong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxl.zhedong.entity.Admin;
import com.fxl.zhedong.mapper.AdminMapper;
import com.fxl.zhedong.service.AdminService;
import com.fxl.zhedong.service.IAdminService;
import org.springframework.stereotype.Service;
/*
   用于进行用户登陆的操作
* */
@Service
public class IAdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

}
