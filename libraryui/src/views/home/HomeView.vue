<template>
  <div>
    <h1
      style="
        text-align: center;
        font-size: 30px;
        margin-top: 0px;
        font-family: cursive;
      "
      class="font-shadow"
    >
      图书管理系统
    </h1>
    <div class="home"></div>
  </div>
</template>

<script>
export default {
  name: "HomeView",
};
</script>
<style scoped>
.home {
  margin: 0 auto;
  border-radius: 100px;
  border: saddlebrown;
  box-shadow: 0px 0px 50px 0px rgba(121, 179, 185, 0.34);
  width: 85%;
  height: 680px;
  background-size: 100% 100%;
  background-image: url("@/assets/home.jpg");
}
</style>