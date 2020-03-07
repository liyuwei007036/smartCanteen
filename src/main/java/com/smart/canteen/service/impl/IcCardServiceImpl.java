package com.smart.canteen.service.impl;

import com.lc.core.dto.Account;
import com.lc.core.utils.ModelMapperUtils;
import com.smart.canteen.dto.card.CardForm;
import com.smart.canteen.entity.Employee;
import com.smart.canteen.entity.IcCard;
import com.smart.canteen.mapper.IcCardMapper;
import com.smart.canteen.service.IIcCardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
//        ModelMapperUtils.strict()
    }
}
