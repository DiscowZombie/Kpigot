/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package fr.discowzombie.kpigot.example.inventory

import fr.discowzombie.kpigot.module.kinventory.KInventory
import fr.discowzombie.kpigot.tool.KItem
import org.bukkit.Bukkit
import org.bukkit.Material

val player = Bukkit.getPlayer("Notch")

val OtherUselessVariable = KInventory("Title") {
    it.addItem(
            KItem(Material.GOLD_INGOT, name = "§6Gold ingot").build()
    )
} .event(false) {
    // Set false as event parameters will not cancel the InventoryInteractEvent, so allow the user to take the item
}.open(listOf(player))
// I only open the inventory (without saving) so I can't retrieve it by using KInventory.getInventory()


