<template>
  <div>
    <el-container>
      <el-header class="nav">
        <div class="navbox">
          <div>
            <img src="@/assets/logo.png" alt="" />
            <span style="margin-left: 7px; font-size: 20px; color: #fff"
              >图书管理系统</span
            >
            <span style="float: right; margin-right: 50px"
              ><el-dropdown trigger="click" style="cursor: pointer">
                <span
                  class="el-dropdown-link"
                  style="color: #fff; font-size: 15px"
                >
                  {{ admin.username }}
                  <i class="el-icon-arrow-down el-icon--right"></i>
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item
                    ><div @click="adminInfo">个人信息</div></el-dropdown-item
                  >
                  <el-dropdown-item
                    ><div @click="updatePass">修改密码</div></el-dropdown-item
                  >
                  <el-dropdown-item
                    ><div @click="logout">退出登录</div></el-dropdown-item
                  >
                </el-dropdown-menu>
              </el-dropdown></span
            >
          </div>
        </div>
      </el-header>
      <el-container>
        <el-aside width="200px" class="left">
          <el-col>
            <el-menu
              :default-active="$route.path"
              class="el-menu-vertical-demo"
              router
              background-color="#304156"
              text-color="#fff"
              active-text-color="#41a6ff"
            >
              <el-menu-item index="/home">
                <i class="el-icon-s-home"></i>
                <span slot="title">系统首页</span>
              </el-menu-item>
              <el-menu-item index="/count">
                <i class="el-icon-data-line"></i>
                <span slot="title">图表统计</span>
              </el-menu-item>
              <el-submenu index="2">
                <template slot="title">
                  <i class="el-icon-s-order"></i>
                  <span>管理员管理</span>
                </template>
                <el-menu-item-group>
                  <el-menu-item index="/admin">管理员列表</el-menu-item>
                </el-menu-item-group>
              </el-submenu>
              <el-submenu index="3">
                <template slot="title">
                  <i class="el-icon-user-solid"></i>
                  <span>人员管理</span>
                </template>
                <el-menu-item-group>
                  <el-menu-item index="/user">人员列表</el-menu-item>
                </el-menu-item-group>
              </el-submenu>

              <el-submenu index="4">
                <template slot="title">
                  <i class="el-icon-s-cooperation"></i>
                  <span>类别管理</span>
                </template>
                <el-menu-item-group>
                  <el-menu-item index="/category">类目列表</el-menu-item>
                </el-menu-item-group>
              </el-submenu>

              <el-submenu index="5">
                <template slot="title">
                  <i class="el-icon-reading"></i>
                  <span>图书管理</span>
                </template>
                <el-menu-item-group>
                  <el-menu-item index="/book">图书列表</el-menu-item>
                </el-menu-item-group>
              </el-submenu>

              <el-submenu index="6">
                <template slot="title">
                  <i class="el-icon-collection"></i>
                  <span>借书管理</span>
                </template>
                <el-menu-item-group>
                  <el-menu-item index="/borrow">借书列表</el-menu-item>
                </el-menu-item-group>
              </el-submenu>
              <el-submenu index="7">
                <template slot="title">
                  <i class="el-icon-receiving"></i>
                  <span>还书管理</span>
                </template>
                <el-menu-item-group>
                  <el-menu-item index="/returnBook">还书列表</el-menu-item>
                </el-menu-item-group>
              </el-submenu>
            </el-menu>
          </el-col>
        </el-aside>
        <el-main style="flex: 1; background-color: white">
          <router-view />
        </el-main>
      </el-container>
    </el-container>
    <el-dialog title="修改密码" :visible.sync="dialogTableVisible" width="30%">
      <el-form :model="form" label-width="100px" ref="formRef" :rules="rules">
        <el-form-item label="新密码" prop="newPass">
          <el-input
            v-model="form.newPass"
            autocomplete="off"
            show-password
          ></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="confirmPassword">
          <el-input
            v-model="form.confirmPassword"
            autocomplete="off"
            show-password
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogTableVisible = false">取 消</el-button>
        <el-button type="primary" @click="savePass">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { updatePassAPI, getAdminByIdAPI } from "@/api";
import Cookies from "js-cookie";
export default {
  name: "Layout",
  data() {
    return {
      adminList: [],
      form: {
        newPass: "",
        confirmPassword:"",
      },
      dialogTableVisible: false,
      admin: Cookies.get("admin") ? JSON.parse(Cookies.get("admin")) : {},
      rules: {
        newPass: [
          { required: true, message: "请输入新密码", trigger: "blur" },
          { min: 5, max: 10, message: "长度在5-10个字符", trigger: "blur" },
        ],
        confirmPassword: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 5, max: 10, message: "长度在5-10个字符", trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    logout() {
      // 清除浏览器用户数据
      Cookies.remove("admin");
      this.$router.push("/login");
      this.$message.success("退出成功");
    },
    adminInfo() {
      this.$router.push("/editadmin?id=" + this.adminList.id);
    },
    async updatePass() {
      const res = await getAdminByIdAPI(this.admin.id);
      console.log(res.data);
      this.form = res.data;
      this.dialogTableVisible = true;
    },
    async savePass() {
      this.$refs["formRef"].validate(async (valid) => {
        if (valid) {
          if (this.form.newPass !== this.form.confirmPassword) {
            this.$message.error("2次输入的新密码不相同")
            return false
          }
          const res = await updatePassAPI(this.form);
          console.log(res);
          if (res.code === "200") {
            this.$message.success("修改成功");
            if (this.form.id === this.admin.id) {
              Cookies.remove("admin");
              this.$router.push("/login");
            } else {
              this.load();
              this.dialogTableVisible = false;
            }
          } else {
            this.$message.error(res.msg);
          }
        }
      });
    },
  },
  async created() {
    this.adminList = JSON.parse(Cookies.get("admin"));
    console.log(this.adminList.id);
  },
};
</script>

<style>
.nav {
  height: 60px;
  line-height: 60px;
  background-color: #304156;
}
img {
  width: 40px;
  position: relative;
  top: 10px;
  left: 0px;
}
.left {
  overflow: hidden;
  height: 100vh;
  margin-right: 2px;
  background-color: #1f2d3d;
}
</style>