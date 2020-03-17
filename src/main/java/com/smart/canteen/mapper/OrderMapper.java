package com.smart.canteen.mapper;

import com.smart.canteen.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 充值记录 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2020-03-08
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 统计一天
     *
     * @param begin
     * @param end
     * @param min
     * @return
     */
    List<String> summaryOrderNum(@Param("begin") Date begin, @Param("end") Date end, @Param("min") int min);

    /**
     * 统计年销售额
     *
     * @param begin
     * @param end
     * @return
     */
    List<Map<String, Object>> summaryYearSale(Date begin, Date end);
}
