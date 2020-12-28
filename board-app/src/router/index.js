import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
	{
		path: '/',
		name: 'Main',
		redirect: '/home',
	},
	{
		path: '*',
		name: '404',
		component: () => import('@/views/error/404.vue'),
	},
	{
		path: '/home',
		name: 'Home',
		component: Home,
	},
	{
		path: '/list',
		name: 'BoardList',
		// route level code-splitting
		// this generates a separate chunk (about.[hash].js) for this route
		// which is lazy-loaded when the route is visited.
		component: () =>
			import(/* webpackChunkName: "about" */ '../views/board/ListView.vue'),
	},
	{
		path: '/write',
		name: 'BoardWrite',
		component: () => import('../views/board/WriteView.vue'),
	},
	{
		path: '/detail',
		name: 'BoardDetail',
		component: () => import('../views/board/DetailView.vue'),
	},
	{
		path: '/edit',
		name: 'BoardEdit',
		component: () => import('../views/board/EditView.vue'),
	},
]

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes,
})

export default router
