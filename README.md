# GOT 'EM RECIPES'

** Food you got . Meals you want.**

This recipe app is a Java android application that has been designed to help the users to reduce the food waste by tracking the ingredients they already have by suggesting the recipes they can make by using the ingredients that they have in their pantry

## The Features

- Its able to Add, edit and even delete the pantry ingredients
- Store the ingredients quantities, units , categories and even expiry dates
- it is a persisitent pantry that stores data using room and SQLite
- It has 20 preloaded recipes
- It has strict recipe matching 
- Quantity checking
- basic unit cobversions
- Singular and plural ingreients will be matched
- A suggested recipe screen
- A recipe detail screen
- A full recipe browser
- Settings screen
- A bottom navigation panel between the home, pantry , recpies and settings screen
- Input validations
- Empty state feedback when the recipes dont match
- expiry warning systems thats linked to settings
- An expiring soon warning in pantry in the ingredients close to the expiry date
- expired warning for ingredients that are past their expiry dates

## The Database

This application uses SQLite through the android room persistence Library
Room was chosen for this project because it was able to provide a structured and a much more reliable way to work with the local SQLite database while also reducing the amount of sql code required.
The database will be there to store the pantry items , the recipes and the recipe ingredients locally through the device
This will allow the pantry data to them remain stored after the application is opened and closed

## The Strict Recipe Matching
A recipe will only be shown in the suggested recipes screen when :
1 : Every required ingredient exists in the pantry.
2 : The pantry contains at least the required ingredients.
3 : The compatible measurement units ca be converted where it is necessary.
Recipes with any missing ingredients or some insufficient quantities will be excluded from the suggested recipes list
This matching system will also be able to handle simple ingredient named differences such as the singular even the plural versions.

When expiry warnings are enabled in the settings :
- The ingredients that are close to expiration date , he display under there names in pantry will say **EXPIRING SOON**
- Ingredients that have passed the expiry date will display as **EXPIRED**
- Items in the pantry without an expiry date will not display a warning.

When the settings are disabled then the expiry warnings will not show

## The navigation
The main sections of the application will use the bottom navigation bar
The navigation bar gives the user access to
- Home
- pantry
- recipes
- settings

Additionally the screen such as the  Add/Edit ingredients and a recipe Detail use there own back and cancel controls

## The technology used 
- Java
- Android Studio
- Room Persisitence Library
- SQLite
- Recycler View
- Custom Recycler Adaters
- Material Components 
- SHaredPreferences 
- Git
- GitHub

## How to run it

1 : Clone or download the repository
2 : Open the project in Android studio
3 : Allow gradle to finish syncing 
4 : Select on Android emulator or a compatible physical Android pevice
5 : Click the Run button in Andorid studios.
6 : the app will open o to the GOT 'EM RECIPES home screen 

## Minimum Android version
 Minimum SDK :  API 24
 
## Project Structure 
The project contains separate components for 
- activities
- room database 
- data models
- DAO interfaces
- recyclerView adapters
- Recipe seeding
- Recipe matching logic
- XML layouts and resources

## Testing Environment
- Android studios
- Pixel 8 Emulator
- Android API 35
- minimum SDK :API 24

## Author
Uthmaan Hassen Mayet