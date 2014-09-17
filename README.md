# InstagramViewer App

A simple Android app to show popular photos on Instagram.
The full requirements for this app can be found [here](http://courses.codepath.com/courses/intro_to_android/week/1#!assignment).

Time spent: 8 hours

### Completed user stories:

All user stories, required, optional, and advanced were completed. Following is the list for the same:

#### Required:

 * [x] User should be able to scroll through current popular photos from Instagram
 * [x] User should be able to see the 
 	- actual photo
 	- the username of the user who posted it and 
 	- the associated caption if one exists
 
#### Optional:
 
 * [x] Show relative timestamp of when the photo was posted
 * [x] Show like count
 * [x] Show the user profile image
 
#### Advaned:

 * [x] Add pull-to-refresh (I used the third party one)
 * [x] Show the latest two comments for each photo
 * [x] For every comment, show the username of the author
 * [x] Display each photo with the same style and proportions as the real Instagram app
 
### Notes:

- Used Android Studio for development
- Tried both P2R libraries - Native and ThirdParty, but decided to stick to the Third party one
- Used Genymotion emulator 

### Walkthrough of all user stories:
<br />
![Video Walkthrough](AndroidInstagramViewerApp.gif)
<br />
<br />

GIF created with [LiceCap](http://www.cockos.com/licecap/).<br />

### Third party libraries, tools, and sites used:

- [Active Async](http://loopj.com/android-async-http/doc/com/loopj/android/http/AsyncHttpClient.html) for making network requests.
- [Picasso](http://square.github.io/picasso/) for downloading and caching images.
- [CircularImageView](https://github.com/Pkmmte/CircularImageView) for preseting user profile images in a circle.
- [IconFinder](https://www.iconfinder.com) for app icons.
- [iconmonstr](http://iconmonstr.com) for images/assets in the app.
