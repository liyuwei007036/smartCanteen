package com.smart.canteen.mapper;

import live.lumia.dto.Account;
import com.smart.canteen.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 岗位 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2020-03-02
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 逻辑删除
     *
     * @param account
     * @param eid
     */
    void logicDeleted(@Param("account") Account account, @Param("eid") Long eid);

}
