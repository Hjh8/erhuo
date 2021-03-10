<template>
  <div class="sideDiv">
    <Menu active-name="1">
      <MenuGroup title="二货分类">
        <Menu-item name="最新发布" key="1" to="index">
          <Icon type="ios-eye" size="20"/>
          最新发布
        </Menu-item>
        <Menu-item key="2" name="数码电子" to="digital">
          <Icon type="md-game-controller-b" size="20"/>
          数码电子
        </Menu-item>
        <Menu-item key="3" name="衣服/日用" to="daily">
          <Icon type="ios-shirt" size="20"/>
          衣服/日用
        </Menu-item>
        <Menu-item key="4" name="图书教材" to="books">
          <Icon type="ios-book" size="20"/>
          图书教材
        </Menu-item>
        <Menu-item key="5" name="运动棋牌" to="sports">
          <Icon type="ios-basketball" size="20"/>
          运动棋牌
        </Menu-item>
        <Menu-item key="6" name="票券小物" to="coupon">
          <Icon type="ios-map" size="20"/>
          票券小物
        </Menu-item>
        <Menu-item key="7" name="其他" to="other">
          <Icon type="md-barcode" size="20"/>
          其他
        </Menu-item>
      </MenuGroup>
    </Menu>
  </div>
</template>

<script>


export default {

}
</script>

<style>
.sideDiv{
  width: 240px;
  height: 68%;
}
</style>