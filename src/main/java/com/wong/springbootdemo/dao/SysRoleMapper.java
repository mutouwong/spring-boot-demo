package com.wong.springbootdemo.dao;

import com.wong.springbootdemo.model.SysRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description: TODO
 * @Author: gang.wang
 * @Date: Created in 下午3:43 2018/2/5
 */
@Mapper
public interface SysRoleMapper {

    /**
     * 查询
     * @return
     */
    @Select("select id,name,description from sys_role_wg")
    List<SysRole> selectAll();

    /**
     * 新增
     * @param role
     */
    @Insert("insert into sys_role_wg (name,seq,isdefault) values(#{name},#{seq},#{isdefault})")
    void addSysRole(SysRole role);

}
