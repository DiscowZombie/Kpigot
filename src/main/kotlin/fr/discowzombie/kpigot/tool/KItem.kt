/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package fr.discowzombie.kpigot.tool

import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

class KItem(
        var material: Material,
        var amount: Int = 1,
        var data: Short = 0,
        var name: String? = null,
        var durability: Short = 0,
        var enchantments: HashMap<Enchantment, Int> = hashMapOf(),
        var lore: List<String> = listOf(),
        var isUnbrekable: Boolean = false,
        var itemFlags: List<ItemFlag> = listOf()
) {
    fun build(): ItemStack {
        val itemStack = ItemStack(material, amount, data)

        // Meta
        val itemMeta = itemStack.itemMeta
        itemMeta.displayName = this.name
        itemMeta.lore = this.lore
        itemMeta.isUnbreakable = this.isUnbrekable
        for (flag in this.itemFlags) {
            itemMeta.addItemFlags(flag)
        }
        itemStack.itemMeta = itemMeta

        // ItemStack
        itemStack.durability = this.durability
        for (enchant in this.enchantments) {
            itemStack.addEnchantment(enchant.key, enchant.value)
        }

        return itemStack
    }

}