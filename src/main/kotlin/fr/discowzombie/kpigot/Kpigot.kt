/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package fr.discowzombie.kpigot

import fr.discowzombie.kpigot.module.kinventory.KInventoryListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class Kpigot : JavaPlugin() {

    private val KOTLIN = "1.2.60"

    override fun onEnable() {
        // Require for KInventory module
        Bukkit.getPluginManager().registerEvents(KInventoryListener(), this)

        Bukkit.getServer().consoleSender.sendMessage("[${this.description.name}] This version includes Kotlin $KOTLIN.")

        super.onEnable()
    }

}