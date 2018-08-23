/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package fr.discowzombie.kpigot.example.inventory

import fr.discowzombie.kpigot.module.kinventory.KInventory
import fr.discowzombie.kpigot.tool.KItem
import org.bukkit.Material
import org.bukkit.entity.Player

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
