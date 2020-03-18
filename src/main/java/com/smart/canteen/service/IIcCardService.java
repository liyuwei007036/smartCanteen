package com.smart.canteen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.core.dto.Account;
import com.smart.canteen.dto.CommonList;
import com.smart.canteen.dto.card.CardForm;
import com.smart.canteen.dto.card.CardSearch;
import com.smart.canteen.dto.card.DeductionForm;
import com.smart.canteen.dto.card.PatchCardForm;
import com.smart.canteen.dto.recharge.RechargeForm;
import com.smart.canteen.entity.Employee;
import com.smart.canteen.entity.IcCard;
import com.smart.canteen.vo.CardVo;
import com.smart.canteen.vo.ResponseMsg;

/**
 * <p>
 * iC卡 服务类
 * </p>
 *
 * @author lc
 * @since 2020-03-03
 */
public interface IIcCardService extends IService<IcCard> {

    /**
     * 添加卡片
     *
     * @param form
     * @param employee
     * @param create
     * @return
     */
    Long addCard(CardForm form, Employee employee, Account create);

    /**
     * 通过卡号获取
     *
     * @param no
     * @return
     */
    IcCard getByCode(String no);

    /**
     * 通过id获取
     *
     * @param id
     * @return
     */
    IcCard getById(Long id);

    /**
     * 更新
     *
     * @param form
     * @param create
     */
    void update(CardForm form, Account create);


    /**
     * 查询卡片
     *
     * @param search
     * @return
     */
    CommonList<CardVo> listCard(CardSearch search);


    /**
     * 刷卡扣款
     *
     * @param cardNo
     * @param money
     * @param machineNo
     * @return
     */
    ResponseMsg deductions(String cardNo, Integer money, String machineNo);

    /**
     * 刷卡查询
     *
     * @param cardNo
     * @return
     */
    ResponseMsg search(String cardNo);

    /**
     * 充值
     *
     * @param form
     * @param account
     */
    void recharge(RechargeForm form, Account account);

    /**
     * 挂失
     *
     * @param id
     * @param account
     */
    void reportLoss(Long id, Account account);

    /**
     * 补卡
     *
     * @param form
     * @param account
     */
    void patchCard(PatchCardForm form, Account account);

    /**
     * 销户
     *
     * @param empId
     * @param account
     */
    void cancellation(Long empId, Account account);

    /**
     * 补扣
     *
     * @param form
     * @param account
     */
    void deduction(DeductionForm form, Account account);
}
