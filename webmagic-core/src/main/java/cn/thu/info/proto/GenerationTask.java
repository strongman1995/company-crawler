package cn.thu.info.proto;

import cn.thu.info.crawler.sitemap.ReportCallBack;
import cn.thu.info.crawler.sitemap.SiteReportGenerator;
import cn.thu.info.model.SourceType;
import cn.thu.info.model.Status;
import cn.thu.info.utils.EmailHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;


public class GenerationTask {
    private  static Logger logger = LoggerFactory.getLogger(GenerationTask.class);
    public GenerationTask(String sessionId, String query){
        generator = new SiteReportGenerator(query);
        generator.report.setSessionId(sessionId);
        this.sessionId = sessionId;
    }
    public GenerationTask(String sessionId, String query, SourceType type){
        generator = new SiteReportGenerator(query);
        generator.sourceType = type;
        generator.report.setSessionId(sessionId);
        this.sessionId = sessionId;
    }
    public void start(){
        generator.start();
    }
    public boolean finished(){
        return !(generator.getCurProcess() < 1.0);
    }
    public double getProcess(){
        return  generator.getCurProcess() * 100.0;
    }
    public int getStatus(){
        return generator.getStatus().id;
    }
    public SiteReportGenerator generator;
    public String sessionId;

    public int sendEmail(final String email) {
        if(generator.getStatus() == Status.SUCC){
            sendEmail(generator, email);
        }else {
            generator.setCallBack(new ReportCallBack() {
                @Override
                public void onFinish(SiteReportGenerator generator) {
                    sendEmail(generator, email);
                }
            });
        }
        return 0;
    }
    public void sendEmail(SiteReportGenerator generator, String email){
        EmailHelper mailHelper = new EmailHelper(false);
        String query = generator.report.query;
        File resFile = new File(generator.report.getReportFile());
        if (!resFile.exists()) {
            mailHelper.doSendHtmlEmail("企业信息报告-生成失败",
                    String.format("尊敬的用户您好!\n  您所提交关于%s的报告请求，由于内部错误生成失败，工作人员将尽快修复问题!", query),
                    email,
                    null);
            logger.error("Send email " + query + " fail");
        }
        mailHelper.doSendHtmlEmail(String.format("企业信息报告-%s", query),
                String.format("尊敬的用户您好!\r\n您所提交的报告请求-%s,已经生成完毕，请查看附件。", query),
                email, resFile);
        logger.info("Send email " + query + " successful");
    }
}
