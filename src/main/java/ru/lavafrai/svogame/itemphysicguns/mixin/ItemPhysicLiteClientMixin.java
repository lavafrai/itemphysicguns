package ru.lavafrai.svogame.itemphysicguns.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.lavafrai.svogame.itemphysicguns.ItemPhysicGuns;
import ru.lavafrai.svogame.itemphysicguns.ItemTransform;
import team.creative.itemphysiclite.ItemPhysicLite;

@Mixin(ItemPhysicLite.class)
public class ItemPhysicLiteClientMixin {
    @Inject(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lcom/mojang/blaze3d/vertex/PoseStack;mulPose(Lorg/joml/Quaternionf;)V",
            ordinal = 1,
            shift = At.Shift.AFTER
        ),
        remap = false
    )
    private static void injectCustomRotation(
            ItemEntity entity,
            float entityYaw,
            float partialTicks,
            PoseStack pose,
            MultiBufferSource buffer,
            int packedLight,
            ItemRenderer itemRenderer,
            RandomSource rand,
            CallbackInfoReturnable<Boolean> cir
    ) {
        int age = entity.getAge();
        ItemTransform transform = ItemPhysicGuns.getInstance().getTransformationForItemOrNull(
            entity,
            (float)age
        );

        if (transform != null) {
            transform.applyTo(pose);
        }
    }
}
