# CS349 A4

Student: x46yan
Marker: Gregory Philbrick


Total: 105 / 100 (105.00%)

Code:
(CO: won’t compile, CR: crashes, FR: UI freezes/unresponsive, NS: not submitted)


Notes:`` ``
# A4 Marking Scheme

## Deliverables (5%)

1. [5/5] The code compiles and runs in Android Studio or IntelliJ using SDK 26 and the Google Pixel AVD, API 26.

## Basics (10%)

2. [4/4] The application consists of a single screen with an image grid, and a main toolbar showing the filter widget.

3. [6/6] There are clear mechanisms to clear the images, load a set of images, load image from a URL.

## Screen Layout (30%)

4. [12/12] Horizontal Layout shows a fixed number of columns (2 or more), no horizontal scrollbar should appear.				

5.  [12/12] Vertical Layout shows a single column, no horizontal bar should appear.

6. [6/6] Layout is automatically switched based on device orientation

## Images (35%)

7. [15/15] The "load images button" should load 10 images and display them in the grid. Images can either be added to the list, or the list can be replaced completely with the set of 10 images; both are acceptable.			

8. [15/15] Images are reasonably sizes for the device and orientation. Images should be scaled to be visible, but do NOT need to be scaled to the same size.

9. [5/5] Selecting an image shows an enlarged image. There is a way of dismissing this image.

## Ranking (20%)

10. [5/5] Individual images can be ranked by changing the rating for the image.		

11. [10/10] The ratings filter on the toolbar filters out the images that don't meet this rating or higher.

12. [5/5] Changing the rating of an image should immediately be reflected in the list (i.e. if it no longer meets the filter criteria, it should disappear).

## Bonus (10%)

13. [5/10] Implements search button and keyword enhancement.

-5 not implemented: Prompt the user for a keyword (using a popup window), search Google Images for that keyword, and display the top-ten results in your image grid.

