package com.smart.canteen.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import live.lumia.error.BaseException;
import live.lumia.utils.MathUtil;
import live.lumia.utils.ModelMapperUtils;
import live.lumia.utils.ObjectUtil;
import com.smart.canteen.dto.CommonList;
import com.smart.canteen.dto.RechargeSummaryDTO;
import com.smart.canteen.dto.recharge.RechargeLogSearch;
import com.smart.canteen.entity.RechargeLog;
import com.smart.canteen.enums.CanteenExceptionEnum;
import com.smart.canteen.enums.RechargeTypeEnum;
import com.smart.canteen.mapper.RechargeLogMapper;
import com.smart.canteen.service.IRechargeLogService;
import com.smart.canteen.vo.RechargeLogVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

    @Override
    public RechargeSummaryDTO getRechargeTotal(Date start, Date end) {
        List<RechargeLog> list = list(Wrappers.<RechargeLog>lambdaQuery()
                .select(RechargeLog::getMoney, RechargeLog::getType)
                .ge(RechargeLog::getCreateTime, start)
                .lt(RechargeLog::getCreateTime, end));

        Double normal = list.parallelStream()
                .filter(x -> x.getType().equals(RechargeTypeEnum.NORMAL))
                .map(RechargeLog::getMoney)
                .reduce((x, y) -> MathUtil.add(ObjectUtil.getDouble(x), ObjectUtil.getDouble(y)))
                .orElse(0d);

        Double refund = list.parallelStream()
                .filter(x -> x.getType().equals(RechargeTypeEnum.REFUND))
                .map(RechargeLog::getMoney)
                .reduce((x, y) -> MathUtil.add(ObjectUtil.getDouble(x), ObjectUtil.getDouble(y)))
                .orElse(0d);

        return new RechargeSummaryDTO(normal, refund);
    }
}
