package ru.lavafrai.svogame.itemphysicguns.embededProviders

import net.minecraft.client.Minecraft
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.item.ItemEntity
import net.minecraftforge.registries.ForgeRegistries
import ru.lavafrai.svogame.itemphysicguns.ItemTransform
import ru.lavafrai.svogame.itemphysicguns.ItemTransformationProvider

class SmartScorchedGuns2Provider : ItemTransformationProvider {

    private val forcedTransforms: Map<String, ItemTransform> = mapOf(
        "scguns:gun_shelf" to ItemTransform()
            .rotateDegrees(0f, 0f, 0f)
            .translate(0.0f, 0.0f, -0.10f),
    )

    private val excludedFromAutoTransform: Set<String> = setOf(
        "scguns:gun_shelf"
    )

    override fun getTransform(entity: ItemEntity, partialTicks: Float): ItemTransform {
        val item = entity.item.item
        val key = ForgeRegistries.ITEMS.getKey(item)?.toString() ?: return ItemTransform()

        forcedTransforms[key]?.let { return it }

        if (!canApplyTransform(entity)) {
            return ItemTransform()
        }

        return ItemTransform()
            .rotateDegrees(0f, 90f, 0f)
            .translate(0.0f, 0.0f, -0.05f)
    }

    override fun canApplyTransform(entity: ItemEntity): Boolean {
        val item = entity.item.item
        val key = ForgeRegistries.ITEMS.getKey(item)?.toString() ?: return false

        forcedTransforms[key]?.let { return true }
        if (excludedFromAutoTransform.contains(key)) return false

        if (!key.startsWith("scguns:")) return false

        val mc = Minecraft.getInstance()
        val level = mc.level ?: return false

        val model = mc.itemRenderer.getModel(entity.item, level, null, entity.id)
        return model.isGui3d || model.isCustomRenderer
    }
}