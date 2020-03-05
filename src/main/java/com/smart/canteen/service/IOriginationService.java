package com.smart.canteen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.core.dto.Account;
import com.smart.canteen.dto.CommonList;
import com.smart.canteen.dto.origination.OriginationForm;
import com.smart.canteen.dto.origination.OriginationSearch;
import com.smart.canteen.entity.Origination;

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
    List<Origination> listAll();
}
