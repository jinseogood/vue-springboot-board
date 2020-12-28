import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

// 스낵바 설정
export const SET_SNACKBAR = 'SET_SNACKBAR'

export default new Vuex.Store({
	state: {
		sb: {
			show: false,
			msg: '',
			color: '',
		},
	},
	mutations: {
		[SET_SNACKBAR](state, sb) {
			state.sb = sb
		},
	},
	actions: {},
	modules: {},
})
