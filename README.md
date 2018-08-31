Kpigot is a Kotlin wrapper for your Minecraft plugins who use Kotlin. With these, you can easily simply the development 
process by using all Kotlin possibilities.

**What the wrapper can do :**
* Manage inventory easily
* Create custom items in one line
* Managing multiple yaml files
* Working with sync and async tasks

The wrapper is totally written in Kotlin and design to work with Kotlin 1.2.60 at least.

Kpigot has been tested on 1.12.2 and 1.13.1. He probably works with all versions >= 1.8.

Plans for futures : [PROGRESS.md](PROGRESS.md)

## Usage

### Item

If you need to build complex Itemstack, you can do this with a pretty easy mechanic :
```kotlin
val myAwesomeDiamonnd = KItem(Material.DIAMOND,
        name = "§bMy awesome diamond",
        amount = 15,
        lore = listOf("§6Rare item"),
        enchantments = hashMapOf(Pair<Enchantment, Int>(Enchantment.KNOCKBACK, 5)),
        itemFlags = listOf(ItemFlag.HIDE_ENCHANTS)
).build()
```

The class is pretty simple to understand, [you can view it here](src/main/kotlin/fr/discowzombie/kpigot/tool/KItem.kt) or [see
more examples](src/test/kotlin/fr/discowzombie/kpigot/example/item/Item.kt).

### File

Multiple yaml configuration can be hard to manage by using Java. Hopefully, with Kotlin, this become easy :
````kotlin
/*
    First parameter is the config name. Without any innovation I choose 'config' but fell free to use your own!
    You can use your own data folder by adding 'datafolder =' else, plugins/Kpigot is used
    If the file doesn't exist, it will be created. Else, simply loaded
*/
val variable = KFile("config", {
    // You can make checks and interact with config

    it.set("key", "value")

    // This is condition is a little obvious, I know...
    if (1 >= 1)
        it.set("obviousValue", 1.toDouble())
}).save()
// Don't forget to save changes

// If you want to modify it again, simply use the same code...

// This is the simplest way to read a value out of a lambda
var value = KFile("config").yamlFile.getString("key")
````

### Inventory

Inventory is an important part of some plugins. Their management was hard, we need class who extends Listener, code is usually heavy. With Kpigot, you have a coherent and lightweight code, thanks to Kotlin technologies :
```kotlin
/*
The first parameter of inventory is displayed-name
After, provided the inventory side (9-based) - default value is 9
*/
val uselessVariable = KInventory("My awesome inventory", slot = 18) {
    // After, use this lambdas to add content to your inventory

    it.addItem(
            KItem(Material.DIAMOND, name = "My awesome diamond").build()
    )

    // You can add as many as you want conditions and items...
}.event {
    // Define your conditions for interact - You do not need to register your class as Listener, the plugin had done it for you!

    // Note: Cast to Player will never fail because the condition to open inventory require to have a valid player
    (it.whoClicked as Player).sendMessage("Yeah, you just open my awesome inventory !")
}.persistantSave("myAwesomeInventory") // Save inventory

// Now, I can use this code to retrieve my inventory
val kInv = KInventory.getInventory("myAwesomeInventory")
```

### Task

Tasks (sync and async) were useful for some action : communication with other external applications or website, working with databases, timer and much more ! Creating a task with Kpigot is probably the lightest way allowed by Spigot.
```kotlin
/*
    Sync task - Hope you love lambdas
    If you do not specify parameters, this task will be executed at once
*/
val variable = KTask({
    // Do what you want !

}).async()
```

**Need more examples ?** Take a look [at test folder](src/test/kotlin/fr/discowzombie/kpigot/example).
