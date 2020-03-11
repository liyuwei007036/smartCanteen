package com.smart.canteen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.canteen.entity.Permission;
import com.smart.canteen.mapper.PermissionMapper;
import com.smart.canteen.service.IPermissionService;
import com.smart.canteen.vo.PermissionVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 组织 服务实现类
 * </p>
 *
 * @author lc
 * @since 2020-03-11
 */
@Transactional(rollbackFor = Exception.class, readOnly = true)
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Override
    public List<PermissionVo> listAll() {
        List<Permission> all = list();
        List<Permission> roots = all.stream().filter(x -> x.getParentId() == 0).collect(Collectors.toList());
        all.removeAll(roots);
        return createTreeNode(roots, all);
    }

    private List<PermissionVo> createTreeNode(List<Permission> list, List<Permission> all) {
        List<PermissionVo> res = new ArrayList<>();
        list.forEach(x -> {
            List<Permission> s = all.stream().filter(y -> x.getId().equals(y.getParentId())).collect(Collectors.toList());
            PermissionVo vo = new PermissionVo();
            vo.setChildren(createTreeNode(s, all));
            vo.setCode(x.getCode());
            vo.setName(x.getName());
            res.add(vo);
        });
        return res;
    }
}
