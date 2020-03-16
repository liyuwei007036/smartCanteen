package com.smart.canteen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.canteen.entity.OperationLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 操作日志 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2020-03-16
 */
@Mapper
public interface OperationLogMapper extends BaseMapper<OperationLog> {

}
