/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package fr.discowzombie.kpigot.example.item

import fr.discowzombie.kpigot.tool.KItem
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag


val myAwesomeDiamonnd = KItem(Material.DIAMOND,
        name = "§bMy awesome diamond",
        amount = 15,
        lore = listOf("§6Rare item"),
        enchantments = hashMapOf(Pair<Enchantment, Int>(Enchantment.KNOCKBACK, 5)),
        itemFlags = listOf(ItemFlag.HIDE_ENCHANTS)
).build()

// Another cool item
val otherItem = KItem(Material.STICK,
        name = "Fun stick",
        isUnbrekable = true,
        enchantments = hashMapOf(Pair<Enchantment, Int>(Enchantment.FIRE_ASPECT, 10))
).build()