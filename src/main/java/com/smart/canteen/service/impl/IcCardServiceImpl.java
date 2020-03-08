package com.smart.canteen.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.core.dto.Account;
import com.lc.core.error.BaseException;
import com.lc.core.utils.MathUtil;
import com.lc.core.utils.ModelMapperUtils;
import com.lc.core.utils.ObjectUtil;
import com.lc.core.utils.ValidatorUtil;
import com.smart.canteen.dto.CommonList;
import com.smart.canteen.dto.card.CardForm;
import com.smart.canteen.dto.card.CardSearch;
import com.smart.canteen.dto.recharge.RechargeForm;
import com.smart.canteen.entity.Employee;
import com.smart.canteen.entity.IcCard;
import com.smart.canteen.entity.RechargeLog;
import com.smart.canteen.enums.*;
import com.smart.canteen.mapper.IcCardMapper;
import com.smart.canteen.service.IIcCardService;
import com.smart.canteen.service.IOrderService;
import com.smart.canteen.service.IRechargeLogService;
import com.smart.canteen.utils.EntityLogUtil;
import com.smart.canteen.vo.CardVo;
import com.smart.canteen.vo.ResponseMsg;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * iC卡 服务实现类
 * </p>
 *
 * @author lc
 * @since 2020-03-03
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class IcCardServiceImpl extends ServiceImpl<IcCardMapper, IcCard> implements IIcCardService {

    @Override
    public Long addCard(CardForm form, Employee employee, Account create) {
        ValidatorUtil.validator(form, CardForm.Insert.class);
        IcCard card = getByCode(form.getNo());
        if (card != null) {
            throw new BaseException(CanteenExceptionEnum.CARD_NO_REPEAT);
        }
        card = ModelMapperUtils.strict(form, IcCard.class);
        card.setStatus(CardStatusEnum.ENABLE);
        card.setAccountStatus(CardAccountEnum.NORMAL);
        card.setEmployeeId(employee.getId());
        card.setEmployeeNo(employee.getNo());
        card.setEmployeeName(employee.getName());
        EntityLogUtil.addNormalUser(card, create);
        boolean save = save(card);
        if (!save) {
            throw new BaseException(CanteenExceptionEnum.CREATE_FAIL);
        }
        return card.getId();
    }

    @Override
    public IcCard getByCode(String no) {
        return getOne(Wrappers.<IcCard>lambdaQuery().eq(IcCard::getNo, no), false);
    }

    @Override
    public IcCard getById(Long id) {
        return super.getById(id);
    }

    @Override
    public void update(CardForm form, Account create) {
        ValidatorUtil.validator(form, CardForm.Update.class);
        IcCard card = getById(form.getId());
        if (card == null) {
            throw new BaseException(CanteenExceptionEnum.CARD_NO_EXIST);
        }
        BeanUtils.copyProperties(form, card, "id", "no");
        boolean b = updateById(card);
        if (!b) {
            throw new BaseException(CanteenExceptionEnum.UPDATE_FAIL);
        }
    }

    @Override
    public CommonList<CardVo> listCard(CardSearch search) {
        Page<CardVo> page = new Page<>(search.getPage(), search.getSize());
        getBaseMapper().selectPageVo(page, search);
        return new CommonList<>(page.hasNext(), page.getTotal(), page.getCurrent(), page.getRecords());
    }


    @Autowired
    private IRechargeLogService iRechargeLogService;

    @Override
    public void recharge(RechargeForm form, Account account) {
        ValidatorUtil.validator(form);
        List<Long> cardIds = form.getCardIds();
        Collection<IcCard> cards = listByIds(cardIds);
        List<RechargeLog> logs = new ArrayList<>();
        cards.forEach(x -> {
            RechargeLog rechargeLog = new RechargeLog();
            double lastBalance = MathUtil.add(ObjectUtil.getDouble(x.getCurrentBalance()), ObjectUtil.getDouble(form.getMoney()));
            x.setCurrentBalance(lastBalance);
            rechargeLog.setMoney(form.getMoney());
            rechargeLog.setCardId(x.getId());
            rechargeLog.setCardNo(x.getNo());
            rechargeLog.setType(RechargeTypeEnum.getByCode(form.getRechargeType()));
            rechargeLog.setBalance(lastBalance);
            rechargeLog.setEmployeeName(x.getEmployeeName());
            rechargeLog.setEmployeeNo(x.getEmployeeNo());
            EntityLogUtil.addNormalUser(rechargeLog, account);
            logs.add(rechargeLog);
        });
        boolean b = updateBatchById(cards);
        cardIds.clear();
        cards.clear();
        if (b) {
            iRechargeLogService.addRechargeLogs(logs);
        } else {
            throw new BaseException(CanteenExceptionEnum.RECHARGE_FAIL);
        }
    }

    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;
    @Autowired
    private TransactionDefinition transactionDefinition;

    @Override
    public ResponseMsg deductions(String cardNo, Integer money) {
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        IcCard card = getOne(Wrappers.<IcCard>lambdaQuery()
                        .select(IcCard::getCurrentBalance, IcCard::getStatus, IcCard::getId, IcCard::getNo, IcCard::getEmployeeName, IcCard::getEmployeeNo)
                        .eq(IcCard::getNo, cardNo),
                false);
        if (card == null) {
            return new ResponseMsg(CmdCodeEnum.CON, Voices.INVALID, cardNo, "无效卡!");
        }
        if (card.getStatus() == CardStatusEnum.DISABLE) {
            return new ResponseMsg(CmdCodeEnum.CON, Voices.LOSS, cardNo, "挂失卡!");
        }
        Double currentBalance = card.getCurrentBalance();
        Double lastBalance = MathUtil.sub(currentBalance, MathUtil.div(money, 100, 2));
        if (lastBalance < 0) {
            return new ResponseMsg(CmdCodeEnum.CON, Voices.NOT, cardNo, "余额不足!");
        }
        // 是否需要加入其他判断
        boolean update = update(Wrappers.<IcCard>lambdaUpdate()
                .set(IcCard::getCurrentBalance, lastBalance)
                .eq(IcCard::getId, card.getId()));
        boolean saveOrder = iOrderService.addOrderForMachine(card, MathUtil.div(money, 100, 2));
        ResponseMsg msg;
        if (update && saveOrder) {
            msg = new ResponseMsg(CmdCodeEnum.CON, Voices.SUCCESS, cardNo, lastBalance, money);
            dataSourceTransactionManager.commit(transactionStatus);
        } else {
            msg = new ResponseMsg(CmdCodeEnum.CON, Voices.NOT, cardNo, "刷卡太快请重刷");
            dataSourceTransactionManager.rollback(transactionStatus);
        }
        return msg;
    }


    @Override
    public ResponseMsg search(String cardNo) {
        IcCard card = getOne(Wrappers.<IcCard>lambdaQuery()
                        .eq(IcCard::getNo, cardNo),
                false);
        if (card == null) {
            return new ResponseMsg(CmdCodeEnum.CON, Voices.WELCOME, cardNo, "无效卡");
        }
        if (card.getStatus() == CardStatusEnum.DISABLE) {
            return new ResponseMsg(CmdCodeEnum.CON, Voices.LOSS, cardNo, "挂失卡!");
        }
        return new ResponseMsg(CmdCodeEnum.CON, Voices.THANKS, cardNo, MathUtil.mul(card.getCurrentBalance(), 1, 2));
    }


}
