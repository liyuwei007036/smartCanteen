package com.smart.canteen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lc.core.dto.Account;
import com.smart.canteen.entity.Origination;
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
     * @param path
     * @param id
     */
    void logicDeleted(@Param("account") Account account, @Param("path") String path, Long id);

}
