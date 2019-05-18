package cn.thu.info.proto;

import cn.thu.info.Config;
import cn.thu.info.crawler.sitemap.SiteReportGenerator;
import cn.thu.info.mapper.CompanyMapper;
import cn.thu.info.model.*;
import cn.thu.info.utils.SessionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;


public class SitemapService extends UnicastRemoteObject implements ISitemapService {
    private static int MAX_SIZE = 100;
    private static LinkedList<GenerationTask> taskList =  new LinkedList<GenerationTask>();
    private static Map<String, GenerationTask> taskMap =  new HashMap<String, GenerationTask>();
    private static Logger logger = LoggerFactory.getLogger(SitemapService.class);

    protected SitemapService() throws RemoteException {
        super();
    }

    @Override
    public String addTask(String query) throws RemoteException{
        logger.info("Query " + query);
        String id = UUID.randomUUID().toString();
        if(taskList.size() > MAX_SIZE) {
            GenerationTask firstTask = taskList.getFirst();
            taskList.removeFirst();
            taskMap.remove(firstTask.sessionId);
        }
        GenerationTask task = new GenerationTask(id, query);
        task.start();
        taskList.addLast(task);
        taskMap.put(id, task);

        return id;
    }
    @Override
    public double getProcess(String sessionId) throws RemoteException{
        GenerationTask task = taskMap.get(sessionId);
        if(task == null){
            logger.error("Session miss " + sessionId);
            return 1.0;
        }else {
            return task.getProcess();
        }
    }
    @Override
    public boolean isFinished(String sessionId) throws RemoteException{
        GenerationTask task = taskMap.get(sessionId);
        if(task == null){
            logger.error("Session miss " + sessionId);
            return true;
        }else {
            return task.finished();
        }
    }
    @Override
    public int getStatus(String sessionId) throws RemoteException{
        GenerationTask task = taskMap.get(sessionId);
        if(task == null){
            logger.error("Session miss " + sessionId);
            return Status.MISS_SESSION.id;
        }else {
            return task.getStatus();
        }
    }

    @Override
    public FileInformation download(String sessionId) throws RemoteException {
        //File f = new File(generator.report.getReportFile());
        FileInformation fileInfo = new FileInformation();
        GenerationTask task = taskMap.get(sessionId);
        if(task == null){
            logger.error("Session miss " + sessionId);
            return fileInfo;
        }else {
            String fileName = task.generator.report.getReportFile();
            BufferedInputStream input = null ;
            try{

                File   file = new File(fileName);
                if(!file.exists()){
                    throw new RemoteException("the file whose name is " + fileName + " not existed ");
                }
                // get content
                byte[] content = new byte[(int)file.length()];
                input = new BufferedInputStream(new FileInputStream(file));
                input.read(content);
                // set file name and content to fileInfo
                fileInfo = new FileInformation();
                fileInfo.setInformation(fileName , content);
            }catch(RemoteException ex){
                throw ex ;
            }catch(Exception ex){
                throw new RemoteException(ex.getLocalizedMessage());
            }finally{
                if(input != null ){
                    try{
                        input.close();
                    }catch(Exception ex){
                    }
                }
            }
            return fileInfo ;
        }
    }

    @Override
    public int addEmail(String sessionId, String email) throws RemoteException {
        GenerationTask task = taskMap.get(sessionId);
        if(task == null){
            logger.error("Session miss " + sessionId);
            return Status.MISS_SESSION.id;
        }else {
            logger.info("Send email to " + email);
            return task.sendEmail(email);
        }
    }

