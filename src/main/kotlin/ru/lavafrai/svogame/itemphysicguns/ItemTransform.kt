package ru.lavafrai.svogame.itemphysicguns

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import org.joml.Quaternionf
import org.joml.Vector3f


data class ItemTransform(
    val translation: Vector3f = Vector3f(0f, 0f, 0f),
    val rotation: Quaternionf = Quaternionf(),
    val scale: Vector3f = Vector3f(1f, 1f, 1f)
) {
    fun applyTo(poseStack: PoseStack) {
        poseStack.translate(translation.x, translation.y, translation.z)
        poseStack.mulPose(rotation)

        if (scale.x != 1f || scale.y != 1f || scale.z != 1f) {
            poseStack.scale(scale.x, scale.y, scale.z)
        }
    }

    fun translate(x: Float = 0f, y: Float = 0f, z: Float = 0f) = apply {
        translation.add(x, y, z)
    }

    fun rotateDegrees(x: Float = 0f, y: Float = 0f, z: Float = 0f) = apply {
        if (x != 0f) rotation.mul(Axis.XP.rotationDegrees(x))
        if (y != 0f) rotation.mul(Axis.YP.rotationDegrees(y))
        if (z != 0f) rotation.mul(Axis.ZP.rotationDegrees(z))
    }

    fun rotate(quaternion: Quaternionf) = apply {
        rotation.mul(quaternion)
    }

    fun scale(x: Float = 1f, y: Float = 1f, z: Float = 1f) = apply {
        scale.mul(x, y, z)
    }

    fun scale(uniform: Float) = scale(uniform, uniform, uniform)

    operator fun plusAssign(other: ItemTransform) {
        translation.add(other.translation)
        rotation.mul(other.rotation)
        scale.mul(other.scale)
    }
}

fun PoseStack.applyTransform(transform: ItemTransform) {
    transform.applyTo(this)
}