package com.smart.canteen.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.core.error.BaseException;
import com.lc.core.utils.ModelMapperUtils;
import com.smart.canteen.dto.CommonList;
import com.smart.canteen.dto.recharge.RechargeLogSearch;
import com.smart.canteen.entity.RechargeLog;
import com.smart.canteen.enums.CanteenExceptionEnum;
import com.smart.canteen.mapper.RechargeLogMapper;
import com.smart.canteen.service.IRechargeLogService;
import com.smart.canteen.vo.RechargeLogVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 充值记录 服务实现类
 * </p>
 *
 * @author lc
 * @since 2020-03-08
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class RechargeLogServiceImpl extends ServiceImpl<RechargeLogMapper, RechargeLog> implements IRechargeLogService {

    @Override
    public void addRechargeLogs(List<RechargeLog> logs) {
        boolean b = saveBatch(logs);
        logs.clear();
        if (!b) {
            throw new BaseException(CanteenExceptionEnum.RECHARGE_FAIL);
        }
    }

    @Override
    public CommonList<RechargeLogVO> listLogs(RechargeLogSearch search) {
        Page<RechargeLog> voPage = new Page<>(search.getPage(), search.getSize());
        page(voPage, Wrappers.<RechargeLog>lambdaQuery()
                .eq(StringUtils.isNotEmpty(search.getCardNo()), RechargeLog::getCardNo, search.getCardNo())
                .eq(StringUtils.isNotEmpty(search.getEmpName()), RechargeLog::getEmployeeName, search.getEmpName())
                .eq(StringUtils.isNotEmpty(search.getEmpNo()), RechargeLog::getEmployeeNo, search.getEmpNo())
                .eq(search.getRechargeType() != null, RechargeLog::getType, search.getRechargeType())
                .eq(StringUtils.isNotEmpty(search.getOperatorName()), RechargeLog::getCreatorName, search.getOperatorName())
                .ge(search.getStart() != null, RechargeLog::getCreateTime, search.getStart())
                .lt(search.getEnd() != null, RechargeLog::getCreateTime, search.getEnd())
                .orderByDesc(RechargeLog::getCreateTime)
        );
        List<RechargeLogVO> collect = voPage.getRecords().stream().map(x -> ModelMapperUtils.strict(x, RechargeLogVO.class)).collect(Collectors.toList());
        return new CommonList<>(voPage.hasNext(), voPage.getTotal(), voPage.getCurrent(), collect);
    }
}