    @Override
    public Report getCompanyInfo(String query) throws RemoteException {
        logger.info("Get company info " + query);
        String id = UUID.randomUUID().toString();
        if(taskList.size() > MAX_SIZE) {
            GenerationTask firstTask = taskList.getFirst();
            taskList.removeFirst();
            taskMap.remove(firstTask.sessionId);
        }
        GenerationTask task = new GenerationTask(id, query);
        task.start();
        taskList.addLast(task);
        taskMap.put(id, task);
        SiteReportGenerator generator = task.generator;
        while(generator.getCurProcess() < 1.0){
            logger.info("Process " + (generator.getCurProcess() * 100.0) + "%");
            if(generator.getStatus() == Status.CONTENT_FINISHED){
                break;
            }
            try {
                Thread.sleep( 1000 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        logger.info(String.format("Query %s finished. With status %s.", query, generator.getStatus()));
        return generator.report;
    }

    @Override
    public List<SnapshotTask> getProcessList(String sessionId) throws RemoteException {
        GenerationTask task = taskMap.get(sessionId);
        if(task == null){
            logger.error("Session miss " + sessionId);
            return null;
        }else {
            return task.generator.report.snapshotTaskList;
        }
    }

    @Override
    public QueryResult getCompanyInfoByName(String s) throws RemoteException {
        QueryResult qr = new QueryResult();
        logger.info("Get company info by Name " + s);
        try{
            SiteReportGenerator srg = new SiteReportGenerator(s);
            qr.companies = srg.getCompanyInfoList(s);
            if(qr.companies.size() == 0){
                qr.status = Status.NOT_FOUND;
                qr.message = "在超过5万个公司信息库中未找到该公司，请检查名称是否正确，或者直接输入公司网址。";
            }else {
                qr.status = Status.SUCC;
                qr.message = "查找成功";
            }
        }catch (Exception e){
            qr.status = Status.FAIL;
            qr.message = e.getMessage();
        }
        return qr;
    }

    @Override
    public Report getCompanyInfoById(String query) throws RemoteException {
        logger.info("Get company info by Id " + query);
        String id = UUID.randomUUID().toString();
        if(taskList.size() > MAX_SIZE) {
            GenerationTask firstTask = taskList.getFirst();
            taskList.removeFirst();
            taskMap.remove(firstTask.sessionId);
        }
        GenerationTask task = new GenerationTask(id, query, SourceType.ID);
        task.start();
        taskList.addLast(task);
        taskMap.put(id, task);
        SiteReportGenerator generator = task.generator;
        while(generator.getCurProcess() < 1.0){
            logger.debug("Process " + (generator.getCurProcess() * 100.0) + "%");
            if(generator.getStatus() == Status.CONTENT_FINISHED){
                break;
            }
            try {
                Thread.sleep( 1000 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        logger.info(String.format("Query %s %s finished. With status %s.", query, generator.report.company.getName(), generator.getStatus()));
        return generator.report;
    }

    @Override
    public Report getCompanyInfoByUrl(String query) throws RemoteException {
        logger.info("Get company info by Url " + query);
        String id = UUID.randomUUID().toString();
        if(taskList.size() > MAX_SIZE) {
            GenerationTask firstTask = taskList.getFirst();
            taskList.removeFirst();
            taskMap.remove(firstTask.sessionId);
        }
        GenerationTask task = new GenerationTask(id, query, SourceType.URL);
        task.start();
        taskList.addLast(task);
        taskMap.put(id, task);
        SiteReportGenerator generator = task.generator;
        while(generator.getCurProcess() < 1.0){
            logger.info("Process " + (generator.getCurProcess() * 100.0) + "%");
            if(generator.getStatus() == Status.CONTENT_FINISHED){
                break;
            }
            try {
                Thread.sleep( 1000 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        logger.info(String.format("Query %s finished. With status %s.", query, generator.getStatus()));
        return generator.report;
    }

    @Override
    public SearchResult searchCompanyInfo(CompanyExample ex) throws RemoteException {
        CompanyExample.Criteria criteria = ex.getOredCriteria().get(0);
        StringBuffer buffer = new StringBuffer();
        for (CompanyExample.Criterion c : criteria.getAllCriteria()){
            buffer.append(c.getCondition() + " ");
        }
        //ex.setOrderByClause("c_id desc limit 0,100");
        logger.info(String.format("Search company %s", buffer.toString()));
        CompanyMapper operation = SessionHelper.getSession().getMapper(CompanyMapper.class);
        SearchResult result = new SearchResult();
        try {
            result.setList(operation.selectByExample(ex));
            ex.setOrderByClause(null);
            result.setTotalSize(operation.countByExample(ex));
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        try {
            ISitemapService service = new SitemapService();
            LocateRegistry.createRegistry(6600);
            Naming.rebind("rmi://127.0.0.1:6600/SitemapService", service);
            //namingContext.rebind("//localhost:1234/HelloServer", service);
            logger.info("Service start!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
