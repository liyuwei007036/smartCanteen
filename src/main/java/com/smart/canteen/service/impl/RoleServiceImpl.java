package com.smart.canteen.service.impl;

import com.smart.canteen.entity.Role;
import com.smart.canteen.mapper.RoleMapper;
import com.smart.canteen.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 岗位 服务实现类
 * </p>
 *
 * @author lc
 * @since 2020-03-02
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
