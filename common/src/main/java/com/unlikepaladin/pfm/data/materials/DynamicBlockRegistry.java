package com.unlikepaladin.pfm.data.materials;

import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class DynamicBlockRegistry {
    private static final Map<Class<? extends VariantBase<?>>, VariantRegistryBase<?>> BLOCK_SET_CONTAINERS = new ConcurrentHashMap<>();
    private static final ConcurrentLinkedDeque<Runnable> FINDER_ADDER = new ConcurrentLinkedDeque<>();

    public static void initialize() {
        compatInit();
        FINDER_ADDER.forEach(Runnable::run);
        FINDER_ADDER.clear();
        BLOCK_SET_CONTAINERS.values().forEach(VariantRegistryBase::buildAll);
        BLOCK_SET_CONTAINERS.values().forEach(VariantRegistryBase::onBlockInit);
    }

    @SuppressWarnings("unchecked")
    public static <T extends VariantBase<T>> VariantRegistryBase<T> getBlockSet(Class<T> type) {
        return (VariantRegistryBase<T>) BLOCK_SET_CONTAINERS.get(type);
    }

    public static void addBlockSetContainer(Class<? extends VariantBase<?>> variantBase, VariantRegistryBase<?> instance) {
        BLOCK_SET_CONTAINERS.put(variantBase, instance);
    }

    public static <T extends VariantBase<T>> void addBlockTypeFinder(Class<T> type, VariantBase.SetFinder<T> blockFinder) {
        FINDER_ADDER.add(() -> {
            VariantRegistryBase<T> container = getBlockSet(type);
            container.addFinder(blockFinder);
        });
    }


    public static void compatInit() {

        addBlockTypeFinder(WoodVariant.class, WoodVariant.Finder.simple(new Identifier("domum_ornamentum:cactus"),
                new Identifier("domum_ornamentum:green_cactus_extra"), new Identifier("cactus")));
        addBlockTypeFinder(WoodVariant.class, WoodVariant.Finder.simple(new Identifier("domum_ornamentum:cactus_extra"),
                new Identifier("domum_ornamentum:cactus_extra"), new Identifier("cactus")));

        addBlockTypeFinder(WoodVariant.class, WoodVariant.Finder.simple(
                "darkerdepths", "petrified", "petrified_planks", "petrified_log"));
        addBlockTypeFinder(WoodVariant.class, WoodVariant.Finder.simple(
                "pokecube_legends", "concrete", "concrete_planks", "concrete_log"));
        addBlockTypeFinder(WoodVariant.class, WoodVariant.Finder.simple(
                "terraqueous", "storm_cloud", "storm_cloud", "storm_cloud_column"));
        addBlockTypeFinder(WoodVariant.class, WoodVariant.Finder.simple(
                "terraqueous", "light_cloud", "light_cloud", "light_cloud_column"));
        addBlockTypeFinder(WoodVariant.class, WoodVariant.Finder.simple(
                "terraqueous", "dense_cloud", "dense_cloud", "dense_cloud_column"));

        WoodVariant.Finder embur = WoodVariant.Finder.simple(
                "byg", "embur", "embur_planks", "embur_pedu");
        embur.addChild("stripped_log", "stripped_embur_pedu");
        embur.addChild("wood", "embur_pedu_top");
        embur.addChild("stripped_wood", "stripped_embur_pedu_top");
        addBlockTypeFinder(WoodVariant.class, embur);
        WoodVariant.Finder emburOld = WoodVariant.Finder.simple(
                "byg", "embur", "embur_planks", "embur_pedu");
        emburOld.addChild("stripped_log", "stripped_embur_pedu");
        emburOld.addChild("wood", "embur_pedu_hyphae");
        emburOld.addChild("stripped_wood", "stripped_embur_pedu_hyphae");
        emburOld.addChild("fence", "embur_fence");
        emburOld.addChild("slab", "embur_slab");
        addBlockTypeFinder(WoodVariant.class, emburOld);

        // mcreator mod with typos...
        addBlockTypeFinder(WoodVariant.class, WoodVariant.Finder.simple(
                "nethers_exoticism", "jabuticaba", "jaboticaba_planks", "jabuticaba_log"));


        WoodVariant.Finder verdant = WoodVariant.Finder.simple(
                "nourished_end", "verdant", "verdant_planks", "verdant_stalk");
        verdant.addChild("wood", "verdant_hyphae");
        verdant.addChild("stripped_wood", "stripped_verdant_hyphae");
        verdant.addChild("stripped_log", "stripped_verdant_stem");
        addBlockTypeFinder(WoodVariant.class, verdant);


        WoodVariant.Finder cerulean = WoodVariant.Finder.simple(
                "nourished_end", "cerulean", "cerulean_planks", "cerulean_stem_thick");
        cerulean.addChild("stripped_wood", "stripped_cerulean_hyphae");
        cerulean.addChild("stripped_log", "cerulean_stem_stripped");
        addBlockTypeFinder(WoodVariant.class, cerulean);

        WoodVariant.Finder soulblight = WoodVariant.Finder.simple(
                "gardens_of_the_dead", "soulblight", "soulblight_planks", "soulblight_stem");
        cerulean.addChild("stripped_wood", "stripped_soulblight_hyphae");
        cerulean.addChild("wood", "soulblight_hyphae");
        cerulean.addChild("stripped_log", "stripped_soulblight_stem");
        addBlockTypeFinder(WoodVariant.class, soulblight);


        WoodVariant.Finder bamboo = WoodVariant.Finder.simple(
                "twigs", "bamboo", "stripped_bamboo_planks", "bundled_bamboo");

        bamboo.addChild("stripped_log", "stripped_bundled_bamboo");

        addBlockTypeFinder(WoodVariant.class, bamboo);

        addBlockTypeFinder(WoodVariant.class, WoodVariant.Finder.simple(
                "habitat", "fairy_ring_mushroom", "fairy_ring_mushroom_planks", "enhanced_fairy_ring_mushroom_stem"));

        WoodVariant.Finder floweringAzalea = WoodVariant.Finder.simple(
                "ecologics", "flowering_azalea", "flowering_azalea_planks", "flowering_azalea_log");
        floweringAzalea.addChild("stripped_log", "stripped_azalea_log");
        floweringAzalea.addChild("leaves", new Identifier("minecraft:flowering_azalea_leaves"));

        addBlockTypeFinder(WoodVariant.class, floweringAzalea);


        WoodVariant.Finder azalea = WoodVariant.Finder.simple(
                "ecologics", "azalea", "azalea_planks", "azalea_log");
        azalea.addChild("leaves", new Identifier("minecraft:azalea_leaves"));

        addBlockTypeFinder(WoodVariant.class, azalea);

        WoodVariant.Finder quarkAzalea = WoodVariant.Finder.simple(
                "quark", "azalea", "azalea_planks", "azalea_log");
        quarkAzalea.addChild("leaves", new Identifier("minecraft:azalea_leaves"));

        addBlockTypeFinder(WoodVariant.class, quarkAzalea);

        WoodVariant.Finder netherReed = WoodVariant.Finder.simple(
                "betternether", "nether_reed", "nether_reed_planks", "nether_reed_roof");

        addBlockTypeFinder(WoodVariant.class, netherReed);

        WoodVariant.Finder clutteredFloweringYellow = WoodVariant.Finder.simple(
                "luphieclutteredmod", "luphie_flowering_yellow", "luphie_flowering_yellow_planks", "luphie_flowering_yellow_log");
        clutteredFloweringYellow.addChild("wood", "luphie_flowering_yellow_wood");
        clutteredFloweringYellow.addChild("stripped_log", "stripped_luphie_flowering_log");
        clutteredFloweringYellow.addChild("stripped_wood", "stripped_luphie_flowering_wood");
        clutteredFloweringYellow.addChild("slab", "luphie_flowering_yellow_slab");
        clutteredFloweringYellow.addChild("fence", "luphie_flowering_yellow_fence");
        clutteredFloweringYellow.addChild("fence_gate", "luphie_flowering_yellow_fence_gate");
        clutteredFloweringYellow.addChild("button", "luphie_flowering_yellow_button");

        addBlockTypeFinder(WoodVariant.class, clutteredFloweringYellow);

        // Stones
        StoneVariant.Finder calcite = StoneVariant.Finder.simple(
                "minecraft", "calcite", "calcite", "stripped_warped_stem");

        addBlockTypeFinder(StoneVariant.class, calcite);

        StoneVariant.Finder lightWood = StoneVariant.Finder.simple(
                "minecraft", "light_wood", "quartz_block", "stripped_oak_log");

        addBlockTypeFinder(StoneVariant.class, lightWood);

        StoneVariant.Finder darkWood = StoneVariant.Finder.simple(
                "minecraft", "dark_wood", "quartz_block", "stripped_dark_oak_log");

        addBlockTypeFinder(StoneVariant.class, darkWood);

        StoneVariant.Finder quartz = StoneVariant.Finder.simple(
                "minecraft", "quartz", "quartz_block", "quartz_block");

        addBlockTypeFinder(StoneVariant.class, quartz);

        StoneVariant.Finder netherite = StoneVariant.Finder.simple(
                "minecraft", "netherite", "netherite_block", "ancient_debris");

        addBlockTypeFinder(StoneVariant.class, netherite);
    }
}

