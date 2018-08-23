/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package fr.discowzombie.kpigot.module.kfile

import org.bukkit.Bukkit
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class KFile(var name: String, private val content: (conf: FileConfiguration) -> Unit = {}, val dataFolder: File = Bukkit.getServer().pluginManager.getPlugin("Kpigot").dataFolder) {

    private var file = File(this.dataFolder, if (this.name.endsWith(".yml").not()) this.name.plus(".yml") else this.name)
    var yamlFile: YamlConfiguration = load(if (this.file.exists().not()) { dataFolder.mkdirs(); this.file.createNewFile(); this.file } else this.file)

    init {
        content.invoke(this.yamlFile)

        save()
    }

    private fun load(file: File): YamlConfiguration = YamlConfiguration.loadConfiguration(file)

    fun save() = this.yamlFile.save(this.file)

}