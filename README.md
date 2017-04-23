# LuckyWheel Android

Android component for lucky wheel view easy to integrate and 
  in your code 
  
 **Installing**
 
 Add it in your root build.gradle at the end of repositories:
 
 	allprojects {
 		repositories {
 			...
 			maven { url 'https://jitpack.io' }
 		}
 	}
 	
 Step 2. Add the dependency
 
 	dependencies {
 		compile 'com.github.mmoamenn:LuckyWheel_Android:0.0.1'
 	}
 	
 **XML**
 
 `<com.bluehomestudio.luckywheel.LuckyWheel
         android:id="@+id/lwv"
         android:layout_width="250dp"
         android:layout_height="250dp"
         android:layout_centerInParent="true"
         LuckyWheel:background_color="@color/colorPrimary"
         LuckyWheel:arrow_image="@drawable/ic_action"/>`
         
 You must use the following properties in your XML
 
 _Choose the background color_ 
 
 `LuckyWheel:background_color`
 
 _Choose wheel image arrow_ 
 
 `LuckyWheel:arrow_image`
 
 **JAVA**
 
 Functions to use after reference the Lucky wheel
 
 * Create wheel sections 
 
 `List<WheelItem> wheelItems = new ArrayList<>();`
 
 `wheelItems.add(new WheelItem(Color.LTGRAY, BitmapFactory.decodeResource(getResources(),
                 R.drawable.ic_action_name)));`
                 
 `wheelItems.add(new WheelItem(Color.BLUE, BitmapFactory.decodeResource(getResources(),
                 R.drawable.ic_action_name)));`
                 
 `wheelItems.add(new WheelItem(Color.BLACK, BitmapFactory.decodeResource(getResources(),
                 R.drawable.ic_action_name)));`
                 
 `wheelItems.add(new WheelItem(Color.GRAY, BitmapFactory.decodeResource(getResources(),
                 R.drawable.ic_action_name)));`
                 
 * Add section to wheel  
 
 `addWheelItems(wheelItems);`
 
 * Rotate to any section section 
 
 `rotateWheelTo(2);`
 
 * On target reach listener
 
 `setLuckyWheelReachTheTarget(new OnLuckyWheelReachTheTarget() {
             @Override
             public void onReachTarget() {
                 // target reached do operation her 
             }
         });`