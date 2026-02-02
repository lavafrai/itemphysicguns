package ru.lavafrai.svogame.itemphysicguns.embededProviders

import com.tacz.guns.item.AttachmentItem
import com.tacz.guns.item.ModernKineticGunItem
import net.minecraft.world.entity.item.ItemEntity
import ru.lavafrai.svogame.itemphysicguns.ItemTransform
import ru.lavafrai.svogame.itemphysicguns.ItemTransformationProvider

class TaczProvider: ItemTransformationProvider {
    override fun getTransform(
        entity: ItemEntity,
        partialTicks: Float
    ): ItemTransform {
        return when (entity.item.item) {
            is ModernKineticGunItem -> {
                ItemTransform()
                    .translate(0.0f, 0.0f, -0.03f)
                    .rotateDegrees(0.0f, 90f, 0.0f)
            }

            is AttachmentItem -> {
                val id = entity.item.tag?.getString("AttachmentId") ?: return ItemTransform()
                if (id.contains("oem") || id.contains("extended_mag")) return ItemTransform()

                ItemTransform()
                    .translate(0.0f, 0.0f, -0.03f)
                    .rotateDegrees(0.0f, -90f, 0.0f)
                    .scale(1.2f)
            }

            else -> error("Unreachable code")
        }
    }

    override fun canApplyTransform(entity: ItemEntity): Boolean {
        val item = entity.item.item
        return item is ModernKineticGunItem || item is AttachmentItem
    }
}