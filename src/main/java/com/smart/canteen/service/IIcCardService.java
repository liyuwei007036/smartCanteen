package com.smart.canteen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.core.dto.Account;
import com.smart.canteen.dto.card.CardForm;
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
     * 通过卡片查询
     *
     * @param no
     * @return
     */
    CardVo getByNo(String no);

    /**
     * 刷卡扣款
     *
     * @param cardNo
     * @param money
     * @return
     */
    ResponseMsg deductions(String cardNo, Integer money);

    /**
     * 刷卡查询
     *
     * @param cardNo
     * @return
     */
    ResponseMsg search(String cardNo);


}
