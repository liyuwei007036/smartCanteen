package com.smart.canteen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import live.lumia.dto.Account;
import com.smart.canteen.dto.CommonList;
import com.smart.canteen.dto.origination.OriginationForm;
import com.smart.canteen.dto.origination.OriginationSearch;
import com.smart.canteen.entity.Origination;
import com.smart.canteen.vo.OriginationVo;

import java.util.List;

/**
 * <p>
 * 组织 服务类
 * </p>
 *
 * @author lc
 * @since 2020-03-02
 */
public interface IOriginationService extends IService<Origination> {


    /**
     * 新增
     *
     * @param dto
     * @param creator
     */
    void add(OriginationForm dto, Account creator);

    /**
     * 添加子节点
     *
     * @param origination
     */
    void addTreeNode(Origination origination);

    /**
     * 修改
     *
     * @param user
     * @param updater
     */
    void update(OriginationForm user, Account updater);

    /**
     * 删除
     *
     * @param id
     * @param updater
     */
    void delete(Long id, Account updater);

    /**
     * 条件查询
     *
     * @param search
     * @return
     */
    CommonList<Origination> listByConditional(OriginationSearch search);


    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    Origination getById(Long id);

    /**
     * 通过名称查询
     *
     * @param name
     * @return
     */
    Origination getByName(String name);

    /**
     * 查询所有
     *
     * @return
     */
    List<OriginationVo> listAll();

    /**
     * 判断是否重复
     *
     * @param name
     * @return
     */
    Origination judgeIsSame(String name);

    /**
     * 获取所有根节点
     *
     * @return
     */
    List<Origination> getAllRoot();

    /**
     * 获取莫个节点下的子节点
     *
     * @param id
     * @return
     */
    List<Origination> getChildren(Long id);
}
