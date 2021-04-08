package com.aster.justbuildmykeep.entity;

import com.aster.justbuildmykeep.Blocks;
import com.aster.justbuildmykeep.Utils;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityTypeRegistry {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Utils.MOD_ID);
    public static final RegistryObject<TileEntityType<FireBasketEntity>> obsidianTERTileEntity = TILE_ENTITIES.register("fire_basket_tileentity",
            () -> TileEntityType.Builder.create(FireBasketEntity::new, Blocks.FIRE_BASKET).build(null));
    public static final RegistryObject<TileEntityType<WeaponRackTileEntity>> WEAPON_RACK_ENTITIES = TILE_ENTITIES.register("weapon_rack",
            () -> TileEntityType.Builder.create(WeaponRackTileEntity::new, Blocks.WEAPON_RACK).build(null));
    public static final RegistryObject<TileEntityType<ArrowBarrelEntity>> ARROW_BARREL_ENTITIES = TILE_ENTITIES.register("arrow_barrel",
            () -> TileEntityType.Builder.create(ArrowBarrelEntity::new, Blocks.ARROW_BARREL).build(null));
    public static final RegistryObject<TileEntityType<ObsidianFirstContainerTileEntity>> OBSIDIAN_FIRST_CONTAINER_ENTITY = TILE_ENTITIES.register("wood_table_a",
            () -> TileEntityType.Builder.create(ObsidianFirstContainerTileEntity::new, Blocks.WOOD_TABLE1).build(null));

    public static RegistryObject<TileEntityType<EmblemShieldEntity>> emblemShieldEntity = TILE_ENTITIES.register("obsidian_ter_tileentity", () -> {
        return TileEntityType.Builder.create(() -> {
            return new EmblemShieldEntity();
        }, Blocks.EMBLEM_SHIELD).build(null);
    });
}
