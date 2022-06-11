package vip.ashes.blood.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vip.ashes.blood.dao.DictMapper;
import vip.ashes.blood.entity.Dict;
import vip.ashes.blood.entity.converter.DictConverter;
import vip.ashes.blood.entity.vo.DictVO;
import vip.ashes.blood.service.DictService;

import java.util.List;

/**
 * @author loveliness
 */
@Service
@AllArgsConstructor
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {
    private final DictMapper dictMapper;
    private final DictConverter dictConverter;

    @Override
    public List<DictVO> getDictByCode(String code) {
        QueryWrapper<Dict> codeEq = new QueryWrapper<Dict>().eq(Dict.COL_CODE, code);
        List<Dict> dicts = dictMapper.selectList(codeEq);
        List<DictVO> dictVOS = dictConverter.dictsToVos(dicts);
        return dictVOS;
    }
}
