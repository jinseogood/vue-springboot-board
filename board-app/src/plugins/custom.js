const myPlugin = {}

myPlugin.install = function(Vue) {
	/**
	 * 페이지 이동
	 * @param {*} url
	 */
	Vue.prototype.movePage = function(url) {
		if (this.$route.path !== url) {
			this.$router.push(url)
		}
	}
	/**
	 * 이전 페이지 이동
	 */
	Vue.prototype.prevPage = function() {
		window.history.length > 1 ? this.$router.go(-1) : this.$router.push('/')
	}
	/**
	 * 페이지 새로고침
	 */
	Vue.prototype.refresh = function() {
		this.$router.go(this.$router.currentRoute)
	}
	/**
	 * 확인창 표시
	 * @param {*} title
	 * @param {*} text
	 */
	Vue.prototype.confirmDialog = function(title, text) {
		let res = this.$dialog.confirm({
			title: title,
			text: text,
			persistent: true,
			actions: {
				false: 'No',
				true: {
					color: 'red',
					text: 'Yes',
				},
			},
		})

		return res
	}
	/**
	 * 입력창 표시
	 * @param {*} title
	 * @param {*} text
	 */
	Vue.prototype.promptDialog = function(title, text, content) {
		let res = this.$dialog.prompt({
			title: title,
			text: text,
			value: content,
			persistent: true,
		})

		return res
	}
}

export default myPlugin
