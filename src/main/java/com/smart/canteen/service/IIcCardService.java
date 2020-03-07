package com.smart.canteen.service;

import com.lc.core.dto.Account;
import com.smart.canteen.dto.card.CardForm;
import com.smart.canteen.entity.Employee;
import com.smart.canteen.entity.IcCard;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
