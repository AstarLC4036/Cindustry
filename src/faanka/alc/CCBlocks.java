package faanka.alc;

import faanka.alc.Blocks.SolarNode;
import faanka.alc.Blocks.SolarTower;
import mindustry.content.Items;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.power.PowerBlock;

public class CCBlocks {
    //Power blocks
    public static PowerBlock mirrorNode, solarTower;

    public static void load()
    {
        mirrorNode = new SolarNode("mirror-node"){{
            requirements(Category.power, ItemStack.with(Items.copper, 1));
            size = 1;
        }};

        solarTower = new SolarTower("mirror-tower"){{
            requirements(Category.power, ItemStack.with(Items.copper, 1));
            size = 2;
            powerProduction = 100;
            maxConnectCount = 10;
            searchRange = 4;
        }};
    }
}