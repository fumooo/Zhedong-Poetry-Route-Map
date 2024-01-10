package com.fxl.zhedong.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxl.zhedong.entity.Admin;

import com.fxl.zhedong.mapper.AdminMapper;
import com.fxl.zhedong.service.impl.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class AdminController {
    @Autowired
    private AdminMapper adminMapper;
    @Resource
    private AdminService adminService;
    @GetMapping("/queryAdmin")
    public List queryAdmin(){
        List<Admin> admins = adminMapper.queryAdmin();
        return admins;
    }
    @GetMapping("/queryAdminPage")
    public Map list(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        Map<String, Object> AdminMap = new HashMap<>();
        Page<Admin> page = new Page<>(pageNum, pageSize);
        IPage<Admin> data = adminMapper.queryAdminPage(page);
        AdminMap.put("count", data.getTotal());
        AdminMap.put("data", data.getRecords());
        return AdminMap;
    }
    @PostMapping("/saveAdmin")
    public int save(@RequestBody Admin admin){
        System.out.println(admin);
        return adminService.save(admin);
    }
    @DeleteMapping("/Admin/{id}")
    public int delete(@PathVariable Integer id){
        return adminMapper.delectAdmin(id);
    }
    @PostMapping("/Admin/batch")
    public int deleteBatch(@RequestBody List<Integer> eids){
        return adminMapper.delectAdmins(eids);
    }

    @PostMapping("/login")
    public Admin login(@RequestBody Admin admin){
        Admin dbadmin = adminMapper.getAdminByAccountAndPassword(admin.getAccount(),admin.getPassword());
        System.out.println(dbadmin);
        return dbadmin;
    }

    @PostMapping("/password")
    public int password(@RequestParam String account,@RequestParam String password){

        return adminMapper.updateAdminByNewPassword(account,password);

    }


}
