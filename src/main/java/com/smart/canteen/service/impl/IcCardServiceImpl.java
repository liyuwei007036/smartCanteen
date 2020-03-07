package com.smart.canteen.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.core.dto.Account;
import com.lc.core.error.BaseException;
import com.lc.core.utils.ModelMapperUtils;
import com.lc.core.utils.ValidatorUtil;
import com.smart.canteen.dto.card.CardForm;
import com.smart.canteen.entity.Employee;
import com.smart.canteen.entity.IcCard;
import com.smart.canteen.enums.CanteenExceptionEnum;
import com.smart.canteen.enums.CardAccountEnum;
import com.smart.canteen.enums.CardStatusEnum;
import com.smart.canteen.mapper.IcCardMapper;
import com.smart.canteen.service.IIcCardService;
import com.smart.canteen.utils.EntityLogUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void addCard(CardForm form, Employee employee, Account create) {
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

    }

    @Override
    public IcCard getByCode(String no) {
        return getOne(Wrappers.<IcCard>lambdaQuery().eq(IcCard::getNo, no), false);
    }

    @Override
    public IcCard getById(Long id) {
        return super.getById(id);
    }
}
