package com.unlikepaladin.pfm.blocks.models.chairDinner.neoforge;

import com.unlikepaladin.pfm.blocks.DinnerChairBlock;
import com.unlikepaladin.pfm.blocks.models.ModelHelper;
import com.unlikepaladin.pfm.blocks.models.neoforge.PFMNeoForgeBakedModel;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.render.model.ModelBakeSettings;
import net.minecraft.client.texture.Sprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockRenderView;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.model.data.ModelProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import net.minecraft.util.math.random.Random;

public class NeoForgeChairDinnerModel extends PFMNeoForgeBakedModel {
    public NeoForgeChairDinnerModel(ModelBakeSettings settings, List<BakedModel> templateBakedModels) {
        super(settings, templateBakedModels);
    }

    public static ModelProperty<Boolean> TUCKED = new ModelProperty<>();

    @Override
    public @NotNull ModelData getModelData(@NotNull BlockRenderView world, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull ModelData tileData) {
        if (state.getBlock() instanceof DinnerChairBlock) {
            ModelData.Builder builder = ModelData.builder();

            ModelData data = builder.build();
            data = super.getModelData(world, pos, state, data);
            return data.derive().with(TUCKED, state.get(DinnerChairBlock.TUCKED)).build();
        }
        return tileData;
    }

    @NotNull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @NotNull Random rand, @NotNull ModelData extraData, RenderLayer layer) {
        if (state != null && extraData != null && extraData.get(TUCKED) != null) {
            int tucked = Boolean.TRUE.equals(extraData.get(TUCKED)) ? 1 : 0;
            List<Sprite> spriteList = getSpriteList(state);
            List<BakedQuad> quads = getTemplateBakedModels().get(tucked).getQuads(state, side, rand, extraData, layer);
            return getQuadsWithTexture(quads, ModelHelper.getOakPlankLogSprites(), spriteList);
        }
        return Collections.emptyList();
    }

    @Override
    public List<BakedQuad> getQuads(ItemStack stack, @Nullable BlockState state, @Nullable Direction face, Random random) {
        List<Sprite> spriteList = getSpriteList(state);
        List<BakedQuad> quads = getTemplateBakedModels().get(0).getQuads(state, face, random);
        return getQuadsWithTexture(quads, ModelHelper.getOakPlankLogSprites(), spriteList);
    }
}