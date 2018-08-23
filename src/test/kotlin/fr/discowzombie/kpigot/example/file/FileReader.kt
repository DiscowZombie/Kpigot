/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package fr.discowzombie.kpigot.example.file

import fr.discowzombie.kpigot.module.kfile.KFile

/*
    First parameter is the config name. Without any innovation I choose 'config' but fell free to use your own!
    You can use your own data folder by adding 'datafolder =' else, default is used (plugins/Kpigot)
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