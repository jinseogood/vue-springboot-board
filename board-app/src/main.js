import Vue from 'vue'
import App from './App.vue'
import router from '@/router/index'
import store from '@/store/index'
import vuetify from '@/plugins/vuetify'
// Vuetify-dialog
import VuetifyDialog from 'vuetify-dialog'
import 'vuetify-dialog/dist/vuetify-dialog.css'
// 사용자 정의 페이지 관련 함수
import page from '@/plugins/page'
// 사용자 정의 다이얼로그 관련 함수
import dialog from '@/plugins/dialog'
// 버튼 전역 컴포넌트
import Button from '@/components/common/Button'

Vue.config.productionTip = false
// 사용자 정의 페이지 함수 사용 설정
Vue.use(page)
// 사용자 정의 다이얼로그 함수 사용 설정
Vue.use(dialog)
// Vuetify-dialog 사용 설정
Vue.use(VuetifyDialog, {
	context: {
		vuetify,
	},
})
// 버튼 전역 컴포넌트 선언
Vue.component('Button', Button)

new Vue({
	router,
	store,
	vuetify,
	render: h => h(App),
}).$mount('#app')
