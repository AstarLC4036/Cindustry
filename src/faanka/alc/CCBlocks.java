package faanka.alc;

import faanka.alc.Blocks.SolarNode;
import faanka.alc.Blocks.SolarTower;
import mindustry.content.Items;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.power.PowerBlock;
import mindustry.mod.ClassMap;

public class CCBlocks {
    //Power blocks
    public static PowerBlock mirrorNode, solarTower;

    public static void load()
    {
        loadClassMap();
        mirrorNode = new SolarNode("mirror-node"){{
            requirements(Category.power, ItemStack.with(Items.copper, 1));
            size = 1;
            localizedName = "Solar Node";
        }};

        solarTower = new SolarTower("mirror-tower"){{
            requirements(Category.power, ItemStack.with(Items.copper, 1));
            size = 2;
            outputsPower = true;
            hasPower = true;
            powerProduction = 720;
            maxConnectCount = 10;
            searchRange = 4;
            localizedName = "Solar Tower";
        }};
    }

    public static void loadClassMap()
    {
        ClassMap.classes.put("SolarTower", fannka.alc.Blocks.SolarTower.class);
    }
}
