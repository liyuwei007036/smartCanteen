package com.smart.canteen.service;

import live.lumia.dto.Account;
import com.smart.canteen.dto.CommonList;
import com.smart.canteen.dto.machine.MachineDTO;
import com.smart.canteen.dto.machine.MachineSearch;
import com.smart.canteen.entity.Machine;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.canteen.vo.MachineVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 卡机 服务类
 * </p>
 *
 * @author lc
 * @since 2020-03-25
 */
public interface IMachineService extends IService<Machine> {
    /**
     * 添加
     *
     * @param dto
     * @param operator
     */
    void add(MachineDTO dto, Account operator);

    /**
     * 修改
     *
     * @param dto
     * @param operator
     */
    void edit(MachineDTO dto, Account operator);

    /**
     * 删除
     *
     * @param id
     * @param operator
     */
    void delete(Long id, Account operator);

    /**
     * 查询
     *
     * @param id
     * @return
     */
    Machine getById(Long id);

    /**
     * 通过code查询
     *
     * @param code
     * @return
     */
    Machine getByCode(String code);

    /**
     * 查询
     *
     * @param name
     * @param code
     * @return
     */
    Machine getByNameOrCode(String name, String code);

    /**
     * 分页
     *
     * @param search
     * @return
     */
    CommonList<MachineVO> page(MachineSearch search);

    /**
     * map映射
     *
     * @param nos
     * @return
     */
    Map<String, String> mapByNo(List<String> nos);
}
