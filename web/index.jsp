
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Bootswatch: Cerulean</title>
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
            <a href="#" class="navbar-brand ">Magic Info</a>
            <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="navbar-collapse collapse" id="navbar-main">
            <ul class="nav navbar-nav">
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
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="">Help</a></li>
                <li><a href="">About</a></li>
            </ul>

        </div>
    </div>
</div>


<div class="container">

<div class="page-header" id="banner">
    <div class="row">
        <div class="col-md-12">
            <h1></h1>
            <div class="alert alert-dismissible alert-success"><button type="button" class="close" data-dismiss="alert">×</button>
                <p class="">Search company, founder, investor, news, everything you want!</p>
            </div>

        </div>
    </div>
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div class="input-group">
                <input type="text" class="form-control" placeholder="Company,  Founder,  News,  Investor, Everything " id="search-key">
			<span class="input-group-btn">
			<a class="btn" id="s-i-bt" href="javascript:search();" role="button"></a>
			</span>
            </div>
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
        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading">Panel heading</div>
                <div class="panel-body">
                    <div class="text-center">
                    <img src="00d9ec63372a40d1868645b178e100af.png" class="text-center" width="150" height="150"/>
                    </div>
                </div>
                <div class="panel-body">
                    Panel content
                </div>
                <div class="panel-body">
                    Panel content
                </div>
                <div class="panel-body">
                    Panel content
                </div>
                <div class="panel-body">
                    Panel content
                </div>
                <div class="panel-body">
                    Panel content
                </div>
            </div>
        </div>
        <dvi class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading">Panel heading</div>
                <div class="panel-body">
                    Panel content
                </div>
            </div>
        </dvi>
    </div>

</div>

<footer>
    <div class="row">
        <div class="col-lg-12">
            <p>Released at 6-18-2016.</p>
            <p>Based on <span class="text-info">intelligent crawler.</span></p>
            <p>By now, there are <span class="text-info">28188</span> companies,
                <span class="text-info">234234</span> financing records,
                <span class="text-info">23423423</span> news,
                <span class="text-info">9090</span> investment organizations, and
                <span class="text-info">32444</span> investors.
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
