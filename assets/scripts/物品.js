function newItem(name) {
	exports[name] = (() => {
		let myItem = extend(Item, name, {});
		return myItem;
	})();
}
newItem("铁")
newItem("铝")
newItem("钨")
newItem("钾")
newItem("钠")
newItem("钙")
newItem("镁")
newItem("锌")
newItem("铬")
newItem("镍")
newItem("锰")
newItem("干电池")
newItem("满铅酸蓄电池")
newItem("空铅酸蓄电池")
newItem("废金属")
newItem("强化合金")
newItem("氢氧化物")
newItem("氧化物")
newItem("盐")
newItem("石料")
newItem("石油焦")
newItem("矿物块")
newItem("砷")
newItem("硒")
newItem("硫")
newItem("硬质聚合塑料")
newItem("硼")
newItem("碲")
newItem("碳化物")
newItem("磷")
newItem("耐高温聚合塑料")
newItem("聚合塑料")
newItem("钒")
newItem("钪")
newItem("钴")
newItem("铍")
newItem("锂")
newItem("锗")
newItem("镓")
newItem("穿甲导弹")
