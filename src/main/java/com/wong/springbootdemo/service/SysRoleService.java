package com.wong.springbootdemo.service;

import com.wong.springbootdemo.exception.BusiException;
import com.wong.springbootdemo.model.SysRole;
import com.wong.springbootdemo.dao.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: TODO
 * @Author: gang.wang
 * @Date: Created in 下午5:39 2018/2/5
 */
@Service
public class SysRoleService {

    @Autowired
    private SysRoleMapper mapper;

    /**
     * 新增
     * @param role
     * @throws Exception
     */
    @Transactional
    public void addSysRole(SysRole role) throws Exception {
        mapper.addSysRole(role);
    }

    /**
     * 测试自定义异常
     * @throws Exception
     */
    public void testException() throws Exception {
        throw new BusiException("111","自定义异常");
    }

}
