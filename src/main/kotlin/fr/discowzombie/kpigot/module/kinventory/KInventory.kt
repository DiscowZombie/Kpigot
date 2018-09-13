/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package fr.discowzombie.kpigot.module.kinventory

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryInteractEvent
import org.bukkit.inventory.Inventory

class KInventory(val name: String, private val slot: Int = 9, private val content: (inventory: Inventory) -> Unit) :
    Listener {

    val inventory: Inventory = Bukkit.createInventory(null, next(slot, 9), name)
    var interact: ((invEvent: InventoryInteractEvent) -> Unit)? = null
    var cancelEvent: Boolean = true

    init {
        content.invoke(this.inventory)
    }

    fun open(players: List<Player>): KInventory {
        InventorySaver.oneTimeInventory = this

        players.forEach {
            it.openInventory(this.inventory)
        }

        return this
    }

    fun event(cancelEvent: Boolean = true, interact: (invEvent: InventoryInteractEvent) -> Unit): KInventory {
        this.cancelEvent = cancelEvent
        this.interact = interact

        return this
    }

    fun persistantSave(key: String) {
        val saver = InventorySaver

        if (saver.persistentInventory[key] != null)
            throw ArrayIndexOutOfBoundsException("One inventory with that name already exist")

        saver.persistentInventory[key] = this
    }

    companion object {
        @JvmStatic
        fun getInventory(key: String): KInventory? {
            return InventorySaver.persistentInventory[key]
        }
    }

    private fun next(number: Int, mod: Int): Int {
        var num: Int = number

        while (num % mod != 0)
            num++

        return num
    }
}

object InventorySaver {
    var persistentInventory: HashMap<String, KInventory> = hashMapOf()
    var oneTimeInventory: KInventory? = null
}