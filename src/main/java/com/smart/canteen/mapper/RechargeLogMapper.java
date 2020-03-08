package com.smart.canteen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.canteen.dto.recharge.RechargeLogSearch;
import com.smart.canteen.entity.RechargeLog;
import com.smart.canteen.vo.RechargeLogVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 充值记录 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2020-03-08
 */
@Mapper
public interface RechargeLogMapper extends BaseMapper<RechargeLog> {

    /**
     * 查询充值记录
     *
     * @param page
     * @param search
     * @return
     */
    IPage<RechargeLogVO> pageVo(Page<?> page, RechargeLogSearch search);
}
