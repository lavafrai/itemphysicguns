package ru.lavafrai.svogame.itemphysicguns.embededProviders

import com.tacz.guns.item.ModernKineticGunItem
import net.minecraft.world.entity.item.ItemEntity
import ru.lavafrai.svogame.itemphysicguns.ItemTransform
import ru.lavafrai.svogame.itemphysicguns.ItemTransformationProvider

class TaczProvider: ItemTransformationProvider {
    override fun getTransform(
        entity: ItemEntity,
        partialTicks: Float
    ): ItemTransform {
        return ItemTransform()
            .translate(0.0f, 0.1f, -0.05f)
            .rotateDegrees(0.0f, 90f, 0.0f)
    }

    override fun canApplyTransform(entity: ItemEntity): Boolean = entity.item.item is ModernKineticGunItem
}