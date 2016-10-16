NotificationListenerService-Example
===================================

##Introduction
NotificationListenerService is introduced in Android 4.3 (API 18). It allows an application to receive information about notifications as it creates or removes. NotificationListenerService class is derived from the Service class. It has two abstract methods namely 1. onNotificationPosted 2. onNotificationRemoved.  
To use NotificationListenerService, we need to create a java file which extends NotificationListenerService and implement two callback methods. Both methods have a parameter named "sbn", which is an object of StatusBarNotification class. StatusBarNotification provides necessary information about Notifications.
NotificationListenerService provides facility to fetch active notifications using getActiveNotifications and also provides a feature to remove notifications using cancelAllNotifications.

##Updated Project and Credits
This example is based in the [original example in GitHub](https://github.com/kpbird/NotificationListenerService-Example) by [Ketan Parmar (kpbird)](https://github.com/kpbird). It has been completely rebuild as an Android Studio project (original example apparently was created with Eclipse and converted to Gradle project).

The updated project has been used to test the assumptions for the [following answer](https://stackoverflow.com/questions/39685835/how-do-some-apps-block-replace-heads-up-notifications/39985892#39985892) in [StackOverflow](https://stackoverflow.com).

##Useful Methods
1. NotificationListenerService
	* onNotificationPosted
	* onNotificationRemoved
2. StatusBarNotification
	* getId
	* getNotification
	* getPackageName
	* getPostTime
	* isClearable
	* isOngoing

##Note
User require to enable notification permission from "Settings > Security > Notification access".

After updating the app it's possible that you need to uncheck and add again the permission, as seen in [this other answer in StackOverflow](https://stackoverflow.com/questions/17911883/cannot-get-the-notificationlistenerservice-class-to-work/37081128#37081128). This has been confirmed to be true in an Android 5.0.1 phone. YMMV

![Mou icon](http://1.bp.blogspot.com/-7Q9G72-ZLCw/UfirCZP-H_I/AAAAAAAAEOk/aqX_YHs6s6Q/s400/device-2013-07-31-113010.png)
![Mou icon](http://1.bp.blogspot.com/-h_bFIcDWWp8/UfirCzDrC_I/AAAAAAAAEO0/9_aMH5EM6Dg/s400/device-2013-07-31-113539.png)
![Mou icon](http://2.bp.blogspot.com/-thl_wNKzILI/UfirDX6NR2I/AAAAAAAAEO4/_o5FWLmkJ2o/s400/device-2013-07-31-113701.png)
![Mou icon](http://1.bp.blogspot.com/-5KyUJQVOVzE/UfirDhqpFzI/AAAAAAAAEPA/RiZoI9dF--Q/s400/device-2013-07-31-113720.png)
