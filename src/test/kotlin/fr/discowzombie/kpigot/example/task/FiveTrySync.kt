/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package fr.discowzombie.kpigot.example.task

import fr.discowzombie.kpigot.module.ktask.KTask

/*
    You can also precise parameters
    First parameters is the delay, second is the amount of repetition
 */
var anotherVar = KTask({
/*
    For example, this task is synced and will be repeated five times, every 10 seconds
    After that, the task will automatically be stopped
*/
}, 10, 5).sync()