<template>
  <div>

  

    <div class="card" style="margin-bottom: 5px">
      <el-table :data="data.tableData" stripe>
        <el-table-column label="是否购买">
          <el-radio> </el-radio>
        </el-table-column>
        <el-table-column label="茶叶名称" prop="goodsName"></el-table-column>
        <el-table-column label="茶叶图片" prop="goodsImg">
          <template #default="scope">
            <el-image style="width: 50px; height: 50px; border-radius: 5px" :src="scope.row.goodsImg"
              :preview-src-list="[scope.row.goodsImg]" preview-teleported></el-image>
          </template>
        </el-table-column>
        <el-table-column label="购买数量" prop="num">
          <el-input-number v-model="num" :min="1" :max="10" size="small" />
        </el-table-column>
        <el-table-column label="单价" prop="num"></el-table-column>
        <el-table-column label="总价" prop="num"></el-table-column>

        <el-table-column label="操作" >
          <el-button type="info" text @click="">
            删除
          </el-button>
        </el-table-column>

      </el-table>
    </div>

    <div class="shopping_message_clearfix">
      购物车小计：￥{{ all_price }}
    </div>
    <hr style="border-top:1px dashed rgb(204,204,204)">
    <div style="display: flex; justify-content: end;">

      <el-button type="primary" plain class="jiesuan" @click="centerDialogVisible = true">去结算
        ></el-button>

    </div>

  
  </div>
</template>

<script setup>
import request from "@/utils/request";
import {reactive} from "vue";
import {ElMessageBox, ElMessage} from "element-plus";

const data = reactive({
  user: JSON.parse(localStorage.getItem('system-user') || '{}'),
  pageNum: 1,
  pageSize: 10,
  total: 0,
  formVisible: false,
  form: {},
  tableData: [],
  orderNo: null
})
//获取购物车数据
const getCart = () =>{
   request.get('/shoppingCart/getCart', {
    params: {
      userId: data.user.id
    }
  }).then(res => {
    data.tableData = res.data?.list
  })
}
getCart();




// 重置
const reset = () => {
  data.orderNo = null
  load()
}

load()
</script>