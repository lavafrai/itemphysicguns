package ru.lavafrai.svogame.itemphysicguns.mixin;

import net.minecraftforge.fml.loading.LoadingModList;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.objectweb.asm.tree.ClassNode;

import java.util.List;
import java.util.Set;

public class ItemPhysicGunsMixinPlugin implements IMixinConfigPlugin {

    @Override
    public void onLoad(String mixinPackage) {
        boolean hasItemPhysic = LoadingModList.get().getModFileById("itemphysic") != null;
        boolean hasItemPhysicLite = LoadingModList.get().getModFileById("itemphysiclite") != null;

        if (!hasItemPhysic && !hasItemPhysicLite) {
            throw new RuntimeException("[ItemPhysicGuns] Neither 'itemphysic' nor 'itemphysiclite' is installed! One of them is required.");
        }
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (mixinClassName.endsWith("ItemPhysicClientMixin")) {
            return LoadingModList.get().getModFileById("itemphysic") != null;
        }
        if (mixinClassName.endsWith("ItemPhysicLiteClientMixin")) {
            return LoadingModList.get().getModFileById("itemphysiclite") != null;
        }
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}
