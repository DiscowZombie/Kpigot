/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package fr.discowzombie.kpigot.module.ktask

import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitTask

class KTask(private val params: () -> Unit, private val delay: Int = 0, private val repeat: Int = 1, private val mainClass: Plugin? = null) {

    private var task: BukkitTask? = null
    private var main: Plugin? = null

    init {
        if (mainClass == null)
            this.main = Bukkit.getServer().pluginManager.getPlugin("Kpigot")
        else
            this.main = mainClass
    }

    fun async(): BukkitTask {
        var repeatAmount = 0

        this.task = Bukkit.getServer().scheduler.runTaskTimerAsynchronously(main, {
            params.invoke()
            repeatAmount++

            if (repeatAmount >= repeat)
                this.task!!.cancel()
        }, delay.toLong() * 20, delay.toLong() * 20)

        return this.task!!
    }

    fun sync(): BukkitTask {
        var repeatAmount = 0

        this.task = Bukkit.getServer().scheduler.runTaskTimer(main, {
            params.invoke()
            repeatAmount++

            if (repeatAmount >= repeat)
                this.task!!.cancel()
        }, delay.toLong() * 20, delay.toLong() * 20)

        return this.task!!
    }

}