<%@page import="com.onelogin.saml2.Auth"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%> 
<%@page import="in.co.ntpc.pradip.SSO.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	 <meta charset="utf-8">
	 <meta http-equiv="X-UA-Compatible" content="IE=edge">
     <meta name="viewport" content="width=device-width, initial-scale=1">
	 <title>IBM WebSphere Portal Single Sign Out</title>
	 <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

     <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
     <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
     <!--[if lt IE 9]>
       <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
       <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
     <![endif]-->
     
     <script>
     
         //document.cookie = "LtpaToken2" + "=" + "" + ";domain=.ntpc.co.in;path=/;expires=Thu, 01-Jan-70 00:00:01 GMT;"
         //document.cookie = "LtpaToken" + "=" + "" + ";domain=.ntpc.co.in;path=/;expires=Thu, 01-Jan-70 00:00:01 GMT;"
         //document.cookie = "JSESSIONID" + "=" + "" + ";domain=pradiptest.ntpc.co.in;path=/;expires=Thu, 01-Jan-70 00:00:01 GMT;"
         
         //setTimeout(function(){
           // window.location.href = 'https://pradip.ntpc.co.in';
         //}, 5000);
         
        
      </script>
</head>
<body>
	<div class="container">
    	<h1>IBM WebSphere Portal Single Sign Out</h1>
    	<b>Logout</b>   	
	    <%

	    

		Auth auth = new Auth(request, response);
		request.getSession().invalidate();
		
		//auth.get
	    
	    Cookie LtpaToken2 = new Cookie("LtpaToken2","val1");
	   	LtpaToken2.setDomain(".ntpc.co.in");
	   	LtpaToken2.setPath("/");
	   	LtpaToken2.setSecure(true);
		response.addCookie(LtpaToken2);
		
		Cookie LtpaToken = new Cookie("LtpaToken","val2");
	   	LtpaToken.setDomain(".ntpc.co.in");
	   	LtpaToken.setPath("/");
	   	LtpaToken.setSecure(true);
		response.addCookie(LtpaToken);
		
		
		Cookie jsession = new Cookie("JSESSIONID","val2");
		jsession.setDomain("pradiptest.ntpc.co.in");
		jsession.setPath("/");
		jsession.setSecure(true);
		response.addCookie(jsession);
		
		
		response.addHeader("Access-Control-Allow-Credentials", "true");
		response.addHeader("Access-Control-Allow-Origin", "login.microsoftonline.com");
		
		
	
		String result=auth.processSLO(false,null,false);
		
		//out.println(result);
	
		
		List<String> errors = auth.getErrors();
		
		if (errors.isEmpty()) {
			
			out.println("<p>Sucessfully logged out</p>");
			//out.println("<a href=\"dologin.jsp\" class=\"btn btn-primary\">Login</a>");
			
			
		} else {
			out.println("<p>");
			for(String error : errors) {
				out.println(" " + error + ".");
			}
			out.println("</p>");
		}
		
		
	%>
	
	<p>Web page redirects to <a href="https://pradip.ntpc.co.in">PRADIP Production</a> </p>
	</div>
	
	
</body>
</html>
