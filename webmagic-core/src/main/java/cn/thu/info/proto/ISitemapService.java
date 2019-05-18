package cn.thu.info.proto;

import cn.thu.info.model.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface ISitemapService extends Remote
{
    String addTask(String query) throws RemoteException;

    double getProcess(String sessionId) throws RemoteException;

    boolean isFinished(String sessionId) throws RemoteException;

    int getStatus(String sessionId) throws RemoteException;

    FileInformation download(String sessionId) throws RemoteException;

    int addEmail(String sessionId, String email) throws RemoteException;

    Report getCompanyInfo(String query) throws RemoteException;

    List<SnapshotTask>  getProcessList(String sessionId) throws RemoteException;

    QueryResult getCompanyInfoByName(String s) throws RemoteException;

    Report getCompanyInfoById(String s) throws RemoteException;

    Report getCompanyInfoByUrl(String url)  throws RemoteException;

    SearchResult searchCompanyInfo(CompanyExample ex) throws RemoteException;


}
