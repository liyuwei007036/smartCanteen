package com.smart.canteen.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import live.lumia.dto.Account;
import live.lumia.error.BaseException;
import live.lumia.utils.ModelMapperUtils;
import live.lumia.utils.ObjectUtil;
import live.lumia.utils.ValidatorUtil;
import com.smart.canteen.dto.CommonList;
import com.smart.canteen.dto.origination.OriginationForm;
import com.smart.canteen.dto.origination.OriginationSearch;
import com.smart.canteen.entity.Origination;
import com.smart.canteen.enums.CanteenExceptionEnum;
import com.smart.canteen.mapper.OriginationMapper;
import com.smart.canteen.service.IEmployeeService;
import com.smart.canteen.service.IOriginationService;
import com.smart.canteen.utils.EntityLogUtil;
import com.smart.canteen.vo.OriginationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        Origination origination = judgeIsSame(dto.getName());
        if (origination != null) {
            throw new BaseException(CanteenExceptionEnum.ORG_NAME_REPEAT);
        }
        origination = ModelMapperUtils.strict(dto, Origination.class);
        origination.setHasChildren(false);
        addTreeNode(origination);
        EntityLogUtil.addNormalUser(origination, creator);
        boolean save = save(origination);
        if (!save) {
            throw new BaseException(CanteenExceptionEnum.CREATE_FAIL);
        }
    }

    /**
     * 添加节点线索
     *
     * @param origination
     */
    @Override
    public void addTreeNode(Origination origination) {
        long parentId = ObjectUtil.getLong(origination.getParentId());
        String path = "-";
        long level = 1L;
        Origination parent;
        if (parentId > 0) {
            parent = getById(parentId);
            if (parent == null) {
                throw new BaseException(CanteenExceptionEnum.PAR_ORG_NOT_EXIST);
            }
            parent.setHasChildren(true);
            updateById(parent);
            level = parent.getLevel() + 1;
            if (path.equals(parent.getPath())) {
                path = parentId + "-";
            } else {
                path = parent.getPath() + parentId + "-";
            }
        }
        origination.setParentId(parentId);
        origination.setLevel(level);
        origination.setPath(path);
    }


    @Override
    public void update(OriginationForm form, Account updater) {
        ValidatorUtil.validator(form, OriginationForm.Update.class);
        Long id = form.getId();
        Origination origination = getById(id);
        if (origination == null) {
            throw new BaseException(CanteenExceptionEnum.ORG_NOT_EXIST);
        }
        Origination oldOrigination = judgeIsSame(form.getName());
        if (oldOrigination != null && !oldOrigination.getId().equals(id)) {
            throw new BaseException(CanteenExceptionEnum.ORG_NAME_REPEAT);
        }
        origination.setName(form.getName());
        origination.setDescription(form.getDescription());
        EntityLogUtil.addNormalUser(origination, updater);
        boolean b = updateById(origination);
        if (!b) {
            throw new BaseException(CanteenExceptionEnum.UPDATE_FAIL);
        }
    }

    @Autowired
    private IEmployeeService iEmployeeService;

    @Override
    public void delete(Long id, Account updater) {
        Origination origination = getById(id);
        if (origination == null) {
            throw new BaseException(CanteenExceptionEnum.ORG_NOT_EXIST);
        }
        List<Long> orgIds = new ArrayList<>();
        String path;
        if (origination.getParentId() > 0) {
            path = String.format("%s%s-", origination.getPath(), origination.getId());
            Origination parent = getById(origination.getParentId());
            List<Origination> children = getChildren(parent.getId());
            if (children == null || children.isEmpty()) {
                parent.setHasChildren(false);
                boolean b = updateById(parent);
                if (!b) {
                    throw new BaseException(CanteenExceptionEnum.UPDATE_FAIL);
                }
            } else {
                orgIds.addAll(children.parallelStream().map(Origination::getId).collect(Collectors.toList()));
            }
        } else {
            path = String.format("%s-", origination.getId());
            origination.setHasChildren(false);
            boolean b = updateById(origination);
            if (!b) {
                throw new BaseException(CanteenExceptionEnum.UPDATE_FAIL);
            }
        }
        orgIds.add(id);
        int i = iEmployeeService.countByOrg(orgIds);
        if (i > 0) {
            throw new BaseException(CanteenExceptionEnum.ORG_HAS_EMP);

        }
        getBaseMapper().logicDeleted(updater, path, origination.getId());
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
    public List<OriginationVo> listAll() {
        List<Origination> all = list();
        List<Origination> roots = all.stream().filter(x -> x.getParentId() == 0).collect(Collectors.toList());
        all.removeAll(roots);
        return createTreeNode(roots, all);
    }


    private List<OriginationVo> createTreeNode(List<Origination> list, List<Origination> all) {
        List<OriginationVo> res = new ArrayList<>();
        list.forEach(x -> {
            Long id = x.getId();
            String name = x.getName();
            List<Origination> s = all.stream().filter(y -> x.getId().equals(y.getParentId())).collect(Collectors.toList());
            OriginationVo vo = new OriginationVo();
            vo.setChildren(createTreeNode(s, all));
            vo.setId(id);
            vo.setLabel(name);
            res.add(vo);
        });
        return res;
    }


    @Override
    public Origination judgeIsSame(String name) {
        return getOne(Wrappers.<Origination>lambdaQuery().eq(!StringUtils.isEmpty(name), Origination::getName, name)
               , false);
    }

    @Override
    public List<Origination> getAllRoot() {
        return list(Wrappers.<Origination>lambdaQuery().eq(Origination::getPath, "-"));
    }

    @Override
    public List<Origination> getChildren(Long id) {
        Origination org = getById(id);
        if (org == null) {
            throw new BaseException(CanteenExceptionEnum.ORG_NOT_EXIST);
        }
        long parentId = ObjectUtil.getLong(org.getParentId());
        return list(Wrappers.<Origination>lambdaQuery()
                .likeLeft(parentId > 0, Origination::getPath, String.format("%s%s-", org.getPath(), org.getId()))
                .likeLeft(parentId < 1, Origination::getPath, String.format("%s-", org.getId()))
                .eq(Origination::getLevel, org.getLevel() + 1));
    }
}
