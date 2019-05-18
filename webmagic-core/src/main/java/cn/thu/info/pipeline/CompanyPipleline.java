package cn.thu.info.pipeline;

import cn.thu.info.mapper.CompanyMapper;
import cn.thu.info.mapper.FinancingMapper;
import cn.thu.info.mapper.ReginfoMapper;
import cn.thu.info.model.Company;
import cn.thu.info.model.Financing;
import cn.thu.info.model.Reginfo;
import cn.thu.info.utils.HttpUtil;
import cn.thu.info.utils.ReflectUtil;
import cn.thu.info.utils.SessionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;
import java.util.Map;


public class CompanyPipleline implements Pipeline {
    void CompanyPipleline(){
        logger.info("Company pipeline instance.");
    }
    private Logger logger = LoggerFactory.getLogger(getClass());
    private CompanyMapper companyOp = SessionHelper.getSession().getMapper(CompanyMapper.class);
    private ReginfoMapper regOp = SessionHelper.getSession().getMapper(ReginfoMapper.class);
    private FinancingMapper financingOp = SessionHelper.getSession().getMapper(FinancingMapper.class);
    @Override
    public void process(ResultItems resultItems, Task task) {
        Map<String, Object> companyMap = resultItems.get("company");
        Map<String, Object> regInfoMap = resultItems.get("reg_info");
        List<Map<String, Object>> financingList = resultItems.get("financing");
        Company company = new Company();

        if(companyMap != null) {
            ReflectUtil.setValueFromMap(company, companyMap);
            String newImg = HttpUtil.download(company.getCompanyImg());
            company.setCompanyImg(newImg);
            companyOp.insert(company);
        }

        if(regInfoMap != null) {
            Reginfo reginfo = new Reginfo();
            ReflectUtil.setValueFromMap(reginfo, regInfoMap);
            reginfo.setcId(company.getcId());
            regOp.insert(reginfo);
        }

        if(financingList != null) {
            for (Map<String, Object> financingMap : financingList) {
                Financing financing = new Financing();
                ReflectUtil.setValueFromMap(financing, financingMap);
                financing.setcId(company.getcId());
                financingOp.insert(financing);
            }

        }
    }
}
