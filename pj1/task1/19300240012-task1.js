Java.perform(function () {
    // Function to hook is defined here
    var MainActivity = Java.use('com.kugou.android.app.splash.SplashActivity');
  
    // Whenever button is clicked
    var beforClick = MainActivity.r;
    var onClick = MainActivity.b
    beforClick.implementation = function () {
      // Show a message to know that the function got called
      send('begin');
  
      // Call the original onClick handler
      beforClick.call(this);
      
      onClick.call(this);
  
      // Log to the console that it's done, and we should have the flag!
      console.log('HooK成功');
    };
  });