package com.uthmaan.gotemrecipes.data;
import com.uthmaan.gotemrecipes.database.AppDatabase;
import com.uthmaan.gotemrecipes.model.Recipe;
import com.uthmaan.gotemrecipes.model.RecipeIngredient;
public class RecipeSeeder {
    // this will be here to add the starter recipe collection only if the database is empty
    public static void seedRecipes(AppDatabase appDatabase) {
        if (appDatabase.recipeDao().getRecipeCount() > 0) {
            return;
        }
        Recipe harrissaHoneyChicken = new Recipe(
                "Harissa Honey Chicken Bowls",
                "Sweet , smoky and spicy chicken served over a simple rice bowl.",
                "Got Some Heat ",
                "1. Cook the rice until its tender. " +
                        "2. Season and cook the chicken until it is golden." +
                        "3. Stir in harissa and the honey." +
                        "4. Serve thechicke over rice."
        );
        long harrissaRecipeId =
                appDatabase.recipeDao().addRecipe(harrissaHoneyChicken);
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient(
                        (int) harrissaRecipeId,
                        "Chicken Breast",
                        300,
                        "g"
                )
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient(
                        (int) harrissaRecipeId,
                        "Harissa Paste",
                        2,
                        "tbsp"
                )
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient(
                        (int) harrissaRecipeId,
                        "Honey",
                        1,
                        "tbsp"
                )
        );
        Recipe creamyCajunChickenOrza = new Recipe(
                "Creamy Cajun Orzo",
                "Creamy, smoky chicken folded through tender orzo with a little Cajun heat.",
                "Quick Cravings",
                "1. Cook the orzo until it is tender." +
                        "2. Season and cook the chicken" +
                        "3. Add the garlic, Cajun seasoning and cream." +
                        "4. Stir the orzo through the sauce and then serve it."
        );
        long cajunOrzoRecipeId =
                appDatabase.recipeDao().addRecipe(creamyCajunChickenOrza);
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) cajunOrzoRecipeId, "Chicken Breast", 300, "g")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) cajunOrzoRecipeId, "Orzo", 200, "g")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) cajunOrzoRecipeId, "Cream", 150, "ml")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) cajunOrzoRecipeId, "Garlic", 2, "units")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) cajunOrzoRecipeId, "Cajun Seasoning", 1, "tbsp")
        );
        Recipe garlicChilliBeedNoodles = new Recipe(
                "Garlic Chilli Butter Beed Noodles",
                "Buttery noodles tossed with the beed, garlic and chilli for a rich , spicy bowl.",
                "Got Some Heat",
                "1. Cook the noodles and set it aside. " +
                        "2. Brown the beef in a hot pan." +
                        "3. Add the butter , garlic and the chilli." +
                        "4. Toss the noodles and coat them well"
        );
        long beefNoodleRecipeId =
                appDatabase.recipeDao().addRecipe(garlicChilliBeedNoodles);
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) beefNoodleRecipeId, "Beef Strips", 250, "g")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) beefNoodleRecipeId, "Noodles", 200, "g")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) beefNoodleRecipeId, "Butter", 2, "tbsp")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) beefNoodleRecipeId, "Garlic", 2, "units")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) beefNoodleRecipeId, "Chilli", 1, "units")
        );
        Recipe smokyPaprikaFlatbreads = new Recipe(
                "Smoky Paprika Chicken Flatbreads",
                "Smoky paprika chicken served in a warm flatbread with a fresh, savoury filling.",
                "Quick Cravings",
                "1. Season the chicken with paprika." +
                        "2. Cook it until its golden and fully cooked." +
                        "3. warm the flatbread." +
                        "4. Fill it with chicken , tomato and onions"
        );
        long paprikaFlatbreadRecipeId =
                appDatabase.recipeDao().addRecipe(smokyPaprikaFlatbreads);
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) paprikaFlatbreadRecipeId, "Chicken Breast", 300, "g")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) paprikaFlatbreadRecipeId, "Flatbread", 2, "units")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) paprikaFlatbreadRecipeId, "Paprika", 1, "tbsp")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) paprikaFlatbreadRecipeId,"Tomato", 1, "units")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) paprikaFlatbreadRecipeId,"Onion", 1, "units")
        );

        Recipe periPeriChickenRice = new Recipe(
                "Peri-Peri Chicken Rice Skillet",
                "A spicy one pa chiken and rice dish with bold peri peri flavour.",
                "Got Some Heat",
                "1. Cook the chicken until it is browned." +
                        "2. Add onion and peri peri sauce." +
                        "3. Stir in the rice and then cook it until heated through." +
                        "4. Serve while hot."
        );
        long periPeriRecipeId =
                appDatabase.recipeDao().addRecipe(periPeriChickenRice);
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) periPeriRecipeId, "Chicken Breast", 300, "g")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) periPeriRecipeId, "Rice", 1, "cups")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) periPeriRecipeId, "Peri-Peri Sauce", 2, "tbsp")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) periPeriRecipeId, "Onion", 1, "units")
        );

        Recipe spicedBeefPotatoHash = new Recipe(
                "Spiced Beef and Potato Hash",
                "Crispy potatoes tossed with a savoury spiced beef for a one pan meal.",
                "Comfort Fix",
                "1. Dice and cook the potatoes until its golden" +
                        "2. Brown the beef with onion and spices." +
                        "3. Add the potatoes back into the pan." +
                        "4. Toss everything together and then serve it hot"
        );
        long beefPotatoHashRecipeId =
                appDatabase.recipeDao().addRecipe(spicedBeefPotatoHash);
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) beefPotatoHashRecipeId,"Beef Mince" , 300, "g")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) beefPotatoHashRecipeId,"Potato" , 400, "g")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) beefPotatoHashRecipeId,"Onion" , 1, "units")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) beefPotatoHashRecipeId,"Paprika" , 1, "tsp")
        );

        Recipe roastedRedPepperPasta = new Recipe(
                "Roasted Red Pepper Pasta",
                "A creamy slightly smoky pasta made with a roasted red pepper and garlic",
                "Comfort Fix",
                "1. Cook the pasta until soft" +
                        "2. Blend the roatsed peppers with the garlic and the cream." +
                        "3. Warm the sauce in the pan"+
                        "4. Toss the pasta in the sauce and serve while hot"
        );
        long redPepperPastaRecipeId =
                appDatabase.recipeDao().addRecipe(roastedRedPepperPasta);
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) redPepperPastaRecipeId, "Pasta", 250, "g")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) redPepperPastaRecipeId, "Red Pepper", 2, "units")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) redPepperPastaRecipeId, "Cream", 150, "ml")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) redPepperPastaRecipeId, "Garlic", 2, "units")
        );

        Recipe stickySoyChilliChicken = new Recipe(
                "STicky SOy Chilli Chicken",
                "Mix soy sauce , honey and chillis",
                "Got Some heat",
                "1. Cook the chicken until brown" +
                        "2. mix soy sauce , honey and some chillis." +
                        "3. Warm the sauce over the chicken"+
                        "4. Simmer until its sticky and serve"
        );
        long stickySoyChickenRecipeId =
                appDatabase.recipeDao().addRecipe(stickySoyChilliChicken);
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) stickySoyChickenRecipeId, "Chicken Breast", 300, "g")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) stickySoyChickenRecipeId, "Soy Sauce", 2, "tbsp")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) stickySoyChickenRecipeId, "Honey", 1, "tbsp")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) stickySoyChickenRecipeId, "Chilli", 2, "units")
        );

        Recipe creamyMushroomPepperPasta = new Recipe(
                "Creamy Mushroom Pepper Pasta",
                "A rich mushroom pasta that has black pepper and a smooth creamy sauce",
                "Comfort Fix",
                "1. Cook the pasta until the pasta is soft"+
                        "2. Fry the mushrooms until they have browned" +
                        "3. Add the cream and the black pepper" +
                        "4. Toss the pasta in the sauce and coat it well"
        );
        long  mushroomPastaRecipeId =
                appDatabase.recipeDao().addRecipe(creamyMushroomPepperPasta);
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) mushroomPastaRecipeId, "Pasta", 250, "g")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) mushroomPastaRecipeId, "Mushrooms", 200, "g")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) mushroomPastaRecipeId, "Cream", 150, "ml")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) mushroomPastaRecipeId, "Black Pepper", 1, "tsp")
        );

        Recipe lemonHerbChickenCouscous = new Recipe(
                "Lemon Herb Chicken COuscous",
                "Bright lemon and herb chicken served with fluffy couscous.",
                "Fresh Picks" ,
                "1. Season the chicken with lemon and herbs" +
                        "2. COok the chicken until it reaches a golden colour" +
                        "3. Prepare the couscous"+
                        "4 Serve the chicken over the couscous"
        );
        long lemonChickenRecipeId =
                appDatabase.recipeDao().addRecipe(lemonHerbChickenCouscous);
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) lemonChickenRecipeId, "Chicken Breast" , 300 ,"g")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) lemonChickenRecipeId, "Couscous" , 200 ,"g")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) lemonChickenRecipeId, "Lemon" , 1 ,"units")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) lemonChickenRecipeId, "Mixed Herbs" , 1 ,"tbsp")
        );

        Recipe garlicParmesanChickenPotatoes = new Recipe(
                "Garlic Parmesan Chicken Potatoes",
                "Golden chicken and potatoes coated in garlic , herbs and parmesan",
                "Comfort Fix",
                "1. Cut the potatoes into small pieces." +
                        "2. Season and cook the chicken until it is golden" +
                        "3. Cook the potatoes with garlic and the herbs" +
                        "4. Add the parmesan and serve it all together."
        );
        long garlicParmesanRecipeId =
                appDatabase.recipeDao().addRecipe(garlicParmesanChickenPotatoes);
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) garlicParmesanRecipeId, "Chicken Breast", 300, "g")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) garlicParmesanRecipeId, "Potato", 400, "g")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) garlicParmesanRecipeId, "Garlic", 2, "units")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) garlicParmesanRecipeId, "Parmesan", 50, "g")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) garlicParmesanRecipeId, "Mixed Herbs", 1, "tsp")
        );

        Recipe koreanStyleBeefRiceBowls = new Recipe(
                "Korean Style Beef Rice Bowls",
                "Sweet and savoury beef served over rice with garlic and soy",
                "Got Some Heat",
                "1. Cook the rice until soft" +
                        "2. Brown the beef with garlic"+
                        "3. Add soy sauce and cook it until its coated"+
                        "4. Serve the beef with rice"
        );
        long  koreanBeefRecipeId =
                appDatabase.recipeDao().addRecipe(koreanStyleBeefRiceBowls);
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) koreanBeefRecipeId, "Beef Mince", 300, "g")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) koreanBeefRecipeId, "Rice", 1, "cups")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) koreanBeefRecipeId, "Soy Sauce", 2, "tbsp")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) koreanBeefRecipeId, "Garlic", 2, "units")
        );

        Recipe loadedChickenRiceBowls = new Recipe(
                "Loaded Chicken Rice Bowls",
                "A simple loaded bowl with chicken , rice , tomato and melted chees",
                "Quick Cravings",
                "1. Cook the rice until it is soft " +
                        "2. Cook the chicken until its golden"+
                        "3. Add chopped tomato"+
                        "4. Serve over rice and finish with cheese."
        );
        long loadedChickenRecipeId =
                appDatabase.recipeDao().addRecipe(loadedChickenRiceBowls);
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) loadedChickenRecipeId, "Chicken Breast", 300, "g")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) loadedChickenRecipeId, "Rice", 1, "cups")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) loadedChickenRecipeId, "Tomato", 1, "units")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) loadedChickenRecipeId, "Cheese", 75, "g")
        );

        Recipe mediterraneanChickenPasta = new Recipe(
                "Mediterranean Chicken Pasta",
                "Chicken pasta with tomato and garlic for a fresh, savoury meal",
                "Fresh Picks",
                "1.Cook the pasta until soft " +
                        "2.Cook the chicken until its golden"+
                        "3. Add garlic and tomato"+
                        "4. Toss everything through the pasta and serve"
        );
        long mediterraneanPastaRecipeId =
                appDatabase.recipeDao().addRecipe(mediterraneanChickenPasta);
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) mediterraneanPastaRecipeId, "Chicken Breast", 300, "g")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) mediterraneanPastaRecipeId, "Pasta", 250, "g")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) mediterraneanPastaRecipeId, "Tomato", 2, "units")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) mediterraneanPastaRecipeId, "Garlic", 2, "units")
        );

        Recipe smokyBeefWraps = new Recipe(
                " Smoky Beef Wraps ",
                "Savoury beef, tomato and onions wrapped in warm flatbread",
                "Quick Cravings",
                        "1. Brown the beef in a pan"+
                        "2. Add tomatoes and onion"+
                        "3. Warm the flatbread"+
                        "4.Fill the flatbread with the beef mixture and serve"
        );
        long smokyBeefRecipeId  =
                appDatabase.recipeDao().addRecipe(smokyBeefWraps);
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) smokyBeefRecipeId, "Beef Mince", 300, "g")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) smokyBeefRecipeId, "Flatbread", 2, "units")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) smokyBeefRecipeId, "Tomato", 1, "units")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) smokyBeefRecipeId, "Onion", 1, "units")
        );

        Recipe creamySpinachChickenRice = new Recipe(
                "Creamy Spinach Chicken Rice",
                "Creamy chicken and spinach served over some hot rice",
                "Comfort Fix",
                "1. Cook the rice until soft" +
                        "2. Cook the chicken until golden"+
                        "3. Add spinach and cream"+
                        "4. Serve the creamy chicken over rice"
        );
        long  creamySpinachRecipeId =
                appDatabase.recipeDao().addRecipe(creamySpinachChickenRice);
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int)creamySpinachRecipeId , "Chicken Breast", 300, "g")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) creamySpinachRecipeId, "Rice", 1, "cups")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) creamySpinachRecipeId, "Spinach", 100, "g")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) creamySpinachRecipeId, "Cream", 150, "ml")
        );

        Recipe sweetChilliGarlicNoodles = new Recipe(
                "Sweet Chilli Noodles",
                "Quick noodles coated in a buttery garlic and sweet chilli sauce",
                "Quick Cravings",
                "1. Cook the noodles until they are soft " +
                        "2. Melt butter with garlic"+
                        "3. Stir in sweet chilli sauce"+
                        "4. Toss the noodles through the sauce and serve"
        );
        long sweetChilliNoodleRecipeId =
                appDatabase.recipeDao().addRecipe(sweetChilliGarlicNoodles);
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) sweetChilliNoodleRecipeId, "Noodles", 200, "g")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) sweetChilliNoodleRecipeId, "Garlic", 2, "units")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) sweetChilliNoodleRecipeId, "Butter", 2, "tbsp")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) sweetChilliNoodleRecipeId, "Sweet Chilli sauce", 2, "tbsp")
        );

        Recipe spicedChickpeaPotatoBowl = new Recipe(
                "Spiced Chickpea Potato Bowl",
                "A hearty potato and chickpea bowl with tomato and onion",
                "Rescue Recipes",
                "1. Cook the potatoes until golden and tender " +
                        "2. Add onion and cook until it is softened"+
                        "3. Add chickpeas and tomato"+
                        "4. Cookk together until hot and then serve"
        );
        long  chickpeaPotatoRecipeId =
                appDatabase.recipeDao().addRecipe(spicedChickpeaPotatoBowl);
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) chickpeaPotatoRecipeId, "Potato", 400, "g")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) chickpeaPotatoRecipeId, "Chickpeas", 200, "g")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) chickpeaPotatoRecipeId, "Tomato", 1, "units")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) chickpeaPotatoRecipeId, "Onion", 1, "units")
        );

        Recipe honeyPaprikaChickenRice = new Recipe(
                "Honey Paprika Chicken RIce",
                "Sweet smoky chicken served over simple rice",
                "Quick Cravings",
                "1. Cook the rice until its tender" +
                        "2. Season the chicken with paprika"+
                        "3. Cook ubtil golden and add the honey"+
                        "4. Serve the chicken over rice"
        );
        long honeyPaprikaRecipeId =
                appDatabase.recipeDao().addRecipe(honeyPaprikaChickenRice);
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) honeyPaprikaRecipeId, "Chicken Breast", 300, "g")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) honeyPaprikaRecipeId, "Rice", 1, "cups")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) honeyPaprikaRecipeId, "Honey", 1, "tbsp")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) honeyPaprikaRecipeId, "Paprika", 1, "tsp")
        );

        Recipe  creamyBeefMushroomPasta = new Recipe(
                "Creamy Beef Mushroom Pasta",
                "Rich beef and mushroom pasta finished with a creamy sauce",
                "Comfort Fix",
                "1. Cook the pasta until tender " +
                        "2. Brown the beef in a pan"+
                        "3.Add mushrooms and cook until softened"+
                        "4. Add cream, stir through the pasta and serve"
        );
        long creamyBeefPastaRecipeId =
                appDatabase.recipeDao().addRecipe(creamyBeefMushroomPasta);
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) creamyBeefPastaRecipeId, "Beef Mince", 300, "g")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) creamyBeefPastaRecipeId, "Pasta", 250, "g")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) creamyBeefPastaRecipeId, "Mushrooms", 200, "g")
        );
        appDatabase.recipeIngredientDao().addRecipeIngredient(
                new RecipeIngredient((int) creamyBeefPastaRecipeId, "Cream", 150, "ml")
        );
    }

}
