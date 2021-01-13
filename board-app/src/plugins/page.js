const page = {}

page.install = function(Vue) {
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
}

export default page
