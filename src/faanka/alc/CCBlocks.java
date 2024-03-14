package faanka.alc;

import faanka.alc.blocks.SolarNode;
import faanka.alc.blocks.SolarTower;
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
            requirements(Category.power, ItemStack.with(Items.metaglass, 150), ItemStack.with(Items.titanium, 100), ItemStack.with(Items.silicon, 100));
            size = 1;
            localizedName = "Solar Node";
        }};

        solarTower = new SolarTower("mirror-tower"){{
            requirements(Category.power, ItemStack.with(Items.metaglass, 250), ItemStack.with(Items.silicon, 150), ItemStack.with(Items.surgeAlloy, 50), ItemStack.with(Items.plastanium, 75), ItemStack.with(Items.graphite, 120));
            size = 2;
            outputsPower = true;
            hasPower = true;
            powerProduction = 120;
            maxConnectCount = 72;
            searchRange = 9;
            localizedName = "Solar Tower";
        }};
    }

    public static void loadClassMap()
    {
        ClassMap.classes.put("SolarTower", faanka.alc.blocks.SolarTower.class);
        ClassMap.classes.put("SolarNode", faanka.alc.blocks.SolarNode.class);
    }
}
