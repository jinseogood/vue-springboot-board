import Vue from 'vue'
import App from './App.vue'
import router from '@/router/index'
import store from '@/store/index'
import vuetify from '@/plugins/vuetify'
import VuetifyDialog from 'vuetify-dialog'
import 'vuetify-dialog/dist/vuetify-dialog.css'

import custom from '@/plugins/custom'

Vue.config.productionTip = false
Vue.use(custom)
Vue.use(VuetifyDialog, {
	context: {
		vuetify,
	},
})

new Vue({
	router,
	store,
	vuetify,
	render: h => h(App),
}).$mount('#app')
