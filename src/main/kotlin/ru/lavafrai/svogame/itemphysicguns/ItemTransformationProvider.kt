package ru.lavafrai.svogame.itemphysicguns

import net.minecraft.world.entity.item.ItemEntity


interface ItemTransformationProvider {
    /**
     * Вычисляет трансформацию для указанной сущности предмета.
     * @param entity Сущность предмета
     * @param partialTicks Доля тика для плавной интерполяции
     * @return Объект трансформации (смещение, вращение, масштаб)
     */
    fun getTransform(entity: ItemEntity, partialTicks: Float): ItemTransform

    /**
     * Определяет, может ли этот провайдер применять трансформацию к представленному предмету.
     * @param entity Сущность предмета
     * @return true, если трансформация может быть применена, иначе false
     */
    fun canApplyTransform(entity: ItemEntity): Boolean
}