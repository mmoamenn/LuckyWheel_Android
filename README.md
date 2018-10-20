# LuckyWheel Android

Android custom component that displays a lucky wheel. it ⁠ features easy customize of colors, addition of items and it's very trivial to integrate in your application.
 
 **Sample**
 
 ![Lucky wheel](https://github.com/mmoamenn/LuckyWheel_Android/blob/master/samples/videotogif_2017.04.24_01.42.22.gif)
  
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
 		compile 'com.github.mmoamenn:LuckyWheel_Android:0.1.0'
 	}
 	
 **XML**
 
     <com.bluehomestudio.luckywheel.LuckyWheel
        android:id="@+id/lwv"
        android:layout_width="250dp"
        android:layout_height="250dp"
        android:layout_centerInParent="true"
        LuckyWheel:background_color="@color/colorPrimary" />

         
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
                 R.drawable.ic_action_name) , "text 1"));`
                 
    `wheelItems.add(new WheelItem(Color.BLUE, BitmapFactory.decodeResource(getResources(),
                 R.drawable.ic_action_name) , "text 2"));`
                 
    `wheelItems.add(new WheelItem(Color.BLACK, BitmapFactory.decodeResource(getResources(),
                 R.drawable.ic_action_name) , "text 3"));`
                 
    `wheelItems.add(new WheelItem(Color.GRAY, BitmapFactory.decodeResource(getResources(),
                 R.drawable.ic_action_name) , "text 4"));`
                 
 * Add sections to wheel  
 
    `addWheelItems(wheelItems);`
    
 * Rotate by touch -- set target before user touch wheel 
 
    `setTarget(3);`

 
 * Rotate to section -- not need if you will use touch 
 
    `rotateWheelTo(2);`
 
 * On target reach listener
 
        setLuckyWheelReachTheTarget(new OnLuckyWheelReachTheTarget() {
             
             @Override
             public void onReachTarget() {
             
                 // target reached do operation her    
             
             }
         });
