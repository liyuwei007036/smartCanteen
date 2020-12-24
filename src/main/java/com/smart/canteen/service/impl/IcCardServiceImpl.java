package com.smart.canteen.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.canteen.dto.CommonList;
import com.smart.canteen.dto.card.CardForm;
import com.smart.canteen.dto.card.CardSearch;
import com.smart.canteen.dto.card.DeductionForm;
import com.smart.canteen.dto.card.PatchCardForm;
import com.smart.canteen.dto.recharge.RechargeForm;
import com.smart.canteen.entity.Employee;
import com.smart.canteen.entity.IcCard;
import com.smart.canteen.entity.Machine;
import com.smart.canteen.entity.RechargeLog;
import com.smart.canteen.enums.*;
import com.smart.canteen.mapper.IcCardMapper;
import com.smart.canteen.service.*;
import com.smart.canteen.utils.EntityLogUtil;
import com.smart.canteen.vo.CardUserVo;
import com.smart.canteen.vo.CardVo;
import com.smart.canteen.vo.ResponseMsg;
import live.lumia.dto.Account;
import live.lumia.error.BaseException;
import live.lumia.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

/**
 * <p>
 * iC卡 服务实现类
 * </p>
 *
 * @author lc
 * @since 2020-03-03
 */
@Slf4j
@Transactional(rollbackFor = Exception.class, timeout = 2000)
@Service
public class IcCardServiceImpl extends ServiceImpl<IcCardMapper, IcCard> implements IIcCardService {

    @Autowired
    private IEmployeeRoleService iEmployeeRoleService;

