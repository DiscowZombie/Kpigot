/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package fr.discowzombie.kpigot.module.kinventory

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent

class KInventoryListener : Listener {

    @EventHandler
    private fun currentInventoryInteract(invEvent: InventoryClickEvent) {
        // Inventory seems invalid or no event was registered
        if (invEvent.inventory == null || invEvent.whoClicked !is Player)
            return

        val currentName = invEvent.inventory.name
        val currentSize = invEvent.inventory.size

        var inventory = InventorySaver.getInstance().persistentInventory.values.distinct()
        if (InventorySaver.getInstance().oneTimeInventory != null)
            inventory = inventory.plus(InventorySaver.getInstance().oneTimeInventory!!)

        // Exclude if inventory doesn't match
        val matchedInventoryes: List<KInventory> = inventory
                .filter {
                    it.interact != null && it.inventory.name == currentName && it.inventory.size == currentSize
                }

        for (kinventory in matchedInventoryes) {
            kinventory.interact!!.invoke(invEvent)
            invEvent.isCancelled = kinventory.cancelEvent
        }
    }

    @EventHandler
    private fun inventoryClose(invClose: InventoryCloseEvent) {
        if (InventorySaver.getInstance().oneTimeInventory != null)
            InventorySaver.getInstance().oneTimeInventory = null
    }

}
