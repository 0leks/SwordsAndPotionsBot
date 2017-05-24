# SwordsAndPotionsBot
This is a bot for the flash game on Kongregate, Swords and Potions 2
http://www.kongregate.com/games/EdgebeeStudios/swords-and-potions-2?acomplete=swords

* In the driver file you have to specify the order in which to make items as well as their cost and where they appear on the menu.
* The application then parsed the screen to find the game window and the resource display.
* To figure out how many of each resource you have,
  * The application first finds all of the numbers in the resource display and binarizes them into black and white.
  * Then they are compared to a library of digit images to figure out which digit it is.
    * It simply selects the one that is most similar.
  * Every time a digit that is slightly different is found, it is added to the library.
  * To initialize the library, I added 10 handlabeled images, one for each digit.
  * Since then, the library has expanded to 113 different images, around 11 per digit.
  * With this self-expanding library of digits, the application was able to continue functioning when the game developer made small changes to the font style.