    @Autowired
    private IRechargeLogService iRechargeLogService;

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
        card.setCurrentBalance(ObjectUtil.getDouble(form.getOpenCardAmount()));
        EntityLogUtil.addNormalUser(card, create);
        boolean save = save(card);
        if (!save) {
            throw new BaseException(CanteenExceptionEnum.CREATE_FAIL);
        }
        if (ObjectUtil.getDouble(card.getOpenCardAmount()) > 0) {
            RechargeForm form1 = new RechargeForm();
            ArrayList<Long> objects = new ArrayList<>();
            objects.add(card.getId());
            form1.setCardIds(objects);
            form1.setMoney(ObjectUtil.getDouble(card.getOpenCardAmount()));
            form1.setRechargeType(RechargeTypeEnum.NORMAL.getValue());
            form1.setDescription("开户金额");
            recharge(form1, create);
        }
        return card.getId();
    }


    @Override
    public CardUserVo getCardUser(Long cardId) {
        if (ObjectUtil.getLong(cardId) < 1) {
            return null;
        }
        CardUserVo vo = getBaseMapper().getByCardId(cardId);
        vo.setRoleName(new HashSet<>(iEmployeeRoleService.getEmpRoleName(vo.getEmpId())));
        return vo;
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
    public void update(CardForm form, Employee employee, Account create) {
        ValidatorUtil.validator(form, CardForm.Update.class);
        IcCard card = getById(form.getId());
        if (card == null) {
            throw new BaseException(CanteenExceptionEnum.CARD_NO_EXIST);
        }
        BeanUtils.copyProperties(form, card, "id", "no");
        card.setEmployeeName(employee.getName());
        card.setEmployeeNo(employee.getNo());
        boolean b = updateById(card);
        if (!b) {
            throw new BaseException(CanteenExceptionEnum.UPDATE_FAIL);
        }
    }

    @Override
    public CommonList<CardVo> listCard(CardSearch search) {
        Page<CardVo> page = new Page<>(search.getPage(), search.getSize());
        getBaseMapper().selectPageVo(page, search, false);
        return new CommonList<>(page.hasNext(), page.getTotal(), page.getCurrent(), page.getRecords());
    }


    @Override
    public CommonList<CardVo> listCardForRecharge(CardSearch search) {
        Page<CardVo> page = new Page<>(search.getPage(), search.getSize());
        getBaseMapper().selectPageVo(page, search, true);
        return new CommonList<>(page.hasNext(), page.getTotal(), page.getCurrent(), page.getRecords());
    }


    @Override
    public void recharge(RechargeForm form, Account account) {
        ValidatorUtil.validator(form);
        List<Long> cardIds = form.getCardIds();
        Collection<IcCard> cards = listByIds(cardIds);
        boolean b1 = cards.stream().anyMatch(x -> x.getStatus() != CardStatusEnum.ENABLE);
        if (b1) {
            throw new BaseException(CanteenExceptionEnum.CARD_TYPE_ERROR1);
        }
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
            rechargeLog.setDescription(form.getDescription());
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

    @Value("${card.timeout}")
    private Long timeout;

    @Override
    public ResponseMsg deductions(String cardNo, Integer money, String machineNo) {
        Long start = System.currentTimeMillis();
        Machine machine = iMachineService.getByCode(machineNo);
        if (machine == null) {
            return new ResponseMsg(CmdCodeEnum.CON, Voices.WARN, cardNo, "机器未注册");
        }
        IcCard card = getOne(Wrappers.<IcCard>lambdaQuery()
                        .select(IcCard::getCurrentBalance, IcCard::getStatus, IcCard::getId, IcCard::getNo, IcCard::getEmployeeName, IcCard::getEmployeeNo)
                        .eq(IcCard::getNo, cardNo),
                false);
        if (card == null) {
            return new ResponseMsg(CmdCodeEnum.CON, Voices.INVALID, cardNo, "无效卡!");
        }
        if (card.getStatus() != CardStatusEnum.ENABLE) {
            return new ResponseMsg(CmdCodeEnum.CON, Voices.LOSS, cardNo, "挂失卡!");
        }
        if (card.getValidityTime() != null && card.getValidityTime().before(new Date())) {
            return new ResponseMsg(CmdCodeEnum.CON, Voices.INVALID, cardNo, "无效卡!");
        }
        double currentBalance = ObjectUtil.getDouble(card.getCurrentBalance());
        Double lastBalance = MathUtil.sub(currentBalance, MathUtil.div(money, 100, 2));
        if (lastBalance < 0) {
            return new ResponseMsg(CmdCodeEnum.CON, Voices.NOT, cardNo, "余额不足!");
        }
        // todo 是否需要加入其他判断
        boolean update = update(Wrappers.<IcCard>lambdaUpdate()
                .set(IcCard::getCurrentBalance, lastBalance)
                .eq(IcCard::getId, card.getId()));
        boolean saveOrder = iOrderService.addOrderForMachine(card, MathUtil.div(money, 100, 2), machineNo, lastBalance);
        ResponseMsg msg;
        Long end = System.currentTimeMillis();
        if (end - start > timeout) {
            msg = new ResponseMsg(CmdCodeEnum.CON, Voices.WARN, cardNo, "网络连接超时！");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } else {
            if (update && saveOrder) {
                msg = new ResponseMsg(CmdCodeEnum.CON, Voices.SUCCESS, cardNo, lastBalance, money);
                RedisUtil.getClient().getTopic("update").publish(cardNo);
            } else {
                msg = new ResponseMsg(CmdCodeEnum.CON, Voices.WARN, cardNo, "刷卡太快请重刷");
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        }
        return msg;
    }

    @Autowired
    private IMachineService iMachineService;

    @Override
    public ResponseMsg search(String cardNo, String machineNo) {
        Machine machine = iMachineService.getByCode(machineNo);
        if (machine == null) {
            return new ResponseMsg(CmdCodeEnum.CON, Voices.WARN, cardNo, "机器未注册");
        }
        IcCard card = getOne(Wrappers.<IcCard>lambdaQuery()
                        .eq(IcCard::getNo, cardNo),
                false);
        if (card == null) {
            Object value = RedisUtil.get("GET_CARD_NO");
            if (value == null) {
                return new ResponseMsg(CmdCodeEnum.CON, Voices.WELCOME, cardNo, "无效卡");
            } else {
                log.info("发送卡号到前端");
                RedisUtil.getClient().getTopic("readCard").publish(cardNo);
                return new ResponseMsg(CmdCodeEnum.CON, Voices.WELCOME, cardNo, "读卡中...");
            }
        }
        if (card.getStatus() != CardStatusEnum.ENABLE) {
            if (card.getAccountStatus() == CardAccountEnum.QUIT) {
                return new ResponseMsg(CmdCodeEnum.CON, Voices.INVALID, cardNo, "无效卡!");
            }
            return new ResponseMsg(CmdCodeEnum.CON, Voices.LOSS, cardNo, "挂失卡!");
        } else {
            if (card.getValidityTime() != null && card.getValidityTime().before(new Date())) {
                return new ResponseMsg(CmdCodeEnum.CON, Voices.INVALID, cardNo, "无效卡!");
            } else {
                return new ResponseMsg(CmdCodeEnum.CON, Voices.THANKS, cardNo, MathUtil.mul(ObjectUtil.getDouble(card.getCurrentBalance()), 1, 2));
            }

        }
    }

    @Override
    public void reportLoss(Long id, Account account) {
        IcCard byId = getById(id);
        if (byId == null) {
            throw new BaseException(CanteenExceptionEnum.CARD_NOT_EXIST);
        }
        if (byId.getStatus() != CardStatusEnum.ENABLE) {
            throw new BaseException(CanteenExceptionEnum.CARD_TYPE_ERROR);
        }
        byId.setStatus(CardStatusEnum.DISABLE);
        byId.setAccountStatus(CardAccountEnum.LOSS);
        EntityLogUtil.addNormalUser(byId, account);
        boolean b = updateById(byId);
        if (!b) {
            throw new BaseException(CanteenExceptionEnum.UPDATE_FAIL);
        }
    }

    @Override
    public void unLoss(Long id, Account account) {
        IcCard byId = getById(id);
        if (byId == null) {
            throw new BaseException(CanteenExceptionEnum.CARD_NOT_EXIST);
        }
        if (byId.getStatus() != CardStatusEnum.DISABLE) {
            throw new BaseException(CanteenExceptionEnum.CARD_TYPE_ERROR);
        }
        if (byId.getAccountStatus() != CardAccountEnum.LOSS) {
            throw new BaseException(CanteenExceptionEnum.CARD_TYPE_ERROR);
        }
        byId.setStatus(CardStatusEnum.ENABLE);
        byId.setAccountStatus(CardAccountEnum.UN_LOSS);
        EntityLogUtil.addNormalUser(byId, account);
        boolean b = updateById(byId);
        if (!b) {
            throw new BaseException(CanteenExceptionEnum.UPDATE_FAIL);
        }
    }

    @Autowired
    private IEmployeeService iEmployeeService;

    @Override
    public void patchCard(PatchCardForm form, Account account) {
        ValidatorUtil.validator(form, PatchCardForm.Insert.class);
        IcCard old = getById(form.getCardId());
        if (old == null) {
            throw new BaseException(CanteenExceptionEnum.CARD_NOT_EXIST);
        }
        if (old.getStatus() != CardStatusEnum.DISABLE && old.getAccountStatus() != CardAccountEnum.LOSS) {
            throw new BaseException(CanteenExceptionEnum.CARD_TYPE_ERROR);
        }
        IcCard newCard = getByCode(form.getCardNo());
        if (newCard != null) {
            throw new BaseException(CanteenExceptionEnum.CARD_NO_REPEAT);
        }
        old.setAccountStatus(CardAccountEnum.UN_LOSS);
        EntityLogUtil.addNormalUser(old, account);
        newCard = new IcCard();
        BeanUtils.copyProperties(old, newCard, "id", "no", "account_status", "status");
        newCard.setNo(form.getCardNo());
        newCard.setAccountStatus(CardAccountEnum.REISSUE);
        newCard.setStatus(CardStatusEnum.ENABLE);
        EntityLogUtil.addNormalUser(newCard, account);
        boolean save = save(newCard);
        Employee emp = iEmployeeService.getById(old.getEmployeeId());
        emp.setCardId(newCard.getId());
        emp.setCardNo(form.getCardNo());
        EntityLogUtil.addNormalUser(emp, account);
        iEmployeeService.updateById(emp);
        if (!save) {
            throw new BaseException(CanteenExceptionEnum.PATCH_CARD_ERROR);
        }
        old.setCurrentBalance(0d);
        boolean b = updateById(old);
        if (!b) {
            throw new BaseException(CanteenExceptionEnum.UPDATE_FAIL);
        }
    }

    @Override
    public void cancellation(Long empId, Account account) {
        Employee emp = iEmployeeService.getById(empId);
        if (emp == null) {
            throw new BaseException(CanteenExceptionEnum.USER_NOT_EXIST);
        }
        if (emp.getStatus() != EmployeeStatusEnum.ENABLE) {
            throw new BaseException(CanteenExceptionEnum.USER_IS_QUIT);
        }
        emp.setStatus(EmployeeStatusEnum.DISABLE);
        boolean b = iEmployeeService.updateById(emp);
        if (!b) {
            throw new BaseException(CanteenExceptionEnum.UPDATE_FAIL);
        }
        Long cardId = emp.getCardId();
        IcCard card = getById(cardId);
        if (card != null) {
            card.setStatus(CardStatusEnum.DISABLE);
            card.setAccountStatus(CardAccountEnum.QUIT);
            EntityLogUtil.addNormalUser(card, account);
            b = updateById(card);
            if (!b) {
                throw new BaseException(CanteenExceptionEnum.UPDATE_FAIL);
            }
        }
    }


    @Override
    public void deduction(DeductionForm form, Account account) {
        ValidatorUtil.validator(form);
        IcCard card = getById(form.getCardId());
        if (card == null) {
            throw new BaseException(CanteenExceptionEnum.CARD_NOT_EXIST);
        }
        if (card.getStatus() != CardStatusEnum.ENABLE) {
            throw new BaseException(CanteenExceptionEnum.CARD_TYPE_ERROR);
        }
        double sub = MathUtil.sub(ObjectUtil.getDouble(card.getCurrentBalance()), form.getMoney());
        if (sub < 0) {
            throw new BaseException(CanteenExceptionEnum.NUM_ERROR);
        }
        card.setCurrentBalance(sub);
        EntityLogUtil.addNormalUser(card, account);
        boolean b = updateById(card);
        if (!b) {
            throw new BaseException(CanteenExceptionEnum.UPDATE_FAIL);
        }
        boolean b1 = iOrderService.addOrderForDeduction(card, form.getMoney(), account, sub, form.getDescription());
        if (!b1) {
            throw new BaseException(CanteenExceptionEnum.UPDATE_FAIL);
        }
    }
}
