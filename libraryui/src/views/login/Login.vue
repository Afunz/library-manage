<template>
  <div style="height: 100vh; overflow: hidden; position: relative">
    <el-card class="verify" v-if="loginAdmin.id">
      <slide-verify
        :l="42"
        :r="10"
        :w="310"
        :h="155"
        :accuracy="5"
        slider-text="向右滑动"
        @success="onSuccess"
        @fail="onFail"
        @refresh="onRefresh"
        :imgs="imgs"
      ></slide-verify>
    </el-card>

    <div
      style="
        width: 500px;
        height: 350px;
        background-color: white;
        border-radius: 30px;
        margin: 260px auto;
        padding: 30px;
      "
    >
      <div
        style="
          margin: 30px;
          text-align: center;
          font-size: 30px;
          font-weight: bold;
          color: #293a5d;
        "
      >
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
      </div>
      <el-form :model="admin" :rules="rules" ref="loginForm">
        <el-form-item prop="username">
          <el-input
            placeholder="请输入账号"
            prefix-icon="el-icon-user"
            size="medium"
            v-model="admin.username"
          ></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            placeholder="请输入密码"
            show-password
            prefix-icon="el-icon-lock"
            size="medium"
            v-model="admin.password"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button
            style="width: 100%"
            size="medium"
            type="primary"
            @click="login"
            >登录</el-button
          >
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { loginAPI } from "@/api";
import Cookies from "js-cookie";
export default {
  name: "Login",
  data() {
    return {
      loginAdmin: {},
      admin: {
        username: "",
        password: "",
      },
      imgs: [
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fitbbs%2F1411%2F03%2Fc11%2F40513136_1415006215595.jpg&refer=http%3A%2F%2Fimg.pconline.com.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1658045243&t=66cafeed4b05e3cc2337bd619564552a",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0146425bbdc6efa801213dead7d60e.jpg%401280w_1l_2o_100sh.jpg&refer=http%3A%2F%2Fimg.zcool.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1658045243&t=e11d669624d5ac428996e933f67f5d93",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201804%2F28%2F20180428190056_cnorp.jpg&refer=http%3A%2F%2Fb-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1658045243&t=a174254b82136a546d08f4e903dddb59",
        "https://gss0.baidu.com/70cFfyinKgQFm2e88IuM_a/baike/pic/item/14ce36d3d539b6005fd3d015e550352ac65cb777.jpg",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbkimg.cdn.bcebos.com%2Fpic%2F18d8bc3eb13533fa828b4f630598ea1f4134970aed60&refer=http%3A%2F%2Fbkimg.cdn.bcebos.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1658045243&t=fb598fe1de3576cf76269f913d9fad0b",
      ],
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { min: 3, max: 10, message: "长度在3-10个字符", trigger: "blur" },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 3, max: 10, message: "长度在3-10个字符", trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    async login() {
      this.$refs["loginForm"].validate(async (valid) => {
        if (valid) {
          const res = await loginAPI(this.admin);
          if (res.code === "200") {
            if (res.data !== null) {
              console.log(res);
              this.loginAdmin = res.data;
            }
          } else {
            this.$message.error(res.msg);
          }
        }
      });
    },
    onSuccess() {
      // 滑块验证通过之后触发的
      Cookies.set("admin", JSON.stringify(this.loginAdmin));
      this.$message.success("登录成功");
      this.$router.push("/");
    },
    onFail() {
      console.log("onFail");
    },
    onRefresh() {
      console.log("refresh");
    },
  },
};
</script>

<style>
.verify {
  width: fit-content;
  background-color: white;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 1000;
}
body {
  width: 100%;
  height: 100vh;
  background: url("@/assets/login.jpg") center center no-repeat;
  background-size: 100% 100%;
}
</style>