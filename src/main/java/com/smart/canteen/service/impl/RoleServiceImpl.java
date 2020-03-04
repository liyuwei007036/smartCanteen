package com.smart.canteen.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.core.dto.Account;
import com.lc.core.error.BaseException;
import com.lc.core.utils.ModelMapperUtils;
import com.lc.core.utils.ValidatorUtil;
import com.smart.canteen.dto.CommonList;
import com.smart.canteen.dto.role.RoleForm;
import com.smart.canteen.dto.role.RoleSearch;
import com.smart.canteen.entity.Role;
import com.smart.canteen.enums.CanteenExceptionEnum;
import com.smart.canteen.mapper.RoleMapper;
import com.smart.canteen.service.IRoleService;
import com.smart.canteen.utils.EntityLogUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 岗位 服务实现类
 * </p>
 *
 * @author lc
 * @since 2020-03-02
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public void add(RoleForm dto, Account creator) {
        ValidatorUtil.validator(dto, RoleForm.Insert.class);
        Role role = getByName(dto.getName());
        if (role != null) {
            throw new BaseException(CanteenExceptionEnum.ROLE_NAME_REPEAT);
        }
        role = ModelMapperUtils.strict(dto, Role.class);
        EntityLogUtil.addNormalUser(role, creator);
        boolean save = save(role);
        if (!save) {
            throw new BaseException(CanteenExceptionEnum.CREATE_FAIL);
        }
    }

    @Override
    public void update(RoleForm form, Account updater) {
        ValidatorUtil.validator(form, RoleForm.Update.class);
        Long id = form.getId();
        Role role = getById(id);
        if (role == null) {
            throw new BaseException(CanteenExceptionEnum.ROLE_NOT_EXIST);
        }
        Role oldRole = getByName(form.getName());
        if (oldRole != null && !oldRole.getId().equals(id)) {
            throw new BaseException(CanteenExceptionEnum.ROLE_NAME_REPEAT);
        }
        EntityLogUtil.addNormalUser(role, updater);
        boolean b = updateById(role);
        if (!b) {
            throw new BaseException(CanteenExceptionEnum.UPDATE_FAIL);
        }
    }

    @Override
    public void delete(Long id, Account updater) {
        Role role = getById(id);
        if (role == null) {
            throw new BaseException(CanteenExceptionEnum.ROLE_NOT_EXIST);
        }
        getBaseMapper().logicDeleted(updater, id);
    }

    @Override
    public CommonList<Role> listByConditional(RoleSearch search) {
        ValidatorUtil.validator(search);
        Page<Role> page = new Page<>(search.getPage(), search.getSize());
        super.page(page, Wrappers.<Role>lambdaQuery()
                .like(!StringUtils.isEmpty(search.getName()), Role::getName, search.getName())

        );
        return new CommonList<>(page.hasNext(), page.getTotal(), page.getCurrent(), page.getRecords());
    }

    @Override
    public Role getById(Long id) {
        return super.getById(id);
    }

    @Override
    public Role getByName(String name) {
        return getOne(Wrappers.<Role>lambdaQuery().eq(Role::getName, name), false);
    }

    @Override
    public List<Role> listAll() {
        return list();
    }
}
