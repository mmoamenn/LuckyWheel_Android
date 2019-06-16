# LuckyWheel Android

Android custom component that displays a lucky wheel. it features easy customize of colors , text and addition of items and it's very trivial to integrate in your application.
 
 **Sample**
 
 ![Lucky wheel](https://github.com/mmoamenn/LuckyWheel_Android/blob/master/samples/pic_sample_mini.png). 
   
 **Installing**
 
 Add it in your root build.gradle at the end of repositories:
 
  ```groovy
 	allprojects {
 		repositories {
 			...
 			maven { url 'https://jitpack.io' }
 		}
 	}
  ```
 	
 Step 2. Add the dependency
 
  ```groovy
  dependencies {
 		compile 'com.github.mmoamenn:LuckyWheel_Android:0.1.2'
 	}
  ```
 	
 **XML**
 
```xml
<com.bluehomestudio.luckywheel.LuckyWheel
        android:id="@+id/lwv"
        android:layout_width="250dp"
        android:layout_height="250dp"
        android:layout_centerInParent="true"
        LuckyWheel:background_color="@color/colorPrimary" />
```

         
 You must use the following properties in your XML
 
 _Choose the background color_ 
 
```xml
LuckyWheel:background_color
```
 
 _Choose wheel image arrow_ 
 
 ```xml
LuckyWheel:arrow_image
```
 
 **JAVA**
 
 Functions to use after reference the Lucky wheel
 
 * Create wheel sections 
 
 ```java
 List<WheelItem> wheelItems = new ArrayList<>();
 wheelItems.add(new WheelItem(Color.LTGRAY, BitmapFactory.decodeResource(getResources(),
                 R.drawable.ic_action_name) , "text 1"));
                 
 wheelItems.add(new WheelItem(Color.BLUE, BitmapFactory.decodeResource(getResources(),
                 R.drawable.ic_action_name) , "text 2"));
                 
wheelItems.add(new WheelItem(Color.BLACK, BitmapFactory.decodeResource(getResources(),
                 R.drawable.ic_action_name) , "text 3"));
                 
wheelItems.add(new WheelItem(Color.GRAY, BitmapFactory.decodeResource(getResources(),
                 R.drawable.ic_action_name) , "text 4"));`
 ```
 
 * Add sections to wheel  
 
 ```java
  addWheelItems(wheelItems);
 ``` 
 * Rotate by touch -- set target before user touch wheel 
 
 ```java
  setTarget(3);
 ``` 
 * Rotate to section -- not need if you will use touch 
 
 ```java
  rotateWheelTo(2);
 ``` 
 * On target reach listener
 
 ```java
  setLuckyWheelReachTheTarget(new OnLuckyWheelReachTheTarget() {
             
             @Override
             public void onReachTarget() {
             
                 // target reached do operation her    
             
             }
         });
 ```
