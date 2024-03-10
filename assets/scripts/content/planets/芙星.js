/**
    看源码Planet.java:
        public Planet(String name, Planet parent, float radius, int sectorSize)
    可以知道Planet的一个构造函数包含以下参数:
        name: 星球的name; 
        parent: 母星(可为null); 
        radius: 半径(单位: /个赛普罗); 
        sectorSize: 区块大小
            区块数量 = 10*3ˢᵉᶜᵗᵒʳˢⁱᶻᵉ + 2;
            比如说Serpulo.sectorSize = 3;那么Serpulo的区块数量就为: 10*3³+2=272;
*/

// 1. 创建星球
const colorSrc = Liquids.water.color;
const colorDst = Pal.heal;
var 芙星 = extend(Planet, "芙星", Planets.sun, 2, 4, {
    // generator: 区块生成器
    generator: extend(SerpuloPlanetGenerator, {
        // 默认核心蓝图
        defaultLoadout: Schematics.readBase64("bXNjaAF4nA3JMQ6AIBAAwQXFRr9i4XuMBR5XkCAYkP9LphwcbmLO/lHMwRq0SY3vF0sGluRvTQ17XoZNStU9d0na20gDduAHAc0Org=="),
        // 给定一个六边形位置 返回一个颜色
        getColor(position){
            // 种子 叠加数量 坚持(每次叠加振幅乘率) 频率 坐标
            let noise = Simplex.noise3d(this.seed, 7, 0.7, 1, position.x, position.y, position.z);
            
            if(noise > 0.5){
                return colorDst;
            }
            
            let deep = Simplex.noise3d(this.seed, 4, 0.7, 1, position.x, position.y, position.z);
            
            return Tmp.c1.set(colorSrc).lerp(Color.black, deep);
        },
        
        generateSector(sector){
            this.super$generateSector(sector);
            
            // TODO
        },
    }),
        
    // public HexSkyMesh(Planet planet, int seed, float speed, float radius, int divisions, Color color, int octaves, float persistence, float scl, float thresh)
    // 发射时核心携带资源倍率
    launchCapacityMultiplier: 0.5,
    sectorSeed: 0, // 区块生成种子
    allowWaves: true, // 是否允许生成区块波次
    allowWaveSimulation: true, // 是否允许后台自动挂波次
    allowSectorInvasion: true, // 是否允许区块被敌人进攻
    allowLaunchSchematics: true, // 是否允许使用核心蓝图
    enemyBuildSpeedMultiplier: 1, // 敌人建筑倍率 这里不做处理
    enemyCoreSpawnReplace: true, // 敌人最后的核心被摧毁后是否要生成一个出怪点
    allowLaunchLoadout: true, // 是否允许发射时携带物资
    clearSectorOnLose: false, // 区块输了是否重置区块(一次过是吧)
    tidalLock: false, // 是否潮汐锁定(冷知识: erekir潮汐锁定)
    prebuildBase: true, // 是否需要像e星那样帅气的着陆建筑特效
    
    // ruleSetter: 当区块地图加载时会自动将地图规则作以下调整
    ruleSetter: r => {
        r.waveTeam = Team.crux;
        r.placeRangeCheck = false; // 恶心的建筑区
        r.showSpawns = false; // 在地图上不显示出怪点
    },
    
    iconColor: Color.valueOf("FFEDAA"), // 星球面板(PlanetDialog)上的颜色
    
    // 环境
    atmosphereColor: Color.valueOf("FFEDAA"), // 环境色
    atmosphereRadIn: 0,
    atmosphereRadOut: 0.6,
    
    // 光照
    updateLighting: true, // 是否有昼夜更替
    lightSrcFrom: 0.2, // 光照数值 这里不做处理
    lightSrcTo: 1,
    lightDstFrom: 0.6,
    lightDstTo: 2,
    
    startSector: 1, // 开始区块(类似零号地区) (这个的查看可以用我的mod<显示星球区块id>)
    
    alwaysUnlocked: true, // 是否默认解锁
    
    landCloudColor: Object.assign(Pal.spore.cpy(), {a: 0.5}), // 核心着陆烟尘的颜色
    
    defaultCore: Blocks.coreShard, // 默认的核心 这里不做处理
    
    init(){
        this.super$init();
        
        // meshLoader, cloudMeshLoader 渲染作用,这里借用Serpulo的,具体待研究
        this.meshLoader = () => new HexMesh(this, 8);
        this.cloudMeshLoader = () => new MultiMesh(
            new HexSkyMesh(this, 12, 0.17, 0.11, 3, Object.assign(new Color().set(colorSrc).mul(0.8), {a: 0.65}), 3, 0.35, 0.7, 0.30),
            new HexSkyMesh(this, 2, 0.7, 0.14, 4, Object.assign(Color.white.cpy().lerp(colorDst, 0.65), {a: 0.70}), 1, 0.5, 1, 0.47)
        );
        
        this.hiddenItems.addAll(), // 在星球上隐藏的物品,会决定该星球上能建造的方块
        this.unlockedOnLand.addAll(); // 着陆该星球就解锁某些物品
    },
});

module.exports = 芙星;