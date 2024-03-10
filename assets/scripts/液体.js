function newLiquid(name) {
	exports[name] = (() => {
		let myLiquid = extend(Liquid, name, {});
		return myLiquid;
	})();
}
newLiquid("污水")
newLiquid("烯")
newLiquid("烷")
newLiquid("天然气")
newLiquid("氢气")
newLiquid("氟")
newLiquid("氧气")
newLiquid("氯气")
newLiquid("汞")
newLiquid("液氦")
newLiquid("液氖")
newLiquid("液氩")
newLiquid("液氪")
newLiquid("溴")
newLiquid("熔融岩石")
newLiquid("熔融盐")
newLiquid("熔融金属")
newLiquid("盐溶液")
newLiquid("硫酸")