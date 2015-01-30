<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8" />
<title>Facebook 支付</title>
<script>
  // This is called with the results from from FB.getLoginStatus().
  function statusChangeCallback(response) {
    console.log('statusChangeCallback');
    console.log(response);
    // The response object is returned with a status field that lets the
    // app know the current login status of the person.
    // Full docs on the response object can be found in the documentation
    // for FB.getLoginStatus().
    if (response.status === 'connected') {
    	
    	document.getElementById('token').innerHTML =
        'accessToken:'+ response.authResponse.accessToken+"<br>"+
        'expiresIn:'+response.authResponse.expiresIn+"<br>"+
        'signedRequest:'+response.authResponse.signedRequest+"<br>"+
        'userID:'+response.authResponse.userID;
    	
    	
    	
    	var kk  = document.createElement("button");
    	kk.onclick=function (){
    		testLogout();
    	};
    	kk.innerHTML='退出facebook';
		
    	document.getElementById('logout-btn').innerHTML='';
    	document.getElementById('logout-btn').appendChild(kk);
      // Logged into your app and Facebook.
      testAPI();
    } else if (response.status === 'not_authorized') {
      // The person is logged into Facebook, but not your app.
      document.getElementById('status').innerHTML = 'Please log ' +
        'into this app.';
    } else {
      // The person is not logged into Facebook, so we're not sure if
      // they are logged into this app or not.
      document.getElementById('status').innerHTML = 'Please log ' +
        'into Facebook.';
      
      document.getElementById('token').innerHTML = '';
    }
  }

  // This function is called when someone finishes with the Login
  // Button.  See the onlogin handler attached to it in the sample
  // code below.
  function checkLoginState() {
    FB.getLoginStatus(function(response) {
      statusChangeCallback(response);
    });
    
  }

  window.fbAsyncInit = function() {
  FB.init({
    appId      : '1540538392868058',
    cookie     : true,  // enable cookies to allow the server to access 
                        // the session
    xfbml      : true,  // parse social plugins on this page
    version    : 'v2.1' // use version 2.1
  });

  // Now that we've initialized the JavaScript SDK, we call 
  // FB.getLoginStatus().  This function gets the state of the
  // person visiting this page and can return one of three states to
  // the callback you provide.  They can be:
  //
  // 1. Logged into your app ('connected')
  // 2. Logged into Facebook, but not your app ('not_authorized')
  // 3. Not logged into Facebook and can't tell if they are logged into
  //    your app or not.
  //
  // These three cases are handled in the callback function.

  FB.getLoginStatus(function(response) {
    statusChangeCallback(response);
  });

  };

  // Load the SDK asynchronously
  (function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));

  // Here we run a very simple test of the Graph API after login is
  // successful.  See statusChangeCallback() for when this call is made.
  function testAPI() {
    console.log('Welcome!  Fetching your information.... ');
    FB.api('/me', function(response) {
      console.log('response:'+response);
      console.log('Successful login for: ' + response.name);
      document.getElementById('status').innerHTML =
        'Thanks for logging in, ' + response.name + '!<br>';
    });
    
    FB.api('/me/?fields=currency', function(data) {
   	  if (!data || data.error) {
   	    // handle errors as appropriate for your application
   	  } else {
   	    // use data.currency.user_currency and data.currency.usd_exchange 
   	    // fields to calculate local pricing
   		console.log(data);
   	    console.log(data.currency.user_currency);
	   	console.log(data.currency.usd_exchange);
   	  }
   	});
  }
  
  function testLogout(){
	  FB.logout(function(response) {
	        // Person is now logged out
		  statusChangeCallback(response);
	    });
  }
</script>


<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/zh_CN/sdk.js#xfbml=1&appId=1540538392868058&version=v2.0";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>
<!--
  Below we include the Login Button social plugin. This button uses
  the JavaScript SDK to present a graphical Login button that triggers
  the FB.login() function when clicked.
-->



<fb:login-button scope="public_profile,email" onlogin="checkLoginState();">
</fb:login-button>

<br>
<div id="logout-btn">

</div>


<div id="status">
</div>
<div id="token">
</div>
</body>
</html>
