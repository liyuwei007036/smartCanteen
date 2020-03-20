package com.smart.canteen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.canteen.dto.card.CardSearch;
import com.smart.canteen.entity.IcCard;
import com.smart.canteen.vo.CardVo;
import org.apache.ibatis.annotations.Mapper;

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
     * 分页查询卡列表
     *
     * @param page
     * @param search
     * @param b
     * @return
     */
    IPage<CardVo> selectPageVo(Page<?> page, CardSearch search, Boolean b);


}
