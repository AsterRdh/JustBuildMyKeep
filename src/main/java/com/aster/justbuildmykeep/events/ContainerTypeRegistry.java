package com.aster.justbuildmykeep.events;

import com.aster.justbuildmykeep.Utils;
import com.aster.justbuildmykeep.container.GoodsBaxContainer;
import com.aster.justbuildmykeep.container.ObsidianFirstContainer;
import com.aster.justbuildmykeep.container.ObsidianFirstContainerItemNumber;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainerTypeRegistry {
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Utils.MOD_ID);
    public static final RegistryObject<ContainerType<ObsidianFirstContainer>> obsidianFirstContainer = CONTAINERS.register(
            "wood_table_a",
            () -> IForgeContainerType
                    .create((int windowId, PlayerInventory inv, PacketBuffer data) -> new ObsidianFirstContainer(windowId, inv, data.readBlockPos(), Minecraft.getInstance().world, new ObsidianFirstContainerItemNumber())));
    public static final RegistryObject<ContainerType<GoodsBaxContainer>> goodsBoxContainer = CONTAINERS.register(
            "goods_box",
            () -> IForgeContainerType
                    .create((int windowId, PlayerInventory inv, PacketBuffer data) -> new GoodsBaxContainer(windowId, inv, data.readBlockPos(), Minecraft.getInstance().world, new ObsidianFirstContainerItemNumber())));
}
