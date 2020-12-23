package com.smart.canteen.mapper;

import live.lumia.dto.Account;
import com.smart.canteen.entity.Machine;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 卡机 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2020-03-25
 */
@Mapper
public interface MachineMapper extends BaseMapper<Machine> {
    /**
     * 逻辑删除
     *
     * @param account
     * @param id
     */
    void logicDeleted(@Param("account") Account account, @Param("id") Long id);
}
