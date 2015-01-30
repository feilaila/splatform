<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
    <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
    <title>My Sample Payment App</title>
  </head>
  <body>

    <h2>Purchase a product:</h2>
    <button id="pay">Buy Product</button>
    <div class="returndata" id="output"></div>


    <script type="text/javascript">
      window.fbAsyncInit = function() {
        FB.init({
          appId      : '1540538392868058',
          status     : true,
          cookie     : true,
          xfbml      : true
        });

        function buy() {
          var obj = {
            method: 'pay',
            action: 'purchaseitem',
            product: 'http://www.friendsmash.com/og/coins.html',
            quantity: 20,
            pricepoint_id: "this_is_a_test_pricepoint"
          };

          FB.ui(obj, function(data) {
              console.log(data);
            });
        }

        document.getElementById('pay').onclick = function() {buy()};
      };

      // Load the SDK Asynchronously
      (function(d){
        var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
        if (d.getElementById(id)) { return; }
        js = d.createElement('script'); js.id = id; js.async = true;
        js.src = "//connect.facebook.net/en_US/all.js";
        ref.parentNode.insertBefore(js, ref);
      }(document));
    </script>
    
    <div id="fb-root"></div>
  </body>
</html>