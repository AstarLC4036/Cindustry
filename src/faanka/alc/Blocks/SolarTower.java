package faanka.alc.Blocks;

import arc.util.Log;
import mindustry.Vars;
import mindustry.gen.Building;
import mindustry.world.blocks.power.PowerGenerator;

public class SolarTower extends PowerGenerator {
    public int maxConnectCount = 10;
    public int connectedCount = 0;
    public int searchRange = 0;

    public SolarTower(String name)
    {
        super(name);
        this.buildType = SolarTowerBuild::new;
    }

    public class SolarTowerBuild extends GeneratorBuild
    {
        private boolean needUpdate = false;
        @Override
        public void updateTile()
        {
            for (int dx = -searchRange; dx < searchRange; dx++)
            {
                for(int dy = -searchRange; dx < searchRange; dy++)
                {
                    Building build = Vars.world.build(tileX() + dx, tileY() + dy);
                    if((build instanceof SolarNode.SolarNodeBuild) && connectedCount < maxConnectCount && ((SolarNode) build.block).connectedTower == null)
                    {
                        Log.info("Detected a new avalible node.");
                        SolarNode.SolarNodeBuild validBuild = (SolarNode.SolarNodeBuild)build;
                        connectedCount += 1;
                        ((SolarNode)validBuild.block).connectedTower = SolarTower.this;

                        needUpdate = true;
                    }
                }
            }

            if(needUpdate)
            {
                powerProduction *= connectedCount;
                needUpdate = false;
            }
        }
    }
}
