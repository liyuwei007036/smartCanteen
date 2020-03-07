package com.smart.canteen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.core.dto.Account;
import com.smart.canteen.dto.card.CardForm;
import com.smart.canteen.entity.Employee;
import com.smart.canteen.entity.IcCard;

import javax.smartcardio.Card;

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
    void addCard(CardForm form, Employee employee, Account create);

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
}
