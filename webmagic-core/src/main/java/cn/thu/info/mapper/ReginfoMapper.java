package cn.thu.info.mapper;

import cn.thu.info.model.Reginfo;
import cn.thu.info.model.ReginfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReginfoMapper {
    int countByExample(ReginfoExample example);

    int deleteByExample(ReginfoExample example);

    int deleteByPrimaryKey(Integer rId);

    int insert(Reginfo record);

    int insertSelective(Reginfo record);

    List<Reginfo> selectByExampleWithBLOBs(ReginfoExample example);

    List<Reginfo> selectByExample(ReginfoExample example);

    Reginfo selectByPrimaryKey(Integer rId);

    int updateByExampleSelective(@Param("record") Reginfo record, @Param("example") ReginfoExample example);

    int updateByExampleWithBLOBs(@Param("record") Reginfo record, @Param("example") ReginfoExample example);

    int updateByExample(@Param("record") Reginfo record, @Param("example") ReginfoExample example);

    int updateByPrimaryKeySelective(Reginfo record);

    int updateByPrimaryKeyWithBLOBs(Reginfo record);

    int updateByPrimaryKey(Reginfo record);
}