package cn.thu.info.controller;

import cn.thu.info.model.*;
import cn.thu.info.proto.Config;
import cn.thu.info.proto.FileInformation;
import cn.thu.info.proto.ISitemapService;
import cn.thu.info.service.SitemapServiceHelper;
import cn.thu.info.util.JSONUtil;
import cn.thu.info.utils.SnapshotUtil;
import cn.thu.info.utils.Util;
import com.sdicons.json.mapper.MapperException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;


@Controller
@RequestMapping("/")
public class IndexController {
    private static Logger logger = LoggerFactory.getLogger(IndexController.class);
    public IndexController(){

    }
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Index");
        /*Object keywords = request.getParameter("keywords");
        if(keywords!=null && keywords.toString().length()>0){
            ISitemapService service = SitemapServiceHelper.createInstance();
            String query = keywords.toString();
            try {
                String id = service.addTask(query);
                while (!service.isFinished(id)){
                    logger.info("Process " + service.getProcess(id));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int res = service.getStatus(id);
                if(res !=0 ){
                    logger.info("Query failed!");
                }else {
                    String path = request.getSession().getServletContext().getRealPath("");
                    FileInformation file = service.download(id);
                    File mkFile = new File(path + "/" + Config.RESULT_PATH );
                    String str = mkFile.getAbsolutePath();
                    if(!mkFile.exists()){
                        mkFile.mkdir();
                    }
                    file.saveToFile(path + "/" + Config.RESULT_PATH + query + ".pdf");

                    Desktop desktop = Desktop.getDesktop();
                    File f = new File(path + "/" + Config.RESULT_PATH + query + ".pdf");
                    try {
                        desktop.open(f);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        //modelAndView.addObject("companyList", companyList);
        //modelAndView.addObject("reginfoList", reginfoList);
        */
        return modelAndView;
    }

    @RequestMapping("/addTask")
    @ResponseBody
    public String addTask(HttpServletRequest request){
        Map<String, Object> result = new HashMap<String, Object>();
        String path = request.getSession().getServletContext().getRealPath("");
        ISitemapService service = SitemapServiceHelper.createInstance();
        Object keywords = request.getParameter("keywords");
        if(keywords!=null && keywords.toString().length()>0) {

            try {
                String id = service.addTask(keywords.toString());
                result.put("sessionId", id);
                result.put("status", 0);
            } catch (RemoteException e) {
                e.printStackTrace();
                result.put("status", -1);
                result.put("message", "Remote rmi exception");
            }

        }else {
            result.put("status", -1);
        }
        return JSONUtil.obj2Json(result);
    }

    @RequestMapping("/getProcess")
    @ResponseBody
    public String getProcess(HttpServletRequest request) throws RemoteException {
        String callback = request.getParameter("callback");
        Map<String, Object> result = new HashMap<String, Object>();
        ISitemapService service = SitemapServiceHelper.createInstance();
        Object sessionId = request.getParameter("sessionId");
        if(sessionId!=null && sessionId.toString().length()>0) {

            try {
                double process = service.getProcess(sessionId.toString());

                result.put("process", String.format("%.2f", process));
                result.put("status", 0);
            } catch (RemoteException e) {
                e.printStackTrace();
                result.put("status", -1);
                result.put("message", "Remote rmi exception");
            }

        }else {
            result.put("status", -1);
        }
        return returnRes(JSONUtil.obj2Json(result), callback);
    }

    @RequestMapping("/isFinished")
    @ResponseBody
    public String isFinished(HttpServletRequest request) throws RemoteException {
        Map<String, Object> result = new HashMap<String, Object>();
        ISitemapService service = SitemapServiceHelper.createInstance();
        Object sessionId = request.getParameter("sessionId");
        if(sessionId!=null && sessionId.toString().length()>0) {

            try {
                boolean finished = service.isFinished(sessionId.toString());
                result.put("finished", finished);
                result.put("status", 0);
            } catch (RemoteException e) {
                e.printStackTrace();
                result.put("status", -1);
                result.put("message", "Remote rmi exception");
            }

        }else {
            result.put("status", -1);
        }
        return JSONUtil.obj2Json(result);
    }


    @RequestMapping("/getStatus")
    @ResponseBody
    public String getStatus(HttpServletRequest request) throws RemoteException {
        String callback = request.getParameter("callback");
        Map<String, Object> result = new HashMap<String, Object>();
        ISitemapService service = SitemapServiceHelper.createInstance();
        Object sessionId = request.getParameter("sessionId");
        if(sessionId!=null && sessionId.toString().length()>0) {

            try {
                int status = service.getStatus(sessionId.toString());
                result.put("state", status);
                result.put("status", 0);
            } catch (RemoteException e) {
                e.printStackTrace();
                result.put("status", -1);
                result.put("message", "Remote rmi exception");
            }

        }else {
            result.put("status", -1);
        }
        return returnRes(JSONUtil.obj2Json(result), callback);
    }

    @RequestMapping("/getResultFile")
    @ResponseBody
    public String getResultFile(HttpServletRequest request) throws RemoteException {
        Map<String, Object> result = new HashMap<String, Object>();
        ISitemapService service = SitemapServiceHelper.createInstance();
        Object sessionId = request.getParameter("sessionId");
        if(sessionId!=null && sessionId.toString().length()>0) {
            try {
                FileInformation fileInfo = service.download(sessionId.toString());
                String disPath = "result/" + UUID.randomUUID() + ".pdf";
                String path = request.getSession().getServletContext().getRealPath("") + "/" + disPath;
                fileInfo.saveToFile(path);
                result.put("path", disPath);
                result.put("status", 0);
            } catch (RemoteException e) {
                e.printStackTrace();
                result.put("status", -1);
                result.put("message", "Remote rmi exception");
            }

        }else {
            result.put("status", -1);
        }
        return JSONUtil.obj2Json(result);
    }

    @RequestMapping("/addEmail")
    @ResponseBody
    public String addEmail(HttpServletRequest request) throws RemoteException {
        Map<String, Object> result = new HashMap<String, Object>();
        ISitemapService service = SitemapServiceHelper.createInstance();
        Object sessionId = request.getParameter("sessionId");
        Object email = request.getParameter("email");
        if(sessionId!=null && sessionId.toString().length()>0
                && email!=null && email.toString().length()>0) {
            try {
                service.addEmail(sessionId.toString(), email.toString());
                result.put("status", 0);
            } catch (RemoteException e) {
                e.printStackTrace();
                result.put("status", -1);
                result.put("message", "Remote rmi exception");
            }

        }else {
            result.put("status", -1);
        }
        return JSONUtil.obj2Json(result);
    }

    @RequestMapping("/getPath")
    @ResponseBody
    public String getPath(HttpServletRequest request) throws RemoteException {
        return request.getSession().getServletContext().getRealPath("");
    }

    @RequestMapping(value = "/getCompanyInfo",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getCompanyInfo(HttpServletRequest request){
        String callback = request.getParameter("callback");
        Map<String, Object> result = new HashMap<String, Object>();
        ISitemapService service = SitemapServiceHelper.createInstance();
        Object keywords = request.getParameter("keywords");
        if(keywords!=null && keywords.toString().length()>0) {
            try {
                Report report = service.getCompanyInfo(keywords.toString());
                result.put("report", report);
                result.put("status", report.status);
            } catch (RemoteException e) {
                e.printStackTrace();
                result.put("status", Status.FAIL);
                result.put("message", "Remote rmi exception");
            }

        }else {
            result.put("status", Status.FAIL);
        }
        return returnRes(JSONUtil.obj2Json(result), callback);
    }
    @RequestMapping(value = "/getCompanyInfoByName",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getCompanyInfoByName(HttpServletRequest request){
        String callback = request.getParameter("callback");
        Map<String, Object> result = new HashMap<String, Object>();
        ISitemapService service = SitemapServiceHelper.createInstance();
        Object keywords = request.getParameter("keywords");
        if(keywords!=null && keywords.toString().length()>0) {
            try {
                QueryResult qr = service.getCompanyInfoByName(keywords.toString());
                result.put("companyList", qr.companies);
                result.put("status", qr.status);
                result.put("message", qr.message);
            } catch (RemoteException e) {
                e.printStackTrace();
                result.put("status", Status.FAIL);
                result.put("message", "Remote rmi exception");
            }

        }else {
            result.put("status", Status.FAIL);
        }
        return returnRes(JSONUtil.obj2Json(result), callback);
    }
    @RequestMapping(value = "/getCompanyInfoById",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getCompanyInfoById(HttpServletRequest request){
        String callback = request.getParameter("callback");
        Map<String, Object> result = new HashMap<String, Object>();
        ISitemapService service = SitemapServiceHelper.createInstance();
        Object keywords = request.getParameter("id");
        if(keywords!=null && keywords.toString().length()>0) {
            try {
                Report report = service.getCompanyInfoById(keywords.toString());
                result.put("report", report);
                result.put("status", report.status);
            } catch (RemoteException e) {
                e.printStackTrace();
                result.put("status", Status.FAIL);
                result.put("message", "Remote rmi exception");
            }

        }else {
            result.put("status", Status.FAIL);
        }
        return returnRes(JSONUtil.obj2Json(result), callback);
    }
    @RequestMapping(value = "/getCompanyInfoByUrl",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getCompanyInfoByUrl(HttpServletRequest request){
        String callback = request.getParameter("callback");
        Map<String, Object> result = new HashMap<String, Object>();
        ISitemapService service = SitemapServiceHelper.createInstance();
        Object keywords = request.getParameter("url");
        if(keywords!=null && keywords.toString().length()>0) {
            try {
                String url = keywords.toString();
                if(!url.startsWith("http")){
                    url = "http://" + url;
                }
                Report report = service.getCompanyInfoByUrl(url);
                result.put("report", report);
                result.put("status", report.status);
            } catch (RemoteException e) {
                e.printStackTrace();
                result.put("status", Status.FAIL);
                result.put("message", "Remote rmi exception");
            }

        }else {
            result.put("status", Status.FAIL);
        }
        return returnRes(JSONUtil.obj2Json(result), callback);
    }

    public String returnRes(String res, String callback){
        if(!StringUtils.isEmpty(callback)){
            return  callback + "(" + res + ")";
        }else {
            return res;
        }
    }

    @RequestMapping("/getProcessList")
    @ResponseBody
    public String getProcessList(HttpServletRequest request) throws RemoteException {
        String callback = request.getParameter("callback");
        Map<String, Object> result = new HashMap<String, Object>();
        ISitemapService service = SitemapServiceHelper.createInstance();
        Object sessionId = request.getParameter("sessionId");
        if(sessionId!=null && sessionId.toString().length()>0) {

            try {
                List<SnapshotTask> list = service.getProcessList(sessionId.toString());
                double process = service.getProcess(sessionId.toString());
                result.put("processDetail", list);
                result.put("process", process);
                result.put("status", 0);
            } catch (RemoteException e) {
                e.printStackTrace();
                result.put("status", -1);
                result.put("message", "Remote rmi exception");
            }

        }else {
            result.put("status", -1);
        }
        return returnRes(JSONUtil.obj2Json(result), callback);
    }

    @RequestMapping(value = "/searchCompanyInfo",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String searchCompanyInfo(HttpServletRequest request){

        String callback = request.getParameter("callback");
        Map<String, Object> result = new HashMap<String, Object>();
        ISitemapService service = SitemapServiceHelper.createInstance();

        Object setupTimeStart = request.getParameter("setupTimeStart");
        Object setupTimeEnd = request.getParameter("setupTimeEnd");
        Object field = request.getParameter("field");
        Object financing_step = request.getParameter("financing_step");
        Object city = request.getParameter("city");
        Object pageSize = request.getParameter("pageSize");
        Object pageId = request.getParameter("pageId");

        int pageSizeInt = 20;
        int pageIdInt = 1;
        if(!Util.isObjEmpty(pageSize)){
            pageSizeInt = Integer.parseInt(pageSize.toString());
        }
        if(!Util.isObjEmpty(pageId)){
            pageIdInt = Integer.parseInt(pageId.toString());
        }
        CompanyExample ex = new CompanyExample();
        CompanyExample.Criteria criteria = ex.createCriteria();

        logger.info(String.format("Search setupTimeStart = %s, setupTimeEnd = %s, field = %s, financing_step = %s, city = %s,"
            , Util.ObjToString(setupTimeStart), Util.ObjToString(setupTimeEnd), Util.ObjToString(field), Util.ObjToString(financing_step),
                Util.ObjToString(city)));

        if(!Util.isObjEmpty(setupTimeStart) && !Util.isObjEmpty(setupTimeEnd)){
            Date startDate = Util.parseDate(setupTimeStart.toString());
            Date endDate = Util.parseDate(setupTimeEnd.toString());
            criteria.andSetupTimeBetween(startDate, endDate);
        }
        if(!Util.isObjEmpty(field)){
            criteria.andField1Like("%" + field + "%");
        }
        if(!Util.isObjEmpty(financing_step)){
            criteria.andFinancingStepLike("%" + financing_step +"%");
        }
        if(!Util.isObjEmpty(city)){
            criteria.andCityEqualTo(city.toString());
        }

        ex.setOrderByClause(String.format("c_id limit %d,%d", (pageIdInt-1)*pageSizeInt, pageSizeInt));
        try {
            SearchResult searchResult = service.searchCompanyInfo(ex);
            result.put("companies", searchResult.getList());
            result.put("totalSize", searchResult.getTotalSize());
            result.put("status", Status.SUCC);
        } catch (RemoteException e) {
            e.printStackTrace();
            result.put("status", Status.FAIL);
            result.put("message", "Remote rmi exception");
        }


        return returnRes(JSONUtil.obj2Json(result), callback);
    }
}
