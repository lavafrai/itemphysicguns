package ru.lavafrai.svogame.itemphysicguns.embededProviders

import com.atsuishio.superbwarfare.item.gun.GunGeoItem
import net.minecraft.world.entity.item.ItemEntity
import ru.lavafrai.svogame.itemphysicguns.ItemTransform
import ru.lavafrai.svogame.itemphysicguns.ItemTransformationProvider

class SuperbWarfareProvider: ItemTransformationProvider {
    override fun getTransform(
        entity: ItemEntity,
        partialTicks: Float
    ): ItemTransform {
        return ItemTransform()
            .translate(0.0f, 0.0f, -0.05f)
            .rotateDegrees(0.0f, 90f, 0.0f)
    }

    override fun canApplyTransform(entity: ItemEntity): Boolean {
        val itemType = entity.item.item
        return itemType is GunGeoItem
    }
}
