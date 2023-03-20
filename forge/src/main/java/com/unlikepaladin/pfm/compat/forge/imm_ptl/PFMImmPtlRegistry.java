package com.unlikepaladin.pfm.compat.forge.imm_ptl;

import com.unlikepaladin.pfm.compat.forge.imm_ptl.entity.PFMMirrorEntity;
import com.unlikepaladin.pfm.registry.BlockItemRegistry;
import com.unlikepaladin.pfm.registry.EntityRegistry;
import com.unlikepaladin.pfm.registry.PaladinFurnitureModBlocksItems;
import com.unlikepaladin.pfm.registry.forge.BlockItemRegistryForge;
import com.unlikepaladin.pfm.registry.forge.EntityRegistryForge;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;

public class PFMImmPtlRegistry {
   public static final EntityType<PFMMirrorEntity> MIRROR = EntityType.Builder.create(PFMMirrorEntity::new, SpawnGroup.MISC).setDimensions(0.0F, 0.0F).makeFireImmune().disableSummon().build("mirror_entity");
    public static void register() {
        PaladinFurnitureModBlocksItems.WHITE_MIRROR = new PFMMirrorBlockIP(AbstractBlock.Settings.of(Material.STONE, MapColor.WHITE).nonOpaque());
        BlockItemRegistry.registerFurniture("white_mirror", PaladinFurnitureModBlocksItems.WHITE_MIRROR, true);

        EntityRegistry.registerEntityType("mirror_entity", MIRROR);
    }
}