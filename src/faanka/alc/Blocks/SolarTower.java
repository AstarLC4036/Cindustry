package faanka.alc.Blocks;

import arc.util.Log;
import arc.util.Time;
import mindustry.Vars;
import mindustry.gen.Building;
import mindustry.world.blocks.power.PowerGenerator;

public class SolarTower extends PowerGenerator {
    public int maxConnectCount = 0;
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
        private float updateTileTimer = 0;
        @Override
        public void updateTile()
        {
            updateTileTimer += Time.delta;
            if(updateTileTimer != 0.1)
            {
                return;
            }

            Log.info("Update once");
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

            updateTileTimer = 0;

            if(needUpdate)
            {
                powerProduction *= connectedCount;
                needUpdate = false;
            }
        }
    }
}
