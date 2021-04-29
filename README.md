# LuckyWheel Android

Android custom component that displays a lucky wheel. it features easy customize of colors , text and addition of items and it's very trivial to integrate in your application.

# What's new in 0.3.0

- Fix the image rotation issue

 **Sample**
 
 ![Lucky wheel](https://github.com/mmoamenn/LuckyWheel_Android/blob/master/samples/color_image.png). 

**Overview**

The LuckyWheel shows a wheel which can spin, and a marker that indicates the selected item in the wheel. Items may be customized with text, image, or a color.

The wheel starts off stationary. Calling the `setTarget()` method with an integer parameter will set the wheel to spin to that section when the user taps the wheel in the UI. Calling `rotateWheelTo()` with an integer parameter will cause the wheel to spin to that section immediately.

The wheel spins for a few seconds, then fires the `setLuckyWheelReachTheTarget` callback to notify the application it has stopped moving.


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
 		implementation 'com.github.mmoamenn:LuckyWheel_Android:0.3.0'
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
 _Change item image padding_ 

 ```xml
LuckyWheel:image_padding
```
 
 **Kotlin**
 
 Functions to use after reference the Lucky wheel
 
 * Create wheel sections 
 
 ```kotlin
 val wheelItems: MutableList<WheelItem> = ArrayList()

 wheelItems.add(WheelItem(Color.LTGRAY,
 BitmapFactory.decodeResource(resources, R.drawable.ic_action_name),
 "text 1"))
 
 wheelItems.add(WheelItem(Color.BLUE,
 BitmapFactory.decodeResource(resources, R.drawable.ic_action_name),
 "text 2"))
 
 wheelItems.add(WheelItem(Color.BLACK,
 BitmapFactory.decodeResource(resources, R.drawable.ic_action_name),
 "text 3"))
 
 wheelItems.add(WheelItem(Color.GRAY,
 BitmapFactory.decodeResource(resources, R.drawable.ic_action_name)
 ,"text 4"))
 
 wheelItems.add(WheelItem(Color.RED,
 BitmapFactory.decodeResource(resources, R.drawable.ic_action_name),
 "text 5"))
 
 wheelItems.add(WheelItem(Color.BLACK,
 BitmapFactory.decodeResource(resources, R.drawable.ic_action_name),
 "text 6"))
 
 ```
 
 * Add sections to wheel  
 
 ```kotlin
  addWheelItems(wheelItems)
 ``` 
 * Rotate by touch -- set target before user touch wheel 
 
 ```kotlin
  setTarget(3)
 ``` 
 * Rotate to section -- not need if you will use touch 
 
 ```koltin
  rotateWheelTo(2)
 ``` 
 * On target reach listener
 
 ```koltin
  lwv.setLuckyWheelReachTheTarget {
  
  }
 ```
 
 
# Note
Last version before support AndroidX ```com.github.mmoamenn:LuckyWheel_Android:0.1.2```
