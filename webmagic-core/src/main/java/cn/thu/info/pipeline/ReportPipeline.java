package cn.thu.info.pipeline;

import cn.thu.info.model.Financing;
import cn.thu.info.model.PageSummary;
import cn.thu.info.model.Report;
import cn.thu.info.utils.Util;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.*;


public class ReportPipeline {
    public String end = "\\end{document}";
    public static String newLine = "\r\n";
    public void generate(Report report) {

        try {
            String mainTemp = FileUtils.readFileToString(new File(report.getTemplateFile()));
            String subTemp = FileUtils.readFileToString(new File(report.getSubFile()));
            StringBuffer subBuffer= new StringBuffer();
            if(report.company.getName() != null ) {
                mainTemp = mainTemp.replace("$productname", report.company.getName());
            }
            if(report.company.getCompanyName() != null ) {
                mainTemp = mainTemp.replace("$companyname", report.company.getCompanyName());
            }
            if(report.company.getIntroduction() != null ) {
                String content = report.company.getIntroduction();

                mainTemp = mainTemp.replace("$companyDesc",
                        content);
            }
            if(report.company.getSetupTime() != null) {
                String regDateInfo = Util.dateParseString(report.company.getSetupTime());
                if(!StringUtils.isEmpty(regDateInfo)){
                    mainTemp = mainTemp.replace("$companySetupTime", "公司成立时间：" + regDateInfo);
                }
            }else if(report.regInfo!=null && report.regInfo.getRegDate() != null){
                String regDateInfo = Util.dateParseString(report.regInfo.getRegDate());
                if(!StringUtils.isEmpty(regDateInfo)){
                    mainTemp = mainTemp.replace("$companySetupTime", "公司注册成立时间：" + regDateInfo);
                }
            }else{
                mainTemp = mainTemp.replace("$companySetupTime", "");
            }

            if(report.company.getWebsite() != null){
                String sentence = "官方网站： http://" + Util.urlNormalizer(report.company.getWebsite());
                mainTemp = mainTemp.replace("$companyWebsite", sentence);
            }
            if(report.regInfo != null) {
                if(report.regInfo.getRegId()!=null) {
                    mainTemp = mainTemp.replace("$regId", report.regInfo.getRegId());
                }
                if(report.regInfo.getLealPerson()!=null) {
                    mainTemp = mainTemp.replace("$lealPerson", report.regInfo.getLealPerson());
                }
                if(report.regInfo.getRegDate() !=null) {
                    mainTemp = mainTemp.replace("$regDate", Util.dateParseString(report.regInfo.getRegDate()));
                }
                if(report.regInfo.getRegCapital()!=null) {
                    mainTemp = mainTemp.replace("$regCapital", report.regInfo.getRegCapital());
                }
                if(report.regInfo.getRegLocation()!=null) {
                    mainTemp = mainTemp.replace("$regLocation", report.regInfo.getRegLocation());
                }
            }

            StringBuffer fSb = new StringBuffer();
            if(report.financingList != null) {
                for (Financing f : report.financingList) {
                    fSb.append(f.getRound());
                    fSb.append(" & ");
                    fSb.append(f.getAmount());
                    fSb.append(" & ");
                    fSb.append(f.getiName());
                    fSb.append(" & ");
                    fSb.append(Util.dateParseString(f.getfDate()));
                    fSb.append(" \\\\");
                    fSb.append(newLine);
                }
            }
            mainTemp = mainTemp.replace("$finacingInfo", fSb.toString());

            if(report.headElements!=null){
                StringBuffer headStr = new StringBuffer();
                for (int i=0; i<report.headElements.size(); i++){
                    headStr.append(report.headElements.get(i).text());
                    if(i!=report.headElements.size()-1)
                        headStr.append(", ");
                }
                mainTemp = mainTemp.replace("$headTitle", headStr.toString());
            }else {
                mainTemp = mainTemp.replace("$headTitle", StringUtils.EMPTY);
            }

            if(report.tailElements!=null){
                StringBuffer tailStr = new StringBuffer();
                for (int i=0; i<report.tailElements.size(); i++){
                    tailStr.append(report.tailElements.get(i).text());
                    if(i!=report.tailElements.size()-1)
                        tailStr.append(", ");
                }
                mainTemp = mainTemp.replace("$tailTitle", tailStr.toString());
            }else {
                mainTemp = mainTemp.replace("$tailTitle", StringUtils.EMPTY);
            }

            for(PageSummary headSummary : report.headPageSummary){
                String cur = subTemp.replace("$sectionTitle", "顶栏 - " + headSummary.text);
                StringBuffer content = new StringBuffer();
                for (String line : headSummary.summaries){
                    if(line.startsWith("var"))
                        continue;
                    content.append("\\item " + line + newLine);
                }
                cur = cur.replace("$sectionContent", content.toString());
                cur = cur.replace("$sectionImg", headSummary.img);

                StringBuffer keywords = new StringBuffer();
                for (int i=0; i<headSummary.keywords.size();i++){
                    keywords.append(headSummary.keywords.get(i));
                    if(i!=headSummary.keywords.size()-1){
                        keywords.append(",  ");
                    }
                }
                cur = cur.replace("$sectionKeywords", keywords.toString());
                cur = cur.replace("$sectionUrl", Util.urlNormalizer(headSummary.url));
                subBuffer.append(cur.toString() + newLine);
            }

            for(PageSummary tailSummary : report.tailPageSummary){
                String cur = subTemp.replace("$sectionTitle", "底栏 - " + tailSummary.text);
                StringBuffer content = new StringBuffer();
                for (String line : tailSummary.summaries){
                    if(line.startsWith("var"))
                        continue;
                    content.append("\\item " + line + newLine);
                }
                cur = cur.replace("$sectionContent", content.toString());
                cur = cur.replace("$sectionImg", tailSummary.img);
                StringBuffer keywords = new StringBuffer();
                for (int i=0; i<tailSummary.keywords.size();i++){
                    keywords.append(tailSummary.keywords.get(i));
                    if(i!=tailSummary.keywords.size()-1){
                        keywords.append(",  ");
                    }
                }
                cur = cur.replace("$sectionKeywords", keywords.toString());
                cur = cur.replace("$sectionUrl", Util.urlNormalizer(tailSummary.url));
                subBuffer.append(cur.toString() + newLine);
            }

            mainTemp += subBuffer.toString();
            mainTemp += end;
            String unicode = new String(mainTemp.getBytes(),"UTF-8");
            String gbk = new String(unicode.getBytes("GBK"));
            //FileUtils.writeByteArrayToFile(new File(report.getLatexFile()),mainTemp.getBytes());
            FileUtils.writeByteArrayToFile(new File(report.getLatexFile()),unicode.getBytes("GBK"));
            //FileUtils.writeStringToFile(new File(report.getLatexFile()), mainTemp);

            String cmd = FileUtils.readFileToString(new File(report.getCmdTEmpFile()));
            cmd = cmd.replace("$path", report.realPath());
            FileUtils.writeStringToFile(new File(report.cmdFile()), cmd);

            String cc = "cmd /C start /b " + report.cmdFile();

            try {
                Process ps = Runtime.getRuntime().exec(cc);
                //System.out.println(ps.getInputStream());
                InputStream inputStream=ps.getInputStream();
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);

                OutputStream outputStream=ps.getOutputStream();
                OutputStreamWriter outputStreamWriter=new OutputStreamWriter(outputStream);
                BufferedWriter bufferedWriter=new BufferedWriter(outputStreamWriter);

                bufferedWriter.write("R\n");
                bufferedWriter.flush();
                String temp=null;
                while((temp=bufferedReader.readLine())!=null);

                ps.waitFor();
                int i = ps.exitValue();
                if (i == 0) {
                    System.out.println("Finished.") ;
                } else {
                    System.out.println("Failed.") ;
                }
            } catch(IOException ioe) {
                ioe.printStackTrace();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
