package com.fingerprintjs.android.fingerprint.signal_providers.installed_apps


import com.fingerprintjs.android.fingerprint.info_providers.PackageInfo
import com.fingerprintjs.android.fingerprint.signal_providers.RawData
import com.fingerprintjs.android.fingerprint.signal_providers.Signal
import com.fingerprintjs.android.fingerprint.signal_providers.StabilityLevel


data class InstalledAppsRawData(
    val applicationsNamesList: List<PackageInfo>,
    val systemApplicationsList: List<PackageInfo>
) : RawData() {

    override fun signals() = listOf(
        applicationsList(),
        systemApplicationsList()
    )

    fun applicationsList() = object : Signal<List<PackageInfo>>(
        1,
        null,
        StabilityLevel.UNIQUE,
        INSTALLED_APPS_KEY,
        INSTALLED_APPS_DISPLAY_NAME,
        applicationsNamesList
    ) {
        override fun toString(): String {
            val sb = StringBuilder()

            applicationsNamesList
                .sortedBy { it.packageName }
                .forEach { sb.append(it.packageName) }

            return sb.toString()
        }
    }

    fun systemApplicationsList() = object : Signal<List<PackageInfo>>(
        2,
        null,
        StabilityLevel.OPTIMAL,
        SYSTEM_APPS_KEY,
        SYSTEM_APPS_DISPLAY_NAME,
        systemApplicationsList
    ) {
        override fun toString(): String {
            val sb = StringBuilder()

            systemApplicationsList
                .sortedBy { it.packageName }
                .forEach { sb.append(it.packageName) }

            return sb.toString()
        }
    }
}