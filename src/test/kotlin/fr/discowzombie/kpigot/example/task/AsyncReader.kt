/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package fr.discowzombie.kpigot.example.task

import fr.discowzombie.kpigot.module.ktask.KTask

/*
    Sync task - Hope you love lambdas
    If you do not specify parameters, this task will be executed at once
*/
val variable = KTask({
    // Do what you want !

}).async()
