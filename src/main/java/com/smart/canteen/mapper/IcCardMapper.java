package com.smart.canteen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.canteen.entity.IcCard;
import com.smart.canteen.vo.CardVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * iC卡 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2020-03-03
 */
@Mapper
public interface IcCardMapper extends BaseMapper<IcCard> {

    /**
     * 通过卡号查询
     *
     * @param no
     * @return
     */
    CardVo getByNo(@Param("no") String no);
}
