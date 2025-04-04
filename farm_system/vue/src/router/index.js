import {createRouter, createWebHistory} from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      component: () => import('@/views/manager.vue'),
      redirect: '/Buy',
      children: [
        { path: 'person', component: () => import('@/views/manager/Person.vue')},
        { path: 'password', component: () => import('@/views/manager/Password.vue')},
        { path: 'admin', component: () => import('@/views/manager/Admin.vue')},
        { path: 'category', component: () => import('@/views/manager/Category.vue')},
        { path: 'goods', component: () => import('@/views/manager/Goods.vue')},
        { path: 'goodsStock', component: () => import('@/views/manager/GoodsStock.vue')},
        { path: 'user', component: () => import('@/views/manager/User.vue')},
        { path: 'buy', component: () => import('@/views/manager/Buy.vue')},
        { path: 'orders', component: () => import('@/views/manager/Orders.vue')},
        { path: 'ShoppingCart', component: () => import('@/views/manager/ShoppingCart.vue')},
      ]
    },
    { path: '/login', component: () => import('@/views/Login.vue')},
    { path: '/register', component: () => import('@/views/Register.vue')},
  ]
})

export default router
