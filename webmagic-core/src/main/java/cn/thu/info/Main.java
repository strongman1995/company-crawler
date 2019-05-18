package cn.thu.info;

import cn.thu.info.crawler.sitemap.SitemapProcessor;
import cn.thu.info.mapper.CompanyMapper;
import cn.thu.info.mapper.FinancingMapper;
import cn.thu.info.mapper.ReginfoMapper;
import cn.thu.info.model.*;
import cn.thu.info.utils.SessionHelper; //whereis Seesion Helper

import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        CompanyMapper operation = SessionHelper.getSession().getMapper(CompanyMapper.class);
        ReginfoMapper operationReg = SessionHelper.getSession().getMapper(ReginfoMapper.class);
        FinancingMapper operationFinancing = SessionHelper.getSession().getMapper(FinancingMapper.class);
        while (in.hasNext()){
            String search = in.nextLine();
            CompanyExample ce = new CompanyExample();
            ce.createCriteria().andNameLike("%" + search + "%");
            System.out.println("%" + search + "%");
            List<Company> resList = operation.selectByExampleWithBLOBs(ce);
            /*for (CompanyWithBLOBs company : resList){
                Report report = new Report();
                report.company = company;
                int cid = company.getcId();
                ReginfoExample regEx = new ReginfoExample();
                regEx.createCriteria().andCIdEqualTo(cid);
                List<Reginfo> regList = operationReg.selectByExampleWithBLOBs(regEx);
                if(regList.size()>0){
                    report.regInfo = regList.get(0);
                }
                FinancingExample fEx = new FinancingExample();
                fEx.createCriteria().andCIdEqualTo(cid);
                List<Financing> fList = operationFinancing.selectByExample(fEx);
                report.financingList = fList;
                SitemapProcessor processor = new SitemapProcessor();
                processor.process(report);

                break;
            }*/

        }
        SessionHelper.closeSession();

    }
}
