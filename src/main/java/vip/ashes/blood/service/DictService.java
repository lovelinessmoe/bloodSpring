package vip.ashes.blood.service;

import com.baomidou.mybatisplus.extension.service.IService;
import vip.ashes.blood.entity.Dict;
import vip.ashes.blood.entity.vo.DictVO;

import java.util.List;

/**
 * @author loveliness
 */
public interface DictService extends IService<Dict> {


    List<DictVO> getDictByCode(String code);

}
