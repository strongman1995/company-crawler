<%@ page import="cn.thu.info.model.CompanyWithBLOBs" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="cn.thu.info.model.Reginfo" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>智能报告</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <link rel="stylesheet" href="css/bootstrap_t.min.css" media="screen">
    <link rel="stylesheet" href="css/index.css" media="screen">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="../bower_components/html5shiv/dist/html5shiv.js"></script>
    <script src="../bower_components/respond/dest/respond.min.js"></script>
    <![endif]-->
    <script>

        var _gaq = _gaq || [];
        _gaq.push(['_setAccount', 'UA-23019901-1']);
        _gaq.push(['_setDomainName', "bootswatch.com"]);
        _gaq.push(['_setAllowLinker', true]);
        _gaq.push(['_trackPageview']);

        (function() {
            var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
            ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
            var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
        })();

    </script>
</head>
<body>
<div class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a href="#" class="navbar-brand ">智能报告</a>
            <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="navbar-collapse collapse" id="navbar-main">
            <!--ul class="nav navbar-nav">
                <li>
                    <a href="">Company</a>
                </li>
                <li>
                    <a href="">Founder</a>
                </li>
                <li>
                    <a href="">Investor</a>
                </li>
                <li>
                    <a href="">News</a>
                </li>
            </ul -->

            <ul class="nav navbar-nav navbar-right">
                <li><a href="">帮助</a></li>
                <li><a href="">关于</a></li>
            </ul>

        </div>
    </div>
</div>


<div class="container">

<div class="page-header" id="banner">
    <div class="row">
        <!--div class="col-md-12">
            <h1></h1>
            <div class="alert alert-dismissible alert-success"><button type="button" class="close" data-dismiss="alert">×</button>
                <p class="">Search company, founder, investor, news, everything you want!</p>
            </div>

        </div-->
    </div>
    <div class="row">
        <div class="col-md-10 col-md-offset-1">

            <form action="index">
            <div class="input-group">
                <input type="text" class="form-control" placeholder="请输入产品名称" id="search-key" name="keywords">
			<span class="input-group-btn">
			<input  class="btn" id="s-i-bt"  role="button" value="" type="submit"/>
			</span>
            </div>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <div class="page-header">
                <h3 >Reuslts</h3>
            </div>
        </div>
    </div>
    <div class="row">
        <% List<CompanyWithBLOBs> list =(List<CompanyWithBLOBs>) request.getAttribute("companyList");%>
        <% for (CompanyWithBLOBs company : list){ %>
        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading"><% out.print(company.getName());%>-公司信息</div>
                <div class="panel-body">
                    公司名称：<%out.print(company.getCompanyName()); %>
                </div>
                <div class="panel-body">
                    主页：<%out.print(company.getWebsite());%>
                </div>
                <div class="panel-body">
                    城市：<%out.print(company.getCity());%>
                </div>
                <div class="panel-body">
                    创办时间：<% out.print(company.getSetupTime());%>
                </div>
                <div class="panel-body">
                    业务类型：<% out.print(company.getField1());%>
                </div>
                <div class="panel-body">
                    融资阶段：<% out.print(company.getFinancingStep());%>
                </div>
                <div class="panel-body">
                    公司简介：<% out.print(company.getIntroduction());%>
                </div>
            </div>
        </div>
        <% }%>

        <% List<Reginfo> regList =(List<Reginfo>) request.getAttribute("reginfoList");%>
        <% for (Reginfo reginfo : regList){ %>
        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading"><% out.print(reginfo.getCompanyName());%>-工商注册</div>
                <div class="panel-body">
                    公司名称：<%out.print(reginfo.getCompanyName()); %>
                </div>
                <div class="panel-body">
                    注册资本：<%out.print(reginfo.getRegCapital());%>
                </div>
                <div class="panel-body">
                    注册日期：<%out.print(reginfo.getRegDate());%>
                </div>
                <div class="panel-body">
                    法人代表：<% out.print(reginfo.getLealPerson());%>
                </div>
                <div class="panel-body">
                    注册号：<% out.print(reginfo.getRegId());%>
                </div>
            </div>
        </div>
        <% }%>
    </div>

</div>

<footer>
    <div class="row">
        <div class="col-lg-12">
            <p>Released at 6-18-2016.</p>
            <p>Based on <span class="text-info">intelligent crawler.</span></p>
            <p>By now, there are <span class="text-info">28188</span> companies,
                <span class="text-info">12892</span> financing records,
                <span class="text-info">16000</span> news,
                <span class="text-info">0</span> investment organizations,
                <span class="text-info">0</span> investors, and
                <span class="text-info">0</span> founders..
            </p>
        </div>
    </div>

</footer>


</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery.1.11.3.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">/* <![CDATA[ */(function(d,s,a,i,j,r,l,m,t){try{l=d.getElementsByTagName('a');t=d.createElement('textarea');for(i=0;l.length-i;i++){try{a=l[i].href;s=a.indexOf('/cdn-cgi/l/email-protection');m=a.length;if(a&&s>-1&&m>28){j=28+s;s='';if(j<m){r='0x'+a.substr(j,2)|0;for(j+=2;j<m&&a.charAt(j)!='X';j+=2)s+='%'+('0'+('0x'+a.substr(j,2)^r).toString(16)).slice(-2);j++;s=decodeURIComponent(s)+a.substr(j,m-j)}t.innerHTML=s.replace(/</g,'&lt;').replace(/>/g,'&gt;');l[i].href='mailto:'+t.value}}catch(e){}}}catch(e){}})(document);/* ]]> */</script></body>
</html>
