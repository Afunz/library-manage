<template>
  <div style="padding: 20px">
    <el-form :model="form" :rules="rules" ref="ruleForm" label-width="100px">
      <el-form-item label="类别名:" prop="name">
        <el-input v-model="form.name" placeholder="请输入类别"></el-input>
      </el-form-item>
      <el-form-item label="备注:" prop="remark">
        <el-input v-model="form.remark" placeholder="请输入备注"></el-input>
      </el-form-item>
    </el-form>
    <div style="text-align: center; margin-top: 30px">
      <el-button type="primary" size="medium" @click="addCategoryInfo"
        >提交</el-button
      >
      <el-button type="warning" size="medium" @click="$router.push('/category')"
        >取消</el-button
      >
    </div>
  </div>
</template>

<script>
import { addCategoryInfoAPI } from "@/api";
export default {
  name: "AddCategory",
  data() {
    return {
      form: {
        name: "",
        remark: "",
      },
      rules: {
        name: [{ required: true, message: "请输入类别名", trigger: "blur" }],
      },
    };
  },
  methods: {
    async addCategoryInfo() {
      this.$refs["ruleForm"].validate(async (valid) => {
        if (valid) {
          const res = await addCategoryInfoAPI(this.form);
          if (res.code === "200") {
            this.$message.success("新增成功！");
            this.$refs["ruleForm"].resetFields();
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