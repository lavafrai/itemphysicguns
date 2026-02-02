package ru.lavafrai.svogame.itemphysicguns

import ru.lavafrai.svogame.itemphysicguns.embededProviders.SuperbWarfareProvider
import ru.lavafrai.svogame.itemphysicguns.embededProviders.TaczProvider

object ProviderHelper {
    fun registerTacz(providers: MutableList<ItemTransformationProvider>) {
        providers.add(TaczProvider())
    }

    fun registerSuperbWarfare(providers: MutableList<ItemTransformationProvider>) {
        providers.add(SuperbWarfareProvider())
    }
}
