package com.smart.canteen.mapper;

import com.lc.core.dto.Account;
import com.smart.canteen.entity.Origination;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 组织 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2020-03-02
 */
@Mapper
public interface OriginationMapper extends BaseMapper<Origination> {

    /**
     * 逻辑删除
     *
     * @param account
     * @param eid
     */
    void logicDeleted(@Param("account") Account account, @Param("id") Long id);

}
