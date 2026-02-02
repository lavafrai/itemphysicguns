@file:Suppress("DEPRECATION", "removal")

package ru.lavafrai.svogame.itemphysicguns

import com.mojang.logging.LogUtils
import net.minecraftforge.fml.ModList
import net.minecraft.world.entity.item.ItemEntity
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod(ItemPhysicGuns.MODID)
class ItemPhysicGuns {
    val providers = mutableListOf<ItemTransformationProvider>()

    companion object {
        const val MODID = "itemphysicguns"
        private val LOGGER = LogUtils.getLogger()
        private lateinit var instance: ItemPhysicGuns

        @JvmStatic
        fun getInstance(): ItemPhysicGuns {
            return instance
        }
    }


    init {
        if (!ModList.get().isLoaded("itemphysic") && !ModList.get().isLoaded("itemphysiclite")) {
            throw RuntimeException("Neither 'itemphysic' nor 'itemphysiclite' is installed! One of them is required for ItemPhysicGuns to work.")
        }
        initializeMod()
    }

    fun initializeMod() {
        instance = this
        MOD_BUS.addListener(this::commonSetup)

        if (ModList.get().isLoaded("tacz")) ProviderHelper.registerTacz(providers)
        if (ModList.get().isLoaded("superbwarfare")) ProviderHelper.registerSuperbWarfare(providers)
    }

    private fun commonSetup(@Suppress("UNUSED_PARAMETER") event: FMLCommonSetupEvent) {
        LOGGER.info("ItemPhysicGuns mod started.")
    }

    fun getTransformationForItemOrNull(
        entity: ItemEntity,
        partialTicks: Float
    ): ItemTransform? {
        for (provider in providers) {
            if (provider.canApplyTransform(entity)) {
                return provider.getTransform(entity, partialTicks)
            }
        }
        return null
    }
}
