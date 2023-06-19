<template>
  <div style="padding: 20px">
    <el-form :model="form" :rules="rules" ref="ruleForm" label-width="100px">
      <el-form-item label="类别名:" prop="name">
        <el-input v-model="form.name" placeholder="请输入类别名"></el-input>
      </el-form-item>
      <el-form-item label="备注:" prop="remark">
        <el-input v-model="form.remark" placeholder="请输入备注"></el-input>
      </el-form-item>
    </el-form>
    <div style="text-align: center; margin-top: 30px">
      <el-button type="primary" size="medium" @click="updateCategoryInfo"
        >修改</el-button
      >
      <el-button type="warning" size="medium" @click="$router.push('/category')"
        >取消</el-button
      >
    </div>
  </div>
</template>

<script>
import { getCategoryByIdAPI, updateCategoryInfoAPI } from "@/api";
export default {
  name: "EditCategory",
  data() {
    return {
      form: {
        id: "",
        name: "",
        remark: "",
      },
      rules: {
        name: [{ required: true, message: "请输入类别名", trigger: "blur" }],
      },
    };
  },
  async created() {
    const id = this.$route.query.id;
    const res = await getCategoryByIdAPI(id);
    this.form = res.data;
  },
  methods: {
    async updateCategoryInfo() {
      this.$refs["ruleForm"].validate(async (valid) => {
        if (valid) {
          const res = await updateCategoryInfoAPI(this.form);
          if (res.code === "200") {
            this.$message.success("更新成功！");
            this.$router.push("/category");
          } else {
            this.$message.error(res.msg);
          }
        }
      });
    },
  },
};
</script>

<style>
</style>