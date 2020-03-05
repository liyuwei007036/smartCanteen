package com.smart.canteen.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.core.dto.Account;
import com.lc.core.error.BaseException;
import com.lc.core.utils.ModelMapperUtils;
import com.lc.core.utils.ObjectUtil;
import com.lc.core.utils.ValidatorUtil;
import com.smart.canteen.dto.CommonList;
import com.smart.canteen.dto.origination.OriginationForm;
import com.smart.canteen.dto.origination.OriginationSearch;
import com.smart.canteen.entity.Origination;
import com.smart.canteen.enums.CanteenExceptionEnum;
import com.smart.canteen.mapper.OriginationMapper;
import com.smart.canteen.service.IOriginationService;
import com.smart.canteen.utils.EntityLogUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 组织 服务实现类
 * </p>
 *
 * @author lc
 * @since 2020-03-02
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class OriginationServiceImpl extends ServiceImpl<OriginationMapper, Origination> implements IOriginationService {

    @Override
    public void add(OriginationForm dto, Account creator) {
        ValidatorUtil.validator(dto, OriginationForm.Insert.class);
        Origination origination = getByName(dto.getName());
        if (origination != null) {
            throw new BaseException(CanteenExceptionEnum.ORG_NAME_REPEAT);
        }
        origination = ModelMapperUtils.strict(dto, Origination.class);
        long parentId = ObjectUtil.getLong(dto.getParentId());
        EntityLogUtil.addNormalUser(origination, creator);
        boolean save = save(origination);
        if (!save) {
            throw new BaseException(CanteenExceptionEnum.CREATE_FAIL);
        }
        addPath(parentId, origination);
    }

    public void addPath(Long parentId, Origination origination) {
        String path = origination.getId().toString();
        if (parentId > 0) {
            Origination parent = getById(parentId);
            if (parent == null) {
                throw new BaseException(CanteenExceptionEnum.PAR_ORG_NOT_EXIST);
            }
            origination.setParentId(parentId);
            path = parent.getPath() + "." + path;
        }
        origination.setPath(path);
        updateById(origination);
    }

    @Override
    public void update(OriginationForm form, Account updater) {
        ValidatorUtil.validator(form, OriginationForm.Update.class);
        Long id = form.getId();
        Origination origination = getById(id);
        if (origination == null) {
            throw new BaseException(CanteenExceptionEnum.ORG_NOT_EXIST);
        }
        Origination oldOrigination = getByName(form.getName());
        if (oldOrigination != null && !oldOrigination.getId().equals(id)) {
            throw new BaseException(CanteenExceptionEnum.ORG_NAME_REPEAT);
        }
        origination.setName(form.getName());
        origination.setCode(form.getCode());
        origination.setDescription(form.getDescription());
        EntityLogUtil.addNormalUser(origination, updater);
        boolean b = updateById(origination);
        if (!b) {
            throw new BaseException(CanteenExceptionEnum.UPDATE_FAIL);
        }
        long parentId = ObjectUtil.getLong(form.getParentId());
        addPath(parentId, origination);
    }

    @Override
    public void delete(Long id, Account updater) {
        Origination origination = getById(id);
        if (origination == null) {
            throw new BaseException(CanteenExceptionEnum.ORG_NOT_EXIST);
        }
        getBaseMapper().logicDeleted(updater, id);
    }

    @Override
    public CommonList<Origination> listByConditional(OriginationSearch search) {
        Page<Origination> page = new Page<>(search.getPage(), search.getSize());
        super.page(page, Wrappers.<Origination>lambdaQuery()
                .like(!StringUtils.isEmpty(search.getName()), Origination::getName, search.getName())

        );
        return new CommonList<>(page.hasNext(), page.getTotal(), page.getCurrent(), page.getRecords());
    }

    @Override
    public Origination getById(Long id) {
        return super.getById(id);
    }

    @Override
    public Origination getByName(String name) {
        return getOne(Wrappers.<Origination>lambdaQuery().eq(Origination::getName, name), false);
    }

    @Override
    public List<Origination> listAll() {
        return list();
    }

}
