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
    <button onclick="buy()">Buy</button>
    <div id="fb-ui-return-data"></div>
    <div id="fb-root"></div>

    <script src="http://connect.facebook.net/en_US/all.js"></script>
    <script> 
　　　 // 这里初始化FB 的 SDK
      FB.init({appId: "1540538392868058", status: true, cookie: true});　　

      // The dialog only opens if you've implemented the
      // Credits Callback payments_get_items.
      function buy() {
        var obj = {
          method: 'pay', 
          action: 'buy_item',
          // You can pass any string, but your payments_get_items must
          // be able to process and respond to this data.
          order_info: {'item_id': '1a'},  // 这里是支付信息,这里的信息在支付中心与FB的支付协议中有
          dev_purchase_params: {'oscif': true}
        };
　　　　　
　　　　 // 调用 支付页面
        FB.ui(obj, js_callback);
      }

　　　 // 反馈函数，作为调试用很好
      // This JavaScript callback handles FB.ui's return data and differs
      // from the Credits Callbacks.
      var js_callback = function(data) {
        if (data['order_id']) {
          // Facebook only returns an order_id if you've implemented
          // the Credits Callback payments_status_update and settled
          // the user's placed order.

          // Notify the user that the purchased item has been delivered
          // without a complete reload of the game.
          write_callback_data(
                    "<br><b>Transaction Completed!</b> </br></br>"
                    + "Data returned from Facebook: </br>"
                    + "Order ID: " + data['order_id'] + "</br>"
                    + "Status: " + data['status']);
        } else if (data['error_code']) {
          // Appropriately alert the user.
          write_callback_data(
                    "<br><b>Transaction Failed!</b> </br></br>"
                    + "Error message returned from Facebook:</br>"
                    + data['error_code'] + " - "
                    + data['error_message']);
        } else {
          // Appropriately alert the user.
          write_callback_data("<br><b>Transaction failed!</b>");
        }
      };

      function write_callback_data(str) {
        document.getElementById('fb-ui-return-data').innerHTML=str;
      }
    </script>
  </body>
</html>